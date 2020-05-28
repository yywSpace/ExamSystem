package com.example.examsystem.service;

import com.example.examsystem.entity.StudentAnswer;

import java.util.List;

public interface StudentAnswerService {
    List<StudentAnswer> getStudentAnswerLimitBy(String studentId,int page, int pageSize);

    int getStudentAnswerCount(String studentId);

    List<StudentAnswer> getStudentAnswers(String studentId);

    void insertStudentAnswer(StudentAnswer studentAnswer);

    void updateStudentAnswer(StudentAnswer studentAnswer);

    void deleteStudentAnswerByExamId(int examId);

    void deleteStudentAnswerById(int id);
    StudentAnswer getStudentAnswerById(int id);
}
