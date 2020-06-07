package com.example.examsystem.utils;

import com.example.examsystem.entity.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static List<Student> getStudentByExcel(File file, String sheetName) {
        List<Student> students = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file);
             XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream)) {
            XSSFSheet sheet = sheets.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows; i++) {
                //获取列数
                XSSFRow row = sheet.getRow(i);
                if (row == null)
                    break;
                if(row.getCell(0) == null)
                    break;
                students.add(new Student(
                        row.getCell(0).toString(),
                        row.getCell(1).toString(),
                        row.getCell(2).toString()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

}
