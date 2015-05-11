package com.apping.blog.service;

import com.apping.blog.entity.Blog;
import com.apping.blog.mapper.BlogMapper;

import java.util.List;

/**
 * Created by baisu on 15-3-28.
 */
public class BlogService {
    private BlogMapper mapper;
    public Blog findById(long blog_id) {
        return mapper.findById(blog_id);
    }

    public Blog findByFileName(String blog_file) {
        return mapper.findByFileName(blog_file);
    }

    public List<Blog> getMoreBlogs(int limit, long position) {
        return mapper.getMoreBlogs(limit, position);
    }

    /**
     * 获取最新博客
     * @return
     */
    public List<Blog> getTopBlogs(int limit) {
        return mapper.getTopBlogs(limit);
    }

    /**
     * 添加blog记录
     * @param blog
     */
    public void add(Blog blog) {
        mapper.add(blog);
    }

    public void deleteById(long blog_id) {
        mapper.deleteById(blog_id);
    }


    public List<Blog> getByTag(String tagName) {
        return mapper.getByTag(tagName);
    }
}
