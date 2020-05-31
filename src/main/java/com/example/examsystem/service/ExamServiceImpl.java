package com.example.examsystem.service;

import com.example.examsystem.entity.Exam;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamMapper examMapper;

    @Override
    public List<Exam> getExamLimitBy(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return examMapper.getExamLimitBy(start, pageSize);
    }

    @Override
    public List<Exam> getTeacherExamLimitBy(int teacherId, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return examMapper.getTeacherExamLimitBy(teacherId, start, pageSize);
    }

    @Override
    public List<Exam> getAutoStartExamListNotStart() {
        return examMapper.getAutoStartExamListNotStart();
    }

    @Override
    public Exam getRunningExam() {
        if (examMapper.getRunningExam().size() == 0)
            return null;
        return examMapper.getRunningExam().get(0);
    }

    @Override
    public Exam getExamById(int id) {
        return examMapper.getExamById(id);
    }

    @Override
    public int getExamCount() {
        return examMapper.getExamCount();
    }
    @Override
    public int getTeacherExamCount(int teacherId) {
        return examMapper.getTeacherExamCount(teacherId);
    }

    @Override
    public void insertExam(Exam exam) {
        examMapper.insertExam(exam);
    }

    @Override
    public void updateExam(Exam exam) {
        examMapper.updateExam(exam);
    }

    @Override
    public void deleteExamById(int id) {
        examMapper.deleteExamById(id);
    }

    @Override
    public Exam clearExam(int id) {
        Exam exam = examMapper.getExamById(id);
        exam.setCleaned(true);
        examMapper.updateExam(exam);
        return exam;
    }
}
