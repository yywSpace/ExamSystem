package com.example.examsystem.service;

import com.example.examsystem.entity.Student;

import java.util.List;

public interface StudentService {
    Student login(String name, String password);

    void insertStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Student student);

    int getStudentCount();

    List<Student> getStudentList();
}
