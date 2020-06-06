package com.example.examsystem.service;

import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentExam;
import com.example.examsystem.mapper.StudentExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Service
public class StudentExamServiceImpl implements StudentExamService {
    @Autowired
    StudentExamMapper studentExamMapper;

    @Override
    public StudentExam getStudentExamById( String id,int examId) {
        return studentExamMapper.getStudentExamById(id,examId);
    }

    @Override
    public void insertStudentExam(StudentExam studentExam) {
        studentExamMapper.insert(studentExam);
    }

    @Override
    public void updateStudentExam(StudentExam studentExam) {
        studentExamMapper.updateStudentExam(studentExam);
    }

    @Override
    public void deleteStudentExamByExamId(int examId) {
        studentExamMapper.deleteByExamId(examId);
    }

    @Override
    public void deleteByExamIdAndStudentId(int examId, String id) {
        studentExamMapper.deleteByExamIdAndStudentId(examId, id);
    }

    @Override
    public int getStudentExamCount(int examId) {
        return studentExamMapper.getStudentExamCount(examId);
    }

    @Override
    public int getStudentExamLoginCount(int examId) {
        return studentExamMapper.getStudentExamLoginCount(examId);
    }

    @Override
    public List<Student> getLoginStudentList(int examId) {
        return studentExamMapper.getLoginStudentList(examId);
     }

    @Override
    public List<Student> getNotLoginStudentList(int examId) {
        return studentExamMapper.getNotLoginStudentList(examId);
    }
    @Override
    public List<Student> getStudentExamListLimitBy(int examId, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return studentExamMapper.getStudentExamListLimitBy(examId, start, pageSize);
    }

    @Override
    public List<Student> getStudentExamByQuery(int examId, Student student, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return studentExamMapper.getStudentExamByQuery(examId, student, start, pageSize);
    }

    @Override
    public int getStudentExamCountByQuery(int examId, Student student) {
        return studentExamMapper.getStudentExamCountByQuery(examId, student);
    }

    @Override
    public List<Student> getStudentExamByIp(int examId, String ip, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return studentExamMapper.getStudentExamByIp(examId, ip, start, pageSize);
    }

    @Override
    public int getStudentExamCountByIp(int examId, String ip) {
        return studentExamMapper.getStudentExamCountByIp(examId, ip);
    }
}
