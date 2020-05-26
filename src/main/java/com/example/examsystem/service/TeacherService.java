package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher login(String name, String password);

    List<Teacher> getTeacherLimitBy(int page, int pageSize);

    int getTeacherCount();

    void insertTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);
    void deleteTeacherById(int id);

}
