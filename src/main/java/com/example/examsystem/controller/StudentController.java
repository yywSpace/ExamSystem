package com.example.examsystem.controller;

import com.example.examsystem.entity.*;
import com.example.examsystem.service.*;
import com.example.examsystem.utils.FileUtil;
import com.example.examsystem.utils.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.ibatis.annotations.Param;
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
    @Autowired
    StudentExamServiceImpl studentExamService;
    @Autowired
    MessageServiceImpl messageService;

    @RequestMapping("/studentLogout")
    public String logout(HttpSession session) {
        session.setAttribute("student", null);
        return "redirect:/login";
    }

    @RequestMapping("/studentMainPage")
    public String mainPage(Model model) {
        Exam exam = examService.getRunningExam();
        model.addAttribute("exam", exam);
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
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

    @ResponseBody
    @RequestMapping("/studentDeleteAnswerFile")
    public void deleteTeacher(@RequestParam("id") int id, HttpSession session) {
        StudentAnswer studentAnswer = studentAnswerService.getStudentAnswerById(id);
        Exam exam = examService.getRunningExam();
        Student student = (Student) session.getAttribute("student");
        String pathString = Setting.uploadPath + exam.getName() + File.separator +
                student.getsClass() + File.separator + student.getId() + File.separator + studentAnswer.getAnswerFileName();
        System.out.println(pathString);
        try {
            Files.deleteIfExists(Paths.get(pathString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentAnswerService.deleteStudentAnswerById(id);
    }

    @ResponseBody
    @RequestMapping("/studentSubmitList")
    public Map<String, Object> submitList(HttpSession session, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Student student = (Student) session.getAttribute("student");
        List<StudentAnswer> studentAnswers = studentAnswerService.getStudentAnswerLimitBy(student.getId(), page, limit);
        System.out.println(studentAnswers);
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
    @RequestMapping("/studentDownloadExamPaper")
    public String downloadExamPaper(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        Exam exam = examService.getRunningExam();
        String filePath = Setting.uploadPath + exam.getName() + File.separator + exam.getPaperName();
        return FileUtil.download(new File(filePath), response, request);
    }

    @RequestMapping(value = "/studentUploadAnswerFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> uploadSource(@RequestParam("file") MultipartFile file, HttpSession session) {
        Map<String ,Object> map = new HashMap<>();
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
            StudentAnswer studentAnswer = studentAnswerService.getStudentAnswerByFileName(student.getId(), files.getName());
            if (studentAnswer == null)
                studentAnswer = new StudentAnswer();
            studentAnswer.setStudentId(student.getId());
            studentAnswer.setExamId(exam.getId());
            studentAnswer.setAnswerFileName(files.getName());
            studentAnswer.setAnswerFileSize((int) files.length());
            studentAnswer.setAnswerFileTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if (studentAnswer.getId() == 0)
                studentAnswerService.insertStudentAnswer(studentAnswer);
            else
                studentAnswerService.updateStudentAnswer(studentAnswer);
            map.put("code",0);
            map.put("msg",files.getAbsolutePath());
            return map;
        } else {
            map.put("code",-1);
            map.put("msg","");
            return map;
        }

    }

    @ResponseBody
    @RequestMapping("/studentLogin")
    public String login(@RequestParam("id") String id, @RequestParam("name") String name, HttpSession session, Model model) {
        Student student = studentService.login(id, name);
        if (student != null) {
            Exam exam = examService.getRunningExam();
            System.out.println(exam);
            if (exam == null)
                return "no_exam";
            else
                model.addAttribute("exam", exam);
            StudentExam studentExam = studentExamService.getStudentExamById(id,exam.getId());

            if (studentExam == null) {
                return "not_in_this_exam";
            }

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

            studentExam.setLogin(true);
            studentExamService.updateStudentExam(studentExam);
            session.setAttribute("student", student);
            return "success";
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping("/refreshMessage")
    public String refreshMessage(int examId, HttpSession session) {
        Integer messageCount = (Integer) session.getAttribute("messageCount");
        System.out.println(messageCount);
        if (messageCount == null) {
            messageCount = messageService.getMessageCount(examId);
            session.setAttribute("messageCount", messageCount);
            return "refresh";
        } else {
            if (messageCount != messageService.getMessageCount(examId)) {
                session.setAttribute("messageCount", messageService.getMessageCount(examId));
                return "refresh";
            }
        }
        return "don't refresh";
    }


}
