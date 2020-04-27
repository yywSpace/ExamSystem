package com.example.examsystem.controller;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    Admin admin;
    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("/background")
    public String background() {
        return "manager/background";
    }

    @RequestMapping("/teacherListPage")
    public String teacherListPage() {
        return "manager/adminTeacherManager";
    }

    @RequestMapping("/teacherAddPage")
    public String teacherAddPage() {
        return "manager/adminTeacherAdd";
    }

    @RequestMapping("/deleteTeacher")
    public void deleteTeacher(@RequestParam("id") int id) {
        teacherService.deleteTeacherById(id);
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


    @ResponseBody
    @RequestMapping("/insertAdmin")
    public void insertTest() {
        admin = new Admin();
        admin.setName("134");
        admin.setPassword("13");
        adminMapper.insertAdmin(admin);
    }

    @ResponseBody
    @RequestMapping("/updateAdmin")
    public void updateTest() {
        admin.setName("1111");
        admin.setPassword("1111");
        adminMapper.updateAdmin(admin);
    }

    @ResponseBody
    @RequestMapping("/deleteAdmin")
    public void deleteTest() {
        adminMapper.deleteAdminById(admin.getId());
    }

    @ResponseBody
    @RequestMapping("/selectAdmin")
    public String selectTest() {
        Admin admin = adminMapper.getAdminByName(this.admin.getName());
        return admin.toString();
    }
}
