package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.StudentExam;

import java.util.List;

public interface StudentExamService {

    void insertStudentExam(StudentExam studentExam);

    void updateStudentExam(StudentExam StudentExam);

    void deleteStudentExamByExamId(int id);

    int getStudentExamCount(int examId);

    List<StudentExam> getStudentExamList(int examId);
}
