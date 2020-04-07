package com.example.demo.entity;

import java.util.Date;

public class deviation {
    private static final long serialVersionUID = 1L;

    private String id;

    private String devicename;

    private String blacktemp;

    private String testshow;

    private Date cretime;

    @Override
    public String toString() {
        return "deviation{" +
                "id='" + id + '\'' +
                ", devicename='" + devicename + '\'' +
                ", blacktemp='" + blacktemp + '\'' +
                ", testshow='" + testshow + '\'' +
                ", cretime=" + cretime +
                '}';
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlacktemp() {
        return blacktemp;
    }

    public void setBlacktemp(String blacktemp) {
        this.blacktemp = blacktemp;
    }

    public String getTestshow() {
        return testshow;
    }

    public void setTestshow(String testshow) {
        this.testshow = testshow;
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

}
