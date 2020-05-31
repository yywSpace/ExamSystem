package com.example.examsystem.service;

import com.example.examsystem.entity.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getExamLimitBy(int page, int pageSize);

    List<Exam> getTeacherExamLimitBy(int teacherId, int page, int pageSize);

    List<Exam> getAutoStartExamListNotStart();

    Exam getRunningExam();

    Exam getExamById(int id);

    int getExamCount();

    int getTeacherExamCount(int teacherId);

    void insertExam(Exam exam);

    void updateExam(Exam exam);

    void deleteExamById(int id);

    Exam clearExam(int id);
}
