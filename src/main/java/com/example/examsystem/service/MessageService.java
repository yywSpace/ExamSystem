package com.example.examsystem.service;

import com.example.examsystem.entity.Message;

import java.util.List;

public interface MessageService {

    void insertMessage(Message message);

    void updateMessage(Message message);

    void deleteMessageById(int id);
    void deleteMessageByExamId(int examId);

    int getMessageCount(int examId);

    List<Message> getMessageListLimitBy(int examId, int page, int pageSize);
}
