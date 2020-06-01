package com.example.examsystem.service;

import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentAnswer;

import java.util.List;

public interface StudentAnswerService {
    List<StudentAnswer> getStudentAnswerLimitBy(String studentId, int page, int pageSize);

    int getStudentAnswerCount(String studentId);

    int getStudentAnswerCountByExamId(int examId);

    List<StudentAnswer> getStudentAnswers(String studentId);

    void insertStudentAnswer(StudentAnswer studentAnswer);

    void updateStudentAnswer(StudentAnswer studentAnswer);

    void deleteStudentAnswerByExamId(int examId);

    void deleteStudentAnswerById(int id);

    StudentAnswer getStudentAnswerById(int id);

    StudentAnswer getStudentAnswerByFileName(String studentId, String fileName);

    List<Student> getUploadStudents(int examId);

    List<Student> getUnUploadStudents(int examId);
}
