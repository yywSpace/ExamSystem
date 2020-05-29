package com.example.examsystem.service;

import com.example.examsystem.entity.StudentExam;
import com.example.examsystem.mapper.StudentExamMapper;
import com.example.examsystem.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentExamServiceImpl implements StudentExamService {
    @Autowired
    StudentExamMapper studentExamMapper;


    @Override
    public void insertStudentExam(StudentExam studentExam) {
        studentExamMapper.insert(studentExam);
    }

    @Override
    public void updateStudentExam(StudentExam StudentExam) {

    }

    @Override
    public void deleteStudentExamByExamId(int examId) {
        studentExamMapper.deleteByExamId(examId);
    }

    @Override
    public int getStudentExamCount(int examId) {
        return studentExamMapper.getStudentExamCount(examId);
    }

    @Override
    public List<StudentExam> getStudentExamList(int examId) {
        return null;
    }
}
