package com.example.examsystem.interceptor;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.service.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    ExamServiceImpl examService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println(request.getServletPath());
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        Admin admin = (Admin) session.getAttribute("admin");
        if (request.getServletPath().contains("Login"))
            return true;
        if (request.getServletPath().startsWith("/admin")) {
            if (admin == null)
                response.sendRedirect("/login");
            return admin != null;
        }
        if (request.getServletPath().startsWith("/student")) {
            if (examService.getRunningExam() == null) {
                response.sendRedirect("/login");
                return false;
            }
            if (student == null)
                response.sendRedirect("/login");
            return student != null;
        }
        if (request.getServletPath().startsWith("/teacher")) {
            if (teacher == null)
                response.sendRedirect("/login");
            return teacher != null;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}



