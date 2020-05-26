package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String password) {
        int adminCount = adminMapper.getAdminCount();
        if (adminCount == 0) {
            if (name.equals("admin") && password.equals("admin")) {
                Admin admin = new Admin();
                admin.setName("admin");
                admin.setPassword("admin");
                return admin;
            } else
                return null;
        }

        Admin admin = adminMapper.getAdminByName(name);
        if (admin.getPassword().equals(PasswordUtil.getMD5(password)))
            return admin;
        return null;
    }

    @Override
    public void insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(Admin admin) {
        adminMapper.deleteAdminById(admin.getId());
    }

    @Override
    public int getAdminCount() {
        return adminMapper.getAdminCount();
    }

    @Override
    public List<Admin> getAdminList() {
        return adminMapper.getAdminList();
    }
}
