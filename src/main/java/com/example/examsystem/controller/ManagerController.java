package com.example.examsystem.controller;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManagerController {
    Admin admin;

    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("/background")
    public String background() {
        return "manager/background";
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
        Admin admin = adminMapper.getAdminById(this.admin.getId());
        return admin.toString();
    }
}
