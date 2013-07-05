package com.wjc.demo.fetchbook.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: wei_jc
 * Date: 13-7-5
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class ImageUtil {
    public static byte[] zoomImage(File imageFile, double percernt) throws IOException {
        Image image = ImageIO.read(imageFile);
        int width = (int) (image.getWidth(null) * percernt);
        int height = (int) (image.getHeight(null) * percernt);
        width = 600;
        height = 600;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().drawImage(image, 0, 0, width, height, null);
        File baseDir = imageFile.getParentFile();
        ImageIO.write(bi, "JPEG", new File(baseDir, "test.jpg"));


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "JPEG", os);
        return os.toByteArray();
    }

    public static byte[] zoomImage(File imageFile, int width, int height) throws IOException {
        Image image = ImageIO.read(imageFile);

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().drawImage(image, 0, 0, width, height, null);
        File baseDir = imageFile.getParentFile();
        ImageIO.write(bi, "JPEG", new File(baseDir, "test.jpg"));


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "JPEG", os);
        return os.toByteArray();
    }

    public static byte[] zoomImage(File imageFile, int width, int height, int minBytes) throws IOException {
        byte[] bytes = zoomImage(imageFile, width, height);
        while (bytes.length < minBytes) {
            bytes = zoomImage(imageFile, width + 50, height + 50);
        }

        return bytes;
    }

    public static void main(String[] args) throws IOException {
        File imageFile = new File("D:\\pictures\\2013-07-04\\3145920.jpg");
        zoomImage(imageFile, 400, 400, 20 * 1024);

        String[] strs = ImageIO.getReaderFormatNames();
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
