package com.example.examsystem.entity;

public class Student {
    private String id;
    private String name;
    private String sClass;
    private String ip = "";

    public Student() {
    }

    public Student(String id, String name, String sClass ) {
        this.id = id;
        this.name = name;
        this.sClass = sClass;
    }

    public Student(String id, String name, String sClass, String ip) {
        this.id = id;
        this.name = name;
        this.sClass = sClass;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sClass='" + sClass + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
