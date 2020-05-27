package com.example.examsystem.entity;

public class Setting {
    public static String uploadPath = "/home/zhy/IdeaProjects/upload/";

    private int id;
    /**
     * 后台扫描任务执行的周期 minute
     */
    private int dutyCycle;
    /**
     * 分页查询时的每页记录数、
     */
    private int pageCount;

    /**
     * 手动开启考试的时间阈值
     */
    private int timeThreshold;
    /**
     * 学生上传文件字节数的有效范围
     */
    private int uploadBytesUpper;
    private int uploadBytesLower;

    /**
     * 是否允许主考教师清理和删除考试
     */
    private boolean allowClearAndDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDutyCycle() {
        return dutyCycle;
    }

    public void setDutyCycle(int dutyCycle) {
        this.dutyCycle = dutyCycle;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTimeThreshold() {
        return timeThreshold;
    }

    public void setTimeThreshold(int timeThreshold) {
        this.timeThreshold = timeThreshold;
    }

    public int getUploadBytesUpper() {
        return uploadBytesUpper;
    }

    public void setUploadBytesUpper(int uploadBytesUpper) {
        this.uploadBytesUpper = uploadBytesUpper;
    }

    public int getUploadBytesLower() {
        return uploadBytesLower;
    }

    public void setUploadBytesLower(int uploadBytesLower) {
        this.uploadBytesLower = uploadBytesLower;
    }

    public boolean isAllowClearAndDelete() {
        return allowClearAndDelete;
    }

    public void setAllowClearAndDelete(boolean allowClearAndDelete) {
        this.allowClearAndDelete = allowClearAndDelete;
    }
}
