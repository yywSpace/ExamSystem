package com.example.examsystem.mapper;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into teacher(name, fullName, password, isManager) values(#{name}, #{fullName}, #{password}, #{isManager})")
    void insertTeacher(Teacher teacher);

    @Update("update teacher set name = #{name}, fullName = #{fullName}, password = #{password}, isManager = #{isManager} where id = #{id}")
    void updateTeacher(Teacher teacher);

    @Delete("delete from teacher where id = #{id}")
    void deleteTeacherById(int id);

    @Select("select count(*) from teacher")
    int getTeacherCount();

    @Select("select * from teacher")
    List<Teacher> getTeacherList();

    @Select("select * from teacher order by id desc limit #{start},#{pageSize}")
    List<Teacher> getTeacherLimitBy(@Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select * from teacher where name = #{name}")
    Teacher getTeacherByName(String name);
}
