package com.example.examsystem.controller;

import com.example.examsystem.entity.Exam;
import com.example.examsystem.entity.Setting;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.service.ExamServiceImpl;
import com.example.examsystem.service.SettingServiceImpl;
import com.example.examsystem.service.TeacherServiceImpl;
import com.example.examsystem.utils.FileUtil;
import com.example.examsystem.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    ExamServiceImpl examService;

    @Autowired
    SettingServiceImpl settingService;

    @ResponseBody
    @RequestMapping("/teacherLogin")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
        Teacher teacher = teacherService.login(account, password);

        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            return "success";
        }
        return "error";
    }

    @RequestMapping("/teacherLogout")
    public String logout(HttpSession session) {
        session.setAttribute("teacher", null);
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping("/archiveExam")
    public String archiveExam(int id, HttpServletResponse response) {
        Exam exam = examService.getExamById(id);
        System.out.println(exam);
        String path = Setting.uploadPath + exam.getName();
        String zipPath = Setting.uploadPath + "zip" + File.separator + exam.getName() + ".zip";
        ZipUtil.createZip(path, zipPath);
        File zipFile = new File(zipPath);
        exam.setArchived(true);
        examService.updateExam(exam);
        System.out.println(exam);
        return FileUtil.download(zipFile, response);
    }

    @ResponseBody
    @RequestMapping("/teacherInsertExam")
    public void insertExam(Exam exam, HttpSession session) throws IOException {
        System.out.println(exam.getName() + "" + exam.getStartTime() + "" + exam.getAutoStart());
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        exam.setTeacherId(teacher.getId());
        String folder = Setting.uploadPath + exam.getName();
        Path path = Paths.get(folder);
        Files.createDirectories(path);
        examService.insertExam(exam);
    }

    @RequestMapping("/teacherMainPage")
    public String mainPage(Model model) {
        model.addAttribute("type", "main");
        return "teacher/teacherMainPage";
    }

    @RequestMapping("/teacherExamBeforePage")
    public String beforePage(Model model) {
        int examCount = examService.getExamCount();
        model.addAttribute("adminCount", examCount);
        model.addAttribute("allow", settingService.getSetting().isAllowClearAndDelete());
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        model.addAttribute("type", "before");
        return "teacher/teacherExamBeforePage";
    }

    @RequestMapping("/teacherModifyExamInfoPage")
    public String modifyExamInfo(int id, Model model) {
        Exam exam = examService.getExamById(id);
        model.addAttribute("exam", exam);
        model.addAttribute("type", "before");
        return "teacher/teacherModifyExamInfoPage";
    }

    @RequestMapping("/teacherExamAfterPage")
    public String afterPage(Model model) {
        int examCount = examService.getExamCount();
        model.addAttribute("adminCount", examCount);
        model.addAttribute("allow", settingService.getSetting().isAllowClearAndDelete());
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        model.addAttribute("type", "after");
        return "teacher/teacherExamAfterPage";
    }
}
