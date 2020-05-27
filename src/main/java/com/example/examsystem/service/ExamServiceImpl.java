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
    public Exam getRunningExam() {
        if (examMapper.getRunningExam().size() == 0)
            return null;
        return examMapper.getRunningExam().get(0);
    }

    @Override
    public int getExamCount() {
        return examMapper.getExamCount();
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
    public void clearExam(int id) {
        // TODO: 2020/5/12 做清理工作

        Exam exam = examMapper.getExamById(id);
        exam.setCleaned(true);
        examMapper.updateExam(exam);
    }
}
