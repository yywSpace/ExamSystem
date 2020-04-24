package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.mapper.AdminMapper;
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
        List<Admin> adminList = adminMapper.getAdminList();
        if (adminList.size() == 0) {
            if (name.equals("admin") && password.equals("admin")) {
                Admin admin = new Admin();
                admin.setName("admin");
                admin.setPassword("admin");
                return admin;
            } else
                return null;
        }
        Optional<Admin> adminOptional = adminList
                .stream()
                .filter(admin -> admin.getName().equals(name))
                .findFirst();
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (admin.getPassword().equals(password))
                return admin;
        }
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
}
