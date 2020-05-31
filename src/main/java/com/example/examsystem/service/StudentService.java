package com.example.examsystem.service;

import com.example.examsystem.entity.Student;

import java.util.List;

public interface StudentService {
    Student login(String name, String password);

    void insertStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(String studentId);

    int getStudentCount();

    Student getStudentById(String id);

    List<Student> getStudentList();

    void clearIp();
}
