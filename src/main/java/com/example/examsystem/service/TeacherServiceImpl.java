package com.example.examsystem.service;

import com.example.examsystem.entity.Teacher;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AdminMapper adminMapper;
    @Override
    public List<Teacher> getTeacherLimitBy(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return teacherMapper.getTeacherLimitBy(start, pageSize);
    }

    @Override
    public int getTeacherCount() {
        return teacherMapper.getTeacherCount();
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacherById(int id) {
        teacherMapper.deleteTeacherById(id);
    }
}
