package com.example.examsystem.service;

import com.example.examsystem.entity.Student;
import com.example.examsystem.mapper.StudentMapper;
import com.example.examsystem.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student login(String id, String name) {
        Student student = studentMapper.getStudentById(id);
        if (student == null)
            return null;
        if (student.getName().equals(name))
            return student;
        return null;
    }

    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudentById(String studentId) {
        studentMapper.deleteStudentById(studentId);
    }

    @Override
    public int getStudentCount() {
        return studentMapper.getStudentCount();
    }

    @Override
    public Student getStudentById(String id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentMapper.getStudentList();
    }

    @Override
    public void clearIp() {
        studentMapper.clearIp();
    }
}
