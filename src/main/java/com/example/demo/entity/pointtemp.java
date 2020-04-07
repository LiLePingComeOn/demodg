package com.example.demo.entity;

public class pointtemp {

    private static final long serialVersionUID = 1L;

    private String id;

    private String planid;

    private String pointtemperature;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid;
    }

    public String getPointtemperature() {
        return pointtemperature;
    }

    public void setPointtemperature(String pointtemperature) {
        this.pointtemperature = pointtemperature;
    }

    @Override
    public String toString() {
        return "pointtemp{" +
                "id='" + id + '\'' +
                ", planid='" + planid + '\'' +
                ", pointtemperature='" + pointtemperature + '\'' +
                '}';
    }
}
