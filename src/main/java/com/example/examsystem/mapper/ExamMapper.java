package com.example.examsystem.mapper;

import com.example.examsystem.entity.Exam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExamMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO exam " +
            "(teacherId, name,paperName, startTime, autoStart, uploadExamPaper, running, finished, archived, cleaned) " +
            "VALUES (#{teacherId}, #{name},#{paperName}, #{startTime}, #{autoStart}, #{uploadExamPaper}, " +
            "#{running}, #{finished}, #{archived}, #{cleaned});")
    void insertExam(Exam exam);

    @Update("update exam set " +
            "name='${name}', paperName='${paperName}', startTime='${startTime}', autoStart=${autoStart}, uploadExamPaper=${uploadExamPaper}, " +
            "running=${running}, finished=${finished}, archived=${archived}, cleaned=${cleaned} where id = ${id}")
    void updateExam(Exam exam);

    @Delete("delete from exam where id = #{id}")
    void deleteExamById(@Param("id") int id);

    @Select("select * from exam order by id desc limit #{start},#{pageSize}")
    List<Exam> getExamLimitBy(@Param("start") int start,@Param("pageSize") int pageSize);

    @Select("select * from exam where teacherId =  #{teacherId} order by id desc limit #{start},#{pageSize}")
    List<Exam> getTeacherExamLimitBy(@Param("teacherId") int teacherId, @Param("start")int start, @Param("pageSize")int pageSize);

    @Select("select * from exam where id = ${id}")
    Exam getExamById(int id);

    @Select("select * from exam where running = 1")
    List<Exam> getRunningExam();

    @Select("select count(*) from exam")
    int getExamCount();

    @Select("select count(*) from exam  where teacherId =  #{teacherId}")
    int getTeacherExamCount(@Param("teacherId") int teacherId);

    @Select("select * from exam where running = 0 and finished = 0 and autoStart = 1")
    List<Exam> getAutoStartExamListNotStart();

}
