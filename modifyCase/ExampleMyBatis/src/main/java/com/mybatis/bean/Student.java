package com.mybatis.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/30.
 */
public class Student {

    private Integer id;

    private String name;

    private Integer sex;

    private String tclass;

    private List<TestReport> testReports;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public List<TestReport> getTestReports() {
        return testReports;
    }

    public void setTestReports(List<TestReport> testReports) {
        this.testReports = testReports;
    }
}
