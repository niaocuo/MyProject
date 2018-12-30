package com.mybatis.bean;

/**
 * Created by 顺 on 2018/7/30.
 */
public class TestReport {

    private  int id;

    private int studentId;

    private int course;

    private int report;

    public int getId() {
        return id;
    }

    public void setId(int tid) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }
}
