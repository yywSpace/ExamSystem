package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;

public interface AdminService {
    Admin login(String name, String password);

    void insertAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(Admin admin);
}
