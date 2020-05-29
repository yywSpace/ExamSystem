package com.example.examsystem.utils;

import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {
    public static String download(File file, HttpServletResponse response) {
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
            response.setContentLength((int) file.length());
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
            byte[] buffer = new byte[1024];

            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "下载失败";
    }

    public static void deleteAll(File file) {
        System.out.println(file.getName());
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                deleteAll(subFile);
            }
        }
        file.delete();
    }
}
