package com.apping.blog.mapper;

import com.apping.blog.entity.Blog;
import com.apping.blog.entity.Tag;
import com.apping.blog.entity.TagView;

import java.util.List;

/**
 * Created by baisu on 15-3-28.
 */
public interface TagMapper {

    public void add(Tag tag);

    public List<TagView> getTags();

    public List<Blog> getByName();

    public void update(Tag tag);

    public void delete(long blog_id);
}
