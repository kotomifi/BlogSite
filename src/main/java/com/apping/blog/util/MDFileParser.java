package com.apping.blog.util;

import com.apping.blog.entity.Blog;
import com.apping.blog.entity.Tag;
import org.markdown4j.Markdown4jProcessor;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 用来解析自定义的bolg文件（在markdown 文件开头添加了一些属性）
 * Created by baisu on 15-4-3.
 */
public class MDFileParser {

    /** 统计已解析关键词的数量 */
    private int parserCount = 0;

    /** 用来保存解析 MD 文件的属性 */
    private Blog mBlog = new Blog();

    /** 保存标签 */
    private List<Tag> tags = new ArrayList<Tag>();

    /** 保存MD文件正文内容　*/
    private StringBuilder content = new StringBuilder();

    /**
     * 解析MD文件
     *
     * @param file 需要解析的文件名
     */
    public void parse(File file) {
        if (!file.exists() || !file.isFile()) {
            return;
        }

        initBlog(file);

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "utf-8");
            br = new BufferedReader(isr);

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                parseLine(line);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 获取解析完的blog属性
     */
    public Blog getBlogProperty() {

        return mBlog;
    }

    /**
     * 获取标签列表
     * @return
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * 获取剩余markdown内容
     */
    public String getContent() {
        String html = content.toString();
        try {
            return new Markdown4jProcessor().process(html);
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * 解析 MD文件关键词
     * @param line
     */
    public void parseLine(String line) {
        String words[] = line.split(":");
        if (words.length == 0) {
            return;
        }

        Matcher dateMatcher = BlogRegex.dateReg.matcher(words[0]);
        Matcher authorMatcher = BlogRegex.authorReg.matcher(words[0]);
        Matcher titleMatcher = BlogRegex.titleReg.matcher(words[0]);
        Matcher tagsMatcher = BlogRegex.tagsReg.matcher(words[0]);
        Matcher abstractMatcher = BlogRegex.abstractReg.matcher(words[0]);

        if (authorMatcher.matches() && parserCount <= 4) {
            mBlog.setBlog_author(words[1].trim());
            ++parserCount;
        } else if (titleMatcher.matches() && parserCount <= 4) {
            mBlog.setBlog_title(words[1].trim());
            ++parserCount;
        } else if (tagsMatcher.matches() && parserCount <= 4) {
            parseTags(words[1].trim());
            ++parserCount;
        } else if (dateMatcher.matches() && parserCount <= 4) {
            Date mDate = str2Date(words[1].trim());
            mBlog.setBlog_date(mDate);
            ++parserCount;
        } else if (abstractMatcher.matches() && parserCount <= 4) {
            mBlog.setBlog_abstract(words[1].trim());
            ++parserCount;
        } else {
            if (parserCount >= 4) {
                content.append(line);
                content.append("\n");
            }
        }
    }

    /**
     * 解析tags
     * @param tagLine
     */
    public void parseTags(String tagLine) {
        String tagTrim = tagLine.replaceAll("\\s*", "");
        String tagArr[] = tagTrim.split(",");

        for (int i = 0; i < tagArr.length; i++) {
            Tag mTag = new Tag(tagArr[i], mBlog.getBlog_id());
            tags.add(mTag);
        }
    }

    /**
     * 字符串转Date类型
     * @param str
     * @return
     */
    public Date str2Date(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }

    public void initBlog(File file) {
        Date d = new Date();
        mBlog.setBlog_id(d.getTime());

        mBlog.setBlog_file(file.getName());

        mBlog.setBlog_modified(FileUtil.fileModifiedTime(file));
    }

}
