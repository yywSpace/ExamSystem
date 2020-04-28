package com.example.examsystem.controller;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Setting;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.AdminServiceImpl;
import com.example.examsystem.service.SettingServiceImpl;
import com.example.examsystem.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    SettingServiceImpl settingService;

    @Autowired
    AdminServiceImpl adminService;

    @RequestMapping("/background")
    public String background() {
        return "manager/background";
    }

    @RequestMapping("/teacherListPage")
    public String teacherListPage(Model model) {
        int adminCount = adminService.getAdminCount();
        model.addAttribute("adminCount", adminCount);
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "manager/adminTeacherManager";
    }

    @RequestMapping("/examSettingPage")
    public String examSettingPage(Model model) {
        int adminCount = adminService.getAdminCount();
        model.addAttribute("adminCount", adminCount);
        model.addAttribute("setting", settingService.getSetting());
        return "manager/adminExamSetting";
    }

    @RequestMapping("/adminInfoPage")
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
    public void updateAdmin(Admin admin) {
        if (admin.getId() == 0) {
            adminService.insertAdmin(admin);
        } else
            adminService.updateAdmin(admin);
    }

    @ResponseBody
    @RequestMapping("/resetSetting")
    public void resetSetting(int id) {
        settingService.restoreToDefault(id);
    }


    @RequestMapping("/deleteTeacher")
    public void deleteTeacher(@RequestParam("id") int id) {
        teacherService.deleteTeacherById(id);
    }

    @ResponseBody
    @RequestMapping("/insertTeacher")
    public void insertTeacher(Teacher teacher) {
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
    @RequestMapping("/teacherList")
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
