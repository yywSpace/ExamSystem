package com.example.examsystem.mapper;

import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Exam;
import com.example.examsystem.entity.Teacher;
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
    void deleteExamById(int id);

    @Select("select * from exam order by id desc limit #{start},#{pageSize}")
    List<Exam> getExamLimitBy(int start, int pageSize);

    @Select("select * from exam where id = ${id}")
    Exam getExamById(int id);

    @Select("select * from exam where running = 1")
    List<Exam> getRunningExam();

    @Select("select count(*) from exam")
    int getExamCount();


}
