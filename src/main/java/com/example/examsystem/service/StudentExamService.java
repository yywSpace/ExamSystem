package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentExam;

import java.util.List;

public interface StudentExamService {

    void insertStudentExam(StudentExam studentExam);

    void updateStudentExam(StudentExam StudentExam);

    void deleteStudentExamByExamId(int id);

    void deleteByExamIdAndStudentId(int examId, String id);

    int getStudentExamCount(int examId);

    List<Student> getStudentExamListLimitBy(int examId, int page, int pageSize);
}
