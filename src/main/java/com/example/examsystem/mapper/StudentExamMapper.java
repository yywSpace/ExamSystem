package com.example.examsystem.mapper;

import com.example.examsystem.entity.StudentExam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentExamMapper {
    @Insert("INSERT INTO studentExam values(#{studentId},#{examId})")
    void insert(StudentExam studentExam);

    @Delete("delete from studentExam where examId = #{examId}")
    void deleteByExamId(int examId);

    @Select("select count(*) from studentExam where examId = #{examId}")
    int getStudentExamCount(int examId);
}
