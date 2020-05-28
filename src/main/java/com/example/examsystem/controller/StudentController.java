package com.example.examsystem.controller;

import com.example.examsystem.entity.Exam;
import com.example.examsystem.entity.Setting;
import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentAnswer;
import com.example.examsystem.service.ExamServiceImpl;
import com.example.examsystem.service.SettingServiceImpl;
import com.example.examsystem.service.StudentAnswerServiceImpl;
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
import java.util.*;

@Controller
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    ExamServiceImpl examService;
    @Autowired
    StudentAnswerServiceImpl studentAnswerService;
    @Autowired
    SettingServiceImpl settingService;

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
    public String submitListPage(Model model, HttpSession session) {
        Exam exam = examService.getRunningExam();
        Student student = (Student) session.getAttribute("student");
        Setting setting = settingService.getSetting();
        List<StudentAnswer> studentAnswers = studentAnswerService.getStudentAnswers(student.getId());
        model.addAttribute("fileSizeStatus", "");
        for (StudentAnswer studentAnswer :
                studentAnswers) {
            if (studentAnswer.getAnswerFileSize() > setting.getUploadBytesUpper()) {
                model.addAttribute("fileSizeStatus", String.format("上传文件大小超出规定的最大值(%d),请检查所提交的文件", setting.getUploadBytesUpper()));
                break;
            }
            if (studentAnswer.getAnswerFileSize() <= setting.getUploadBytesLower()) {
                model.addAttribute("fileSizeStatus", String.format("上传文件大小小于或等于规定的最小值(%d),请检查所提交的文件", setting.getUploadBytesLower()));
                break;
            }
        }
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        model.addAttribute("exam", exam);
        model.addAttribute("type", "submitList");
        return "student/studentSubmitListPage";
    }

    @RequestMapping("/deleteAnswerFile")
    public void deleteTeacher(@RequestParam("id") int id, HttpSession session) throws IOException {
        StudentAnswer studentAnswer = studentAnswerService.getStudentAnswerById(id);
        Exam exam = examService.getRunningExam();
        Student student = (Student) session.getAttribute("student");
        String pathString = Setting.uploadPath + exam.getName() + File.separator +
                student.getsClass() + File.separator + student.getId() + File.separator + studentAnswer.getAnswerFileName();
        Files.deleteIfExists(Paths.get(pathString));
        studentAnswerService.deleteStudentAnswerById(id);
    }

    @ResponseBody
    @RequestMapping("/studentSubmitList")
    public Map<String, Object> submitList(HttpSession session, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Student student = (Student) session.getAttribute("student");
        List<StudentAnswer> studentAnswers = studentAnswerService.getStudentAnswerLimitBy(student.getId(), page, limit);
        int studentAnswerCount = studentAnswerService.getStudentAnswerCount(student.getId());
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", studentAnswerCount);
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", studentAnswers);
        //返回给前端
        return tableData;
    }

    @ResponseBody
    @RequestMapping("/downloadExamPaper")
    public String downloadExamPaper(HttpServletResponse response) {
        Exam exam = examService.getRunningExam();
        String filePath = Setting.uploadPath + exam.getName() + File.separator + exam.getPaperName();
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
        Exam exam = examService.getRunningExam();
        Student student = (Student) session.getAttribute("student");
        System.out.println(student.getsClass());
        System.out.println(file);
        String pathString = Setting.uploadPath + exam.getName() + File.separator + student.getsClass() + File.separator + student.getId() + File.separator;
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

        if (files != null) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setStudentId(student.getId());
            studentAnswer.setExamId(exam.getId());
            studentAnswer.setAnswerFileName(files.getName());
            studentAnswer.setAnswerFileSize((int) files.length());
            studentAnswer.setAnswerFileTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            studentAnswerService.insertStudentAnswer(studentAnswer);
            return "{\"code\":0,\"msg\":\"" + files.getAbsolutePath() + "\"}";
        } else
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

            if (student.getIp() == null || student.getIp().equals("")) {
                if (NetworkUtil.getLocalHostLANAddress() == null)
                    student.setIp("127.0.0.1");
                else
                    student.setIp(NetworkUtil.getLocalHostLANAddress().getHostAddress());
                studentService.updateStudent(student);
            }

            // ip
            if (NetworkUtil.getLocalHostLANAddress() == null) {
                if (!"127.0.0.1".equals(student.getIp()))
                    return "ip_error";
            } else if (!NetworkUtil.getLocalHostLANAddress().getHostAddress().equals(student.getIp()))
                return "ip_error";


            session.setAttribute("student", student);
            return "success";
        }
        return "error";
    }

}
