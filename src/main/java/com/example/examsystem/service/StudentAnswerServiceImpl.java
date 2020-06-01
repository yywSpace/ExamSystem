package com.example.examsystem.service;

import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentAnswer;
import com.example.examsystem.mapper.ExamMapper;
import com.example.examsystem.mapper.StudentAnswerMapper;
import com.example.examsystem.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {
    @Autowired
    StudentAnswerMapper studentAnswerMapper;
    @Autowired
    ExamServiceImpl examService;

    @Override
    public List<StudentAnswer> getStudentAnswerLimitBy(String studentId, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return studentAnswerMapper.getStudentAnswerLimitBy(studentId,
                examService.getRunningExam().getId(),
                start, pageSize);
    }

    @Override
    public int getStudentAnswerCount(String studentId) {
        return studentAnswerMapper.getStudentAnswerCount(studentId, examService.getRunningExam().getId());
    }

    @Override
    public int getStudentAnswerCountByExamId(int examId) {
        return studentAnswerMapper.getStudentAnswerCountByExamId(examId);
    }

    @Override
    public List<Student> getUploadStudents(int examId) {
        return studentAnswerMapper.getUploadStudents(examId);
    }
    @Override
    public List<Student> getUnUploadStudents(int examId) {
        return studentAnswerMapper.getUnUploadStudents(examId);
    }

    @Override
    public List<StudentAnswer> getStudentAnswers(String studentId) {
        if (examService.getRunningExam() == null)
            return new ArrayList<>();
        return studentAnswerMapper.getStudentAnswers(studentId, examService.getRunningExam().getId());
    }

    @Override
    public void insertStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswerMapper.insertStudentAnswer(studentAnswer);
    }

    @Override
    public void updateStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswerMapper.updateStudentAnswer(studentAnswer);
    }

    @Override
    public void deleteStudentAnswerByExamId(int examId) {
        studentAnswerMapper.deleteStudentAnswerByExamId(examId);

    }

    @Override
    public void deleteStudentAnswerById(int id) {
        studentAnswerMapper.deleteStudentAnswerById(id);
    }

    @Override
    public StudentAnswer getStudentAnswerById(int id) {
        return studentAnswerMapper.getStudentAnswerById(id);
    }

    @Override
    public StudentAnswer getStudentAnswerByFileName(String studentId, String fileName) {
        return studentAnswerMapper.getStudentAnswerByFileName(studentId, fileName);
    }
}
