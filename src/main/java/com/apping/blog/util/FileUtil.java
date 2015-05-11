package com.apping.blog.util;

import com.apping.blog.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;

/**
 * Created by baisu on 15-4-9.
 */
public class FileUtil {

    /**
     * 获取文件最后修改时间
     * @param file
     * @return
     */
    public static long fileModifiedTime(File file) {
        Calendar cal = Calendar.getInstance();
        long time = file.lastModified();
        return time;
    }

    /**
     * 同步img文件夹
     */
    public static void syncImgDir() {
        File sourceDir = new File(Config.BLOG_IMG_PATH);
        File targetDir = new File(Config.PARSED_BLOG_IMG_PATH);

        File[] imgFiles = sourceDir.listFiles();
        File[] cImgFiles = targetDir.listFiles();

        for (File img : imgFiles) {
            File temp = new File(Config.PARSED_BLOG_IMG_PATH + "/" + img.getName());
            if (!temp.exists()) {
                copyFile(img, temp);
            }
        }
    }

    /**
     * 复制文件
     * @param source
     * @param target
     */
    public static void copyFile(File source , File target) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fcin = null;
        FileChannel fcout = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);

            fcin = fis.getChannel();
            fcout= fos.getChannel();

            fcin.transferTo(0, fcin.size(), fcout);

        } catch (Exception e) {

        } finally {
            try {
                fis.close();
                fcin.close();
                fos.close();
                fcout.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
