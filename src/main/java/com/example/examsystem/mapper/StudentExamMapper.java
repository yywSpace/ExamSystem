package com.example.examsystem.mapper;

import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.StudentAnswer;
import com.example.examsystem.entity.StudentExam;
import org.apache.ibatis.annotations.*;
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

    @Select("select count(*) from studentExam,student " +
            "where examId = #{examId} and student.id = studentExam.studentId  " +
            "and student.id like '%${student.id}%' and name like '%${student.name}%' and sClass like '%${student.sClass}%' ")
    int getStudentExamCountByQuery(@Param("examId") int examId, @Param("student")Student student);

    @Select("select count(*) from studentExam where examId = #{examId} and login = 1")
    int getStudentExamLoginCount(int examId);


    @Select("select (@i:=@i+1) as i,student.id,name,sClass,ip  from studentExam,student,(select @i:=0) as it " +
            "where examId = #{examId} and student.id = studentExam.studentId " +
            "order by i desc limit #{start},#{pageSize}")
    List<Student> getStudentExamListLimitBy(@Param("examId") int examId,@Param("start") int start,@Param("pageSize") int pageSize);

    @Select("select (@i:=@i+1) as i,student.id,name,sClass,ip  from studentExam,student,(select @i:=0) as it " +
            "   where examId = #{examId} and student.id = studentExam.studentId " +
            "       and student.id like '%${student.id}%' and name like '%${student.name}%' and sClass like '%${student.sClass}%' " +
            "       order by i desc limit #{start},#{pageSize}")
    List<Student> getStudentExamByQuery(@Param("examId") int examId, @Param("student")Student student, @Param("start") int start,@Param("pageSize") int pageSize);

    @Select("select count(*) from studentExam,student " +
            "where examId = #{examId} and student.id = studentExam.studentId  " +
            "and student.ip like '%${ip}%'")
    int getStudentExamCountByIp(@Param("examId") int examId, String ip);

    @Select("select (@i:=@i+1) as i,student.id,name,sClass,ip  from studentExam,student,(select @i:=0) as it " +
            "   where examId = #{examId} and student.id = studentExam.studentId " +
            "       and student.ip like '%${ip}%'" +
            "       order by i desc limit #{start},#{pageSize}")
    List<Student> getStudentExamByIp(@Param("examId") int examId, String ip, @Param("start") int start,@Param("pageSize") int pageSize);

    @Select("select * from studentExam where studentId = #{studentId} and examId = #{examId}")
    StudentExam getStudentExamById(@Param("studentId") String studentId,@Param("examId") int examId);

    @Delete("delete from studentExam where examId = #{examId} and studentId = #{studentId}")
    void deleteByExamIdAndStudentId(@Param("examId")int examId, @Param("studentId")String studentId);

    @Update("update studentExam set examId = #{examId}, studentId = #{studentId},login = #{login} where id = #{id}")
    void updateStudentExam(StudentExam studentExam);

    @Select("select (@i:=@i+1) as i,student.id,name,sClass,ip,login  from studentExam,student,(select @i:=0) as it " +
            "   where examId = #{examId} and student.id = studentExam.studentId " +
            "       and login = 1" +
            "       order by i desc")
    List<Student> getLoginStudentList(int examId);

    @Select("select (@i:=@i+1) as i,student.id,name,sClass,ip,login  from studentExam,student,(select @i:=0) as it " +
            "   where examId = #{examId} and student.id = studentExam.studentId " +
            "       and login = 0" +
            "       order by i desc")
    List<Student> getNotLoginStudentList(int examId);

}
