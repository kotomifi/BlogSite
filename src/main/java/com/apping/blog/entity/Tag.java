package com.apping.blog.entity;

/**
 * Created by baisu on 15-3-28.
 */
public class Tag {
    private String tag_name;
    private long blog_id;

    public Tag() {

    }

    public Tag(String tag_name, long blog_id) {
        this.tag_name = tag_name;
        this.blog_id = blog_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public long getBlog_id() {
        return blog_id;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public void setBlog_id(long blog_id) {
        this.blog_id = blog_id;
    }

}
