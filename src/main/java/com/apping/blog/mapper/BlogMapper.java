package com.apping.blog.mapper;

import com.apping.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by baisu on 15-3-28.
 */
public interface BlogMapper {
    public Blog findById(long blog_id);

    public Blog findByFileName(String blog_file);

    public List<Blog> getTopBlogs(int limit);

    public List<Blog> getMoreBlogs(@Param("limit") int limit, @Param("position") long position);

    public void add(Blog blog);

    public void delete(Blog blog);

    public void deleteById(long blog_id);

    public void update(Blog blog);

    public List<Blog> getByTag(String tagName);

}
