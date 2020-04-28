package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin login(String name, String password);

    void insertAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(Admin admin);

    int getAdminCount();

    List<Admin> getAdminList();
}
