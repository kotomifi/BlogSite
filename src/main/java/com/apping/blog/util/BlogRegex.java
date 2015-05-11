package com.apping.blog.util;

import java.util.regex.Pattern;

/**
 * Created by baisu on 15-4-10.
 */
public class BlogRegex {

    public final static Pattern dateReg = Pattern.compile(
        "(date)(\\s)*", Pattern.CASE_INSENSITIVE
    );

    public final static Pattern titleReg = Pattern.compile(
            "(title)(\\s)*", Pattern.CASE_INSENSITIVE
    );

    public final static Pattern authorReg = Pattern.compile(
            "(author)(\\s)*", Pattern.CASE_INSENSITIVE
    );

    public final static Pattern tagsReg = Pattern.compile(
            "(tags)(\\s)*", Pattern.CASE_INSENSITIVE
    );

    public final static Pattern abstractReg = Pattern.compile(
            "(abstract)(\\s)*", Pattern.CASE_INSENSITIVE
    );
}
