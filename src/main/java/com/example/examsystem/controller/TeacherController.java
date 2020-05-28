package com.example.examsystem.controller;

import com.example.examsystem.entity.Exam;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.service.ExamServiceImpl;
import com.example.examsystem.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    ExamServiceImpl examService;

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
        return "redirect:/teacherLoginPage";
    }

    @RequestMapping("/teacherMainPage")
    public String mainPage() {
        return "teacher/framework";
    }
}
