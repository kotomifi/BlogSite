package com.apping.blog.util;

import com.apping.blog.Config;
import com.apping.blog.entity.Blog;
import com.apping.blog.entity.Tag;
import com.apping.blog.mapper.BlogMapper;
import com.apping.blog.mapper.TagMapper;
import com.apping.blog.web.BaseServiceWeb;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 当程序启动时，创建一个线程来定时遍历git版本库，并解析
 *
 * Created by baisu on 15-4-3.
 */
public class InitMDFile implements ServletContextListener {

    private final static int FILE_SAME = 0;
    private final static int FILE_CHANGE = 1;
    private final static int FILE_ADD = 2;

    private long blog_id;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        /**
         * 每３min更新版本库
         * 解析一次md文件
         * 同步一次图片目录
         */
        Runnable runnable = new Runnable() {
            public void run() {
                RepositoryUtil repositoryUtil = new RepositoryUtil();

                File file = new File(Config.LOCAL_PATH);
                System.out.println(file.getAbsolutePath());
                System.out.println(file.exists());
                if (file.exists()) {
                    repositoryUtil.pullRepository();
                } else {
                    System.out.println(file.getName());
                    repositoryUtil.initRepository();
                }

                parseBlogFile();
                FileUtil.syncImgDir();
            }
        };

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 3, TimeUnit.MINUTES);

        // 每２min解析一次MD文件
//        Runnable runnable2 = new Runnable() {
//            @Override
//            public void run() {
//                parseBlogFile();
//            }
//        };
//
//        ScheduledExecutorService service2 = Executors.newSingleThreadScheduledExecutor();
//        service2.scheduleAtFixedRate(runnable2, 0, 3, TimeUnit.MINUTES);

//        new Thread() {
//            @Override
//            public void run() {
//
//                while (true) {
//                    parseBlogFile();
//
//                    // 每隔2分钟解析一次文件目录
//                    try {
//                        Thread.sleep(60000 * 2);
//                    } catch (InterruptedException e) {
//                        return;
//                    }
//                }
//            }
//        }.start();

        // 每２miin同步图片目录
//        new Thread() {
//            @Override
//            public void run() {
//
//                while (true) {
//                    FileUtil.syncImgDir();
//
//                    // 每隔2分钟解析一次文件目录
//                    try {
//                        Thread.sleep(60000 * 2);
//                    } catch (InterruptedException e) {
//                        return;
//                    }
//                }
//            }
//        }.start();

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     * 遍历博客目录下的文件，若文件新增或者文件修改，则重新解析该文件
     */
    public void parseBlogFile() {

        File file = new File(Config.BLOG_FILE_PATH);
        if (file.isDirectory()) {
            File[] mdFiles = file.listFiles();
            for (File f : mdFiles) {
                if (f.isFile() && f.getName().endsWith(".md")) {
                    System.out.println(f.getAbsolutePath());
                    switch (fileModified(f.getName())) {
                        case FILE_SAME:
                            break;
                        case FILE_CHANGE:
                            updateBlog(f);
                            break;
                        case FILE_ADD:
                            addBlog(f);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    public void addBlog(File f) {
        MDFileParser mParser = new MDFileParser();
        mParser.parse(f);

        // 添加到数据库中
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.add(mParser.getBlogProperty());
        System.out.println(mParser.getBlogProperty().toString());

        //添加tag到数据库中
        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);
        List<Tag> tags = mParser.getTags();
        for (Tag t : tags) {
            tagMapper.add(t);
        }
        sqlSession.commit();
        sqlSession.close();
    }

    public void updateBlog(File f) {
        MDFileParser mParser = new MDFileParser();
        mParser.parse(f);

        // 添加到数据库中
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();


        //删除旧tag
        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);
        tagMapper.delete(blog_id);

        //删除旧blog
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.deleteById(blog_id);

        //跟新blog
        blogMapper.add(mParser.getBlogProperty());
        System.out.println(mParser.getBlogProperty().toString());

        //插入新tag
        List<Tag> tags = mParser.getTags();
        for (Tag t : tags) {
            tagMapper.add(t);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 判断一个文件是否修改
     * 文件没有改变返回０
     * 文件修改返回１
     * 新文件返回２
     *
     * @param fileName
     * @return
     */
    public int fileModified(String fileName) {
        System.out.println(fileName);
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog mBlog = blogMapper.findByFileName(fileName);
        sqlSession.close();
        if (mBlog != null) {
            long preTime = mBlog.getBlog_modified();
            long curTime = FileUtil.fileModifiedTime(new File(Config.BLOG_FILE_PATH + "/" + fileName));
            if (curTime == preTime) {
                return FILE_SAME;
            }
            blog_id = mBlog.getBlog_id();
            return FILE_CHANGE;
        }
        return FILE_ADD;
    }
}
