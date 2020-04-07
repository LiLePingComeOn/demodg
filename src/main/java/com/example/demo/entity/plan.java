package com.example.demo.entity;

import java.util.Date;
import java.util.List;

public class plan {
    private static final long serialVersionUID = 1L;

    private String id ;

    private String value;

    private String testinterval;

    private String testcount;

    private Date cretime;

    private String point;

    private String flow;

    private String differentValue;

    private String arrangeValue;

    private List<pointtemp> list ;

    @Override
    public String toString() {
        return "plan{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", testinterval='" + testinterval + '\'' +
                ", testcount='" + testcount + '\'' +
                ", cretime=" + cretime +
                ", point='" + point + '\'' +
                ", flow='" + flow + '\'' +
                ", differentValue='" + differentValue + '\'' +
                ", arrangeValue='" + arrangeValue + '\'' +
                ", list=" + list +
                '}';
    }

    public List<pointtemp> getList() {
        return list;
    }

    public void setList(List<pointtemp> list) {
        this.list = list;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getDifferentValue() {
        return differentValue;
    }

    public void setDifferentValue(String differentValue) {
        this.differentValue = differentValue;
    }

    public String getArrangeValue() {
        return arrangeValue;
    }

    public void setArrangeValue(String arrangeValue) {
        this.arrangeValue = arrangeValue;
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTestinterval() {
        return testinterval;
    }

    public void setTestinterval(String testinterval) {
        this.testinterval = testinterval;
    }

    public String getTestcount() {
        return testcount;
    }

    public void setTestcount(String testcount) {
        this.testcount = testcount;
    }


}
