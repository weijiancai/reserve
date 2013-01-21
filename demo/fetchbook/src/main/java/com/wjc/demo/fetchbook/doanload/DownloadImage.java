package com.wjc.demo.fetchbook.doanload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * 下载图片
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class DownloadImage {
    /**
     * 下载图片所存放的基准目录
     */
    public static String BASE_DIR = "D:/pictures";

    private URL[] urls;

    public DownloadImage(URL[] urls) {
        this.urls = urls;

        down();
    }

    /**
     * 下载所有图片
     */
    private void down() {
        for (URL url : urls) {
            down(url);
        }
    }

    /**
     * 下载单个图片
     *
     * @param url 图片url
     */
    public void down(URL url) {
        // 取当前日期
        String date = String.format("%tF", new Date());
        File dateDir = new File(BASE_DIR, date);
        if (!dateDir.exists()) {
            dateDir.mkdirs();
        }
        try {
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            String urlStr = url.toString();
            FileOutputStream fos = new FileOutputStream(new File(dateDir, urlStr.substring(urlStr.lastIndexOf("/"))));
            int i = 0;
            while((i = is.read()) != -1) {
                fos.write(i);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
