package com.apping.blog;

/**
 * Created by baisu on 15-3-28.
 */
public class Config {
    /** 每次加载最新博客数量 */
    public static final int BLOG_LIMIT_NUM = 5;

    /** 服务器端存放 blog 版本库的绝对路径 */
    public static final String BLOG_PAHT = "/home/baisu/Desktop/blog";

//    public static final String BLOG_PAHT = "/root/blog";

    /** markdown 文件存放路劲 */
    public static final String BLOG_FILE_PATH = BLOG_PAHT + "/post";

    /** 图片文件存放路劲 */
    public static final String BLOG_IMG_PATH = BLOG_PAHT + "/img";

    /** 服务器端存放 解析文件的绝对路径 */
    //public static final String PARSED_BLOG_PAHT = "/home/baisu/Desktop/BlogApp/src/main/webapp/blog";

    public static final String PARSED_BLOG_PAHT = "/usr/local/apache-tomcat-7.0.61/webapps/Blog/blog";

    /** markdown解析后文件存放路劲 */
    public static final String PARSE_BLOG_FILE_PATH = PARSED_BLOG_PAHT + "/post";

    /** 图片文件复制后存放路劲 */
    public static final String PARSED_BLOG_IMG_PATH = PARSED_BLOG_PAHT + "/img";

    /** MD 文件 Author属性 */
    public static final String MD_AUTHOR = "Author";

    /** MD 文件 Title属性 */
    public static final String MD_TITLE = "Title";

    /** MD 文件 Date属性 */
    public static final String MD_DATE = "Date";

    /** MD 文件 Abstract属性 */
    public static final String MD_ABSTRACT = "Abstract";

    /** MD 文件 Tags属性 */
    public static final String MD_TAGS = "Tags";

    /** 本地git仓库路劲 */
    public static final String LOCAL_PATH = "/home/baisu/Desktop/blog";

//    public static final String LOCAL_PATH = "/root/blog";

    /** 远程git仓库URL */
    public static final String REMOTE_PATH = "https://github.com/kotomifi/blog.git";
}
