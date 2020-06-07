package com.example.examsystem.mapper;

import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentAnswer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentAnswerMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into studentAnswer(studentId, examId, answerFileName,answerFileSize,answerFileTime)" +
            " values(#{studentId}, #{examId}, #{answerFileName}, #{answerFileSize}, #{answerFileTime})")
    void insertStudentAnswer(StudentAnswer studentAnswer);

    @Update("update studentAnswer " +
            " set answerFileName = #{answerFileName}, answerFileSize = #{answerFileSize}, answerFileTime = #{answerFileTime}" +
            " where id = #{id}")
    void updateStudentAnswer(StudentAnswer studentAnswer);

    @Delete("delete from studentAnswer where examId = #{examId}")
    void deleteStudentAnswerByExamId(int examId);

    @Delete("delete from studentAnswer where id = #{id}")
    void deleteStudentAnswerById(int id);

    @Select("select count(*) from studentAnswer where studentId = #{studentId}  and examId = #{examId}")
    int getStudentAnswerCount(@Param("studentId") String studentId,@Param("examId") int examId);

    @Select("select count(distinct studentId) from studentAnswer where examId = #{examId}")
    int getStudentAnswerCountByExamId(int examId);

    @Select("select * from studentAnswer where id = #{id}")
    StudentAnswer getStudentAnswerById(int id);

    @Select("select * from studentAnswer where studentId = #{studentId} and answerFileName=#{fileName}")
    StudentAnswer getStudentAnswerByFileName(@Param("studentId") String studentId,@Param("fileName") String fileName);

    @Select("select * from studentAnswer where studentId = #{studentId}  and examId = #{examId} order by id desc limit #{start},#{pageSize}")
    List<StudentAnswer> getStudentAnswerLimitBy(@Param("studentId")String studentId, @Param("examId")int examId,@Param("start") int start,@Param("pageSize") int pageSize);

    @Select("select * from studentAnswer where studentId = #{studentId}  and examId = #{examId}")
    List<StudentAnswer> getStudentAnswers(@Param("studentId") String studentId,@Param("examId") int examId);

    @Select("select distinct student.id,name,sClass,ip from studentAnswer,student " +
            "where examId = #{examId} and student.id = studentAnswer.studentId;")
    List<Student> getUploadStudents(int examId);

    @Select("SELECT * FROM student, studentExam " +
            "where examId = #{examId} and student.id = studentExam.studentId and " +
            "student.id not in (select studentId from studentAnswer where examId = #{examId});")
    List<Student> getUnUploadStudents(int examId);

}
