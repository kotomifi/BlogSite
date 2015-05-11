package com.apping.blog.web;

import com.apping.blog.Config;
import com.apping.blog.entity.Blog;
import com.apping.blog.mapper.BlogMapper;
import com.apping.blog.util.MDFileParser;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.List;

/**
 * Created by baisu on 15-3-28.
 */

@Path("/blog")
public class BlogServiceWeb {


    @GET
    @Path("/findById/{blog_id}")
    @Produces(MediaType.TEXT_HTML)
    public String findById(@PathParam("blog_id") long blog_id) {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        System.out.println(blog_id);
        Blog blog = blogMapper.findById(blog_id);
        sqlSession.close();

        if (blog != null) {
            MDFileParser parser = new MDFileParser();
            File file = new File(Config.BLOG_FILE_PATH + "/" + blog.getBlog_file());
            parser.parse(file);


            String head = "<head>\n" +
                    "            <meta charset=\"utf-8\">\n" +
                    "            <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";

            String link = "<link href=\"http://kevinburke.bitbucket.org/markdowncss/markdown.css\" rel=\"stylesheet\"></link>";
            head += link;
            head += "</head>";

            return head + parser.getContent();
        }
        return null;
    }

    @GET
    @Path("/topBlogs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> getTopBlogs() {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> topBlogs = blogMapper.getTopBlogs(Config.BLOG_LIMIT_NUM);

        sqlSession.close();
        return topBlogs;
    }

    @GET
    @Path("/moreBlogs/{blog_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> getMoreBlogs(@PathParam("blog_id") long blog_id) {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> moreBlogs = blogMapper.getMoreBlogs(Config.BLOG_LIMIT_NUM, blog_id);

        sqlSession.close();
        return moreBlogs;
    }


    @GET
    @Path("/getByTag/{tag_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> getMoreBlogs(@PathParam("tag_name") String tag_name) {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> moreBlogs = blogMapper.getByTag(tag_name);

        sqlSession.close();
        return moreBlogs;
    }
}
