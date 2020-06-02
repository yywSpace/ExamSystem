package com.example.examsystem.mapper;

import com.example.examsystem.entity.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MessageMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into message(examId,time,content) values(#{examId}, #{time}, #{content})")
    void insertMessage(Message message);

    @Update("update message set time = #{time}, content = #{content} where id = #{id}")
    void updateMessage(Message message);

    @Delete("delete from message where id = #{id}")
    void deleteMessageById(int id);

    @Delete("delete from message where examId = #{examId}")
    void deleteMessageByExamId(int examId);

    @Select("select * from message where examId = #{examId} order by id desc limit #{start},#{pageSize}")
    List<Message> getMessageListLimitBy(@Param("examId") int examId,@Param("start") int start,@Param("pageSize") int pageSize);

    @Select("select count(*) from message  where examId = #{examId}")
    int getMessageCount(int examId);
}
