package com.example.examsystem.service;

import com.example.examsystem.entity.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getExamLimitBy(int page, int pageSize);

    Exam getRunningExam();

    Exam getExamById(int id);

    int getExamCount();

    void insertExam(Exam exam);

    void updateExam(Exam exam);

    void deleteExamById(int id);

    Exam clearExam(int id);
}
