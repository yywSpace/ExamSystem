package com.example.examsystem.service;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentExam;

import java.util.List;

public interface  StudentExamService {

    StudentExam getStudentExamById(String id, int examId);

    void insertStudentExam(StudentExam studentExam);

    void updateStudentExam(StudentExam StudentExam);

    void deleteStudentExamByExamId(int id);

    void deleteByExamIdAndStudentId(int examId, String id);

    int getStudentExamCount(int examId);

    int getStudentExamLoginCount(int examId);

    List<Student> getStudentExamListLimitBy(int examId, int page, int pageSize);

    List<Student> getStudentExamByQuery(int examId, Student student, int page, int pageSize);

    int getStudentExamCountByQuery(int examId, Student student);

    List<Student> getStudentExamByIp(int examId, String ip, int page, int pageSize);

    int getStudentExamCountByIp(int examId, String ip);


    List<Student> getLoginStudentList(int examId);

    List<Student> getNotLoginStudentList(int examId);
}
