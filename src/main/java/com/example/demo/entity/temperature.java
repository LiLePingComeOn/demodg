package com.example.demo.entity;

import java.util.Date;

public class temperature {
    private static final long serialVersionUID = 1L;

    private String id;

    private String temperature;

    private Date cretime;

    private String devicefk;

    private String planfk;

    private String sys_user;

    private String comtemperature;

    private String ktdevicefk;

    private String wettemp;

    private String status;

    private String emssing;

    @Override
    public String toString() {
        return "temperature{" +
                "id='" + id + '\'' +
                ", temperature='" + temperature + '\'' +
                ", cretime=" + cretime +
                ", devicefk='" + devicefk + '\'' +
                ", planfk='" + planfk + '\'' +
                ", sys_user='" + sys_user + '\'' +
                ", comtemperature='" + comtemperature + '\'' +
                ", ktdevicefk='" + ktdevicefk + '\'' +
                ", wettemp='" + wettemp + '\'' +
                ", status='" + status + '\'' +
                ", emssing='" + emssing + '\'' +
                '}';
    }

    public String getEmssing() {
        return emssing;
    }

    public void setEmssing(String emssing) {
        this.emssing = emssing;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWettemp() {
        return wettemp;
    }

    public void setWettemp(String wettemp) {
        this.wettemp = wettemp;
    }

    public String getKtdevicefk() {
        return ktdevicefk;
    }

    public void setKtdevicefk(String ktdevicefk) {
        this.ktdevicefk = ktdevicefk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    public String getDevicefk() {
        return devicefk;
    }

    public void setDevicefk(String devicefk) {
        this.devicefk = devicefk;
    }

    public String getPlanfk() {
        return planfk;
    }

    public void setPlanfk(String planfk) {
        this.planfk = planfk;
    }

    public String getSys_user() {
        return sys_user;
    }

    public void setSys_user(String sys_user) {
        this.sys_user = sys_user;
    }

    public String getComtemperature() {
        return comtemperature;
    }

    public void setComtemperature(String comtemperature) {
        this.comtemperature = comtemperature;
    }

}