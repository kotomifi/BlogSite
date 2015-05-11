package com.apping.blog.service;

import com.apping.blog.entity.Tag;
import com.apping.blog.entity.TagView;
import com.apping.blog.mapper.TagMapper;

import java.util.List;

/**
 * Created by baisu on 15-4-10.
 */
public class TagService {

    private TagMapper mapper;

    public void add(Tag tag) {
        mapper.add(tag);
    }

    public List<TagView> getTags() {
        return mapper.getTags();
    }

    public void update(Tag tag) {
        mapper.update(tag);
    }

    public void delete(long blog_id) {
        mapper.delete(blog_id);
    }
}
