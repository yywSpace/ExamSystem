package com.example.examsystem.entity;

import java.util.Date;

public class Exam {
    private int id;
    private int teacherId;
    private String name;
    private String startTime;
    private Boolean autoStart;
    private Boolean uploadExamPaper;
    private Boolean running;
    private Boolean finished;
    private Boolean archived;
    private Boolean cleaned;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Boolean getAutoStart() {
        return autoStart;
    }

    public void setAutoStart(Boolean autoStart) {
        this.autoStart = autoStart;
    }


    public Boolean getUploadExamPaper() {
        return uploadExamPaper;
    }

    public void setUploadExamPaper(Boolean uploadExamPaper) {
        this.uploadExamPaper = uploadExamPaper;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getCleaned() {
        return cleaned;
    }

    public void setCleaned(Boolean cleaned) {
        this.cleaned = cleaned;
    }
}
