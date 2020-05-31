package com.example.examsystem.mapper;

import com.example.examsystem.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into student(id, name, sClass, ip) values(#{id}, #{name}, #{sClass}, #{ip}) " +
            "ON DUPLICATE KEY UPDATE name = #{name}, sClass = #{sClass}, ip = #{ip}")
    void insertStudent(Student student);

    @Update("update student set name = #{name}, sClass = #{sClass}, ip = #{ip} where id = #{id}")
    void updateStudent(Student student);

    @Delete("delete from student where id = #{id}")
    void deleteStudentById(String id);

    @Select("select * from student where id = #{id}")
    Student getStudentById(String id);

    @Select("select * from student")
    List<Student> getStudentList();

    @Select("select count(*) from student")
    int getStudentCount();

    @Select("update student set ip = ''")
    void clearIp();
}
