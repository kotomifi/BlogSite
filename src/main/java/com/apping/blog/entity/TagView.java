package com.apping.blog.entity;

/**
 * Created by baisu on 15-4-10.
 */
public class TagView {

    private String tag_name;

    private int blog_count;

    public String getTag_name() {
        return tag_name;
    }

    public int getBlog_count() {
        return blog_count;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public void setBlog_count(int blog_count) {
        this.blog_count = blog_count;
    }
}
