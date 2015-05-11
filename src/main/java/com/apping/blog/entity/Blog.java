package com.apping.blog.entity;

import java.util.Date;

/**
 * Created by baisu on 15-3-28.
 */
public class Blog {
    private long blog_id;
    private String blog_file;
    private String blog_title;
    private String blog_author;
    private Date blog_date;
    private String blog_abstract;
    private long blog_modified;

    public void setBlog_modified(long blog_modified) {
        this.blog_modified = blog_modified;
    }

    public long getBlog_modified() {

        return blog_modified;
    }

    public long getBlog_id() {
        return blog_id;
    }

    public String getBlog_file() {
        return blog_file;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public String getBlog_author() {
        return blog_author;
    }

    public String getBlog_abstract() {
        return blog_abstract;
    }

    public Date getBlog_date() {
        return blog_date;
    }

    public void setBlog_id(long blog_id) {
        this.blog_id = blog_id;
    }

    public void setBlog_file(String blog_file) {
        this.blog_file = blog_file;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public void setBlog_author(String blog_author) {
        this.blog_author = blog_author;
    }

    public void setBlog_abstract(String blog_abstract) {
        this.blog_abstract = blog_abstract;
    }

    public void setBlog_date(Date blog_date) {
        this.blog_date = blog_date;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blog_id='" + blog_id + '\'' +
                ", blog_file='" + blog_file + '\'' +
                ", blog_title='" + blog_title + '\'' +
                ", blog_author='" + blog_author + '\'' +
                ", blog_date=" + blog_date +
                ", blog_abstract='" + blog_abstract + '\'' +
                '}';
    }
}
