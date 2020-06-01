package com.example.examsystem.service;

import com.example.examsystem.entity.Message;
import com.example.examsystem.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public void insertMessage(Message message) {
        messageMapper.insertMessage(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageMapper.updateMessage(message);
    }

    @Override
    public void deleteMessageById(int id) {
        messageMapper.deleteMessageById(id);
    }

    @Override
    public void deleteMessageByExamId(int examId) {
        messageMapper.deleteMessageByExamId(examId);
    }

    @Override
    public int getMessageCount(int examId) {
        return messageMapper.getMessageCount(examId);
    }

    @Override
    public List<Message> getMessageListLimitBy(int examId, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return messageMapper.getMessageListLimitBy(examId, start, pageSize);
    }
}
