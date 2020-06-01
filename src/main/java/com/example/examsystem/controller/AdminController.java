package com.example.examsystem.controller;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Exam;
import com.example.examsystem.entity.Setting;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.service.*;
import com.example.examsystem.utils.FileUtil;
import com.example.examsystem.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    SettingServiceImpl settingService;

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    ExamServiceImpl examService;
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    StudentExamServiceImpl studentExamService;
    @Autowired
    StudentAnswerServiceImpl studentAnswerService;

    @ResponseBody
    @RequestMapping("/adminLogin")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
        Admin admin = adminService.login(account, password);
        if (admin != null) {
            session.setAttribute("admin", admin);
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = {"/", "/login"})
    public String loginPage(Model model) {
        Exam exam = examService.getRunningExam();
        model.addAttribute("exam", exam);
        return "loginPage";
    }

    @RequestMapping("/adminLogout")
    public String logout(HttpSession session) {
        session.setAttribute("admin", null);
        return "redirect:/login";
    }

    @RequestMapping("/adminExamManagerPage")
    public String examManagerPage(Model model) {
        int adminCount = adminService.getAdminCount();
        model.addAttribute("adminCount", adminCount);
        model.addAttribute("examManager", true);
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "manager/adminExamManage";
    }

    @ResponseBody
    @RequestMapping("/examList")
    public Map<String, Object> examList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<Exam> examList = examService.getExamLimitBy(page, limit);
        int examCount = examService.getExamCount();
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", examCount);
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", examList);
        //返回给前端
        return tableData;
    }

    @ResponseBody
    @RequestMapping("/insertExam")
    public void insertExam(Exam exam) {
        examService.insertExam(exam);
    }

    @ResponseBody
    @RequestMapping("/deleteExam")
    public void deleteExam(int id) {
        examService.deleteExamById(id);
    }

    @ResponseBody
    @RequestMapping("/clearExam")
    public void clearExam(int id) {
        Exam exam = examService.clearExam(id);
        String examPath = Setting.uploadPath + exam.getName();
        // 考试文件数据库信息
        studentAnswerService.deleteStudentAnswerByExamId(exam.getId());
        // 文件
        FileUtil.deleteAll(new File(examPath));
        // 考试与考生
        studentExamService.deleteStudentExamByExamId(exam.getId());
        // ip置空
        studentService.clearIp();
        // msg
        messageService.deleteMessageByExamId(exam.getId());
    }


    @RequestMapping("/adminTeacherListPage")
    public String teacherListPage(Model model) {
        int adminCount = adminService.getAdminCount();
        model.addAttribute("adminCount", adminCount);
        model.addAttribute("teacherManager", true);
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "manager/adminTeacherManage";
    }

    @RequestMapping("/adminExamSettingPage")
    public String examSettingPage(Model model) {
        int adminCount = adminService.getAdminCount();
        model.addAttribute("adminCount", adminCount);
        model.addAttribute("setting", settingService.getSetting());
        return "manager/adminExamSetting";
    }

    @RequestMapping(value = {"/adminInfoPage", "/admin"})
    public String adminInfoPage(Model model) {
        int adminCount = adminService.getAdminCount();
        model.addAttribute("adminCount", adminCount);
        System.out.println(adminCount);
        if (adminCount == 0) {
            Admin admin = new Admin();
            admin.setName("admin");
            admin.setPassword("admin");
            model.addAttribute("admin", admin);
        } else {
            // 在登录时将信息存入session
            model.addAttribute("admin", adminService.getAdminList().get(0));
        }

        return "manager/adminModifyMessage";
    }

    @ResponseBody
    @RequestMapping("/updateAdmin")
    public void updateAdmin(Admin admin, HttpSession session) {
        admin.setPassword(PasswordUtil.getMD5(admin.getPassword()));
        if (admin.getId() == 0) {
            adminService.insertAdmin(admin);
        } else
            adminService.updateAdmin(admin);
        session.setAttribute("admin", admin);
    }

    @ResponseBody
    @RequestMapping("/resetSetting")
    public void resetSetting(int id) {
        settingService.restoreToDefault(id);
    }


    @RequestMapping("/adminDeleteTeacher")
    public void deleteTeacher(@RequestParam("id") int id) {
        teacherService.deleteTeacherById(id);
    }

    @ResponseBody
    @RequestMapping("/adminInsertTeacher")
    public void insertTeacher(Teacher teacher) {
        System.out.println(teacher.getName());
        teacher.setPassword(PasswordUtil.getMD5(teacher.getPassword()));
        if (teacher.isManager()) {
            Admin admin = new Admin();
            admin.setName(teacher.getName());
            admin.setPassword(teacher.getPassword());
            adminService.insertAdmin(admin);
        }
        teacherService.insertTeacher(teacher);
    }

    @ResponseBody
    @RequestMapping("/updateTeacher")
    public void updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
    }

    @ResponseBody
    @RequestMapping("/updateSetting")
    public void updateSetting(Setting setting) {
        settingService.updateSetting(setting);
    }

    @ResponseBody
    @RequestMapping("/adminTeacherList")
    public Map<String, Object> teacherList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<Teacher> teacherList = teacherService.getTeacherLimitBy(page, limit);
        int teacherCount = teacherService.getTeacherCount();
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", teacherCount);
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", teacherList);
        //返回给前端
        return tableData;
    }
}
