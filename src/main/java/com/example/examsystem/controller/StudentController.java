package com.example.examsystem.controller;

import com.example.examsystem.entity.Exam;
import com.example.examsystem.entity.Setting;
import com.example.examsystem.entity.Student;
import com.example.examsystem.service.ExamServiceImpl;
import com.example.examsystem.service.StudentServiceImpl;
import com.example.examsystem.utils.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    ExamServiceImpl examService;



    @RequestMapping("/studentLogout")
    public String logout(HttpSession session) {
        session.setAttribute("student", null);
        return "redirect:/studentLoginPage";
    }

    @RequestMapping("/studentLoginPage")
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping("/studentMainPage")
    public String mainPage(Model model) {
        Exam exam = examService.getRunningExam();
        model.addAttribute("exam", exam);
        model.addAttribute("type", "main");
        return "student/studentMainPage";
    }
    @RequestMapping("/studentSubmitListPage")
    public String submitListPage(Model model) {
        Exam exam = examService.getRunningExam();
        model.addAttribute("exam", exam);
        model.addAttribute("type", "submitList");
        return "student/studentSubmitListPage";
    }

    @ResponseBody
    @RequestMapping("/downloadExamPaper")
    public String downloadExamPaper(HttpServletResponse response) {
        Exam exam = examService.getRunningExam();
        String filePath = Setting.uploadPath + exam.getName() + "/" + exam.getPaperName();
        File file = new File(filePath);
        if (file.exists()) {
//            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + exam.getPaperName());// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }

    @RequestMapping(value = "/uploadAnswerFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSource(@RequestParam("file") MultipartFile file, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        System.out.println(student.getsClass());
        System.out.println(file);
        String pathString = Setting.uploadPath + "java/" + student.getsClass() + "/" + student.getId() + "/";
        File files = null;
        if (file != null) {
            Path filePath = Paths.get(pathString);
            try {
                if (!Files.exists(filePath))
                    Files.createDirectories(filePath);
                files = new File(filePath.toFile(), Objects.requireNonNull(file.getOriginalFilename()));
                file.transferTo(files);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (files != null)
            return "{\"code\":0,\"msg\":\"" + files.getAbsolutePath() + "\"}";
        else
            return "{\"code\":-1,\"msg\":\"}";
    }

    @ResponseBody
    @RequestMapping("/studentLogin")
    public String login(@RequestParam("id") String id, @RequestParam("name") String name, HttpSession session, Model model) {
        Student student = studentService.login(id, name);
        if (student != null) {
            Exam exam = examService.getRunningExam();
            if (exam == null)
                return "no_exam";
            else
                model.addAttribute("exam", exam);

            System.out.println(student.getIp());
            // ip
            if (NetworkUtil.getLocalHostLANAddress() == null) {
                if ("127.0.0.1".equals(student.getIp()))
                    return "ip_error";
            } else if (!NetworkUtil.getLocalHostLANAddress().getHostAddress().equals(student.getIp()))
                return "ip_error";

            if (student.getIp() == null) {
                if (NetworkUtil.getLocalHostLANAddress() == null)
                    student.setIp("127.0.0.1");
                else
                    student.setIp(NetworkUtil.getLocalHostLANAddress().getHostAddress());
                studentService.updateStudent(student);
            }
            session.setAttribute("student", student);
            return "success";
        }
        return "error";
    }

}
