package com.example.examsystem.entity;


public class Exam {
    private int id;
    private int teacherId;
    private String name;
    private String paperName;
    private String startTime;
    private Boolean autoStart = false;
    private Boolean uploadExamPaper = false;
    private Boolean running = false;
    private Boolean finished = false;
    private Boolean archived = false;
    private Boolean cleaned = false;

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

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

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", paperName='" + paperName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", autoStart=" + autoStart +
                ", running=" + running +
                ", finished=" + finished +
                ", archived=" + archived +
                ", cleaned=" + cleaned +
                '}';
    }
}
