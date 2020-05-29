package com.example.examsystem.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static final Logger log = LoggerFactory.getLogger(ZipUtil.class);

    public static void createZip(String sourcePath, String zipPath) {
        try (FileOutputStream fos = new FileOutputStream(zipPath); ZipOutputStream zos = new ZipOutputStream(fos)) {
            writeZip(new File(sourcePath), "", zos);
        } catch (IOException e) {
            log.error("创建ZIP文件失败", e);
        }
    }

    private static void writeZip(File file, String parentPath, ZipOutputStream zos) throws IOException {
        if (file.isDirectory()) {//处理文件夹
            parentPath += file.getName() + File.separator;
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {
                for (File f : files) {
                    writeZip(f, parentPath, zos);
                }
            } else { //空目录则创建当前目录
                zos.putNextEntry(new ZipEntry(parentPath));
            }
        } else {
            try (FileInputStream fis = new FileInputStream(file)) {
                ZipEntry ze = new ZipEntry(parentPath + file.getName());
                zos.putNextEntry(ze);
                byte[] content = new byte[1024];
                int len;
                while ((len = fis.read(content)) != -1) {
                    zos.write(content, 0, len);
                    zos.flush();
                }
            } catch (IOException e) {
                log.error("创建ZIP文件失败", e);
            }
        }
    }
}
