package com.example.examsystem.mapper;

import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentExam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentExamMapper {
    @Insert("INSERT INTO studentExam (studentId, examId) values(#{studentId},#{examId})")
    void insert(StudentExam studentExam);

    @Delete("delete from studentExam where examId = #{examId}")
    void deleteByExamId(int examId);

    @Select("select count(*) from studentExam where examId = #{examId}")
    int getStudentExamCount(int examId);

    @Select("select student.id,name,sClass,ip from studentExam,student " +
            "where examId = #{examId} and student.id = studentExam.studentId " +
            "order by id desc limit #{start},#{pageSize}")
    List<Student> getStudentExamListLimitBy(int examId, int start, int pageSize);

    @Delete("delete from studentExam where examId = #{examId} and studentId = #{studentId}")
    void deleteByExamIdAndStudentId(int examId, String studentId);
}
