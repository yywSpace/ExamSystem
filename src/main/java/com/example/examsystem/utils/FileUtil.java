package com.example.examsystem.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static String download(File file, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println(file.getName());
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
            response.setContentLength((int) file.length());
            String clientType = request.getHeader("User-Agent");
            System.out.println(clientType);
            String fileName = file.getName();
            if (clientType.contains("Firefox")) {
                fileName = base64EncodeFileName(fileName);
            } else {
                //IE ，或者  Chrome （谷歌浏览器） ，
                //对中文的名字进行编码处理
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
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

    private static String base64EncodeFileName(String fileName) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return "=?UTF-8?B?"
                + base64Encoder.encode(fileName
                .getBytes(StandardCharsets.UTF_8)) + "?=";
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

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        InputStream ins = file.getInputStream();
        File toFile = new File(file.getOriginalFilename());
        inputStreamToFile(ins, toFile);
        ins.close();
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }
}
