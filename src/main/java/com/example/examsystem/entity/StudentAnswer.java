package com.example.examsystem.entity;

public class StudentAnswer {
    private int id;
    private String studentId;
    private int examId;
    private String answerFileName;
    private int answerFileSize;
    private String answerFileTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getAnswerFileName() {
        return answerFileName;
    }

    public void setAnswerFileName(String answerFileName) {
        this.answerFileName = answerFileName;
    }

    public int getAnswerFileSize() {
        return answerFileSize;
    }

    public void setAnswerFileSize(int answerFileSize) {
        this.answerFileSize = answerFileSize;
    }

    public String getAnswerFileTime() {
        return answerFileTime;
    }

    public void setAnswerFileTime(String answerFileTime) {
        this.answerFileTime = answerFileTime;
    }
}
