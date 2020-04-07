package com.example.demo.entity;

import java.util.Date;

public class SecurityDoor {

    private String id;

    private String doorIp;

    private String doorName;

    private String alarmKind;

    private String alarmPlace;

    private int todayPassNumber;

    private int todayAlarmNumber;

    private String picPath;

    private String passTime;

    private String alarmkindName;

    public SecurityDoor() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoorIp() {
        return this.doorIp;
    }

    public void setDoorIp(String doorIp) {
        this.doorIp = doorIp;
    }

    public String getDoorName() {
        return this.doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getAlarmKind() {
        return this.alarmKind;
    }

    public void setAlarmKind(String alarmKind) {
        this.alarmKind = alarmKind;
    }

    public String getAlarmPlace() {
        return this.alarmPlace;
    }

    public void setAlarmPlace(String alarmPlace) {
        this.alarmPlace = alarmPlace;
    }

    public int getTodayPassNumber() {
        return this.todayPassNumber;
    }

    public void setTodayPassNumber(int todayPassNumber) {
        this.todayPassNumber = todayPassNumber;
    }

    public int getTodayAlarmNumber() {
        return this.todayAlarmNumber;
    }

    public void setTodayAlarmNumber(int todayAlarmNumber) {
        this.todayAlarmNumber = todayAlarmNumber;
    }

    public String getPicPath() {
        return this.picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPassTime() {
        return this.passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public String getAlarmkindName() {
        return this.alarmkindName;
    }

    public void setAlarmkindName(String alarmkindName) {
        this.alarmkindName = alarmkindName;
    }

    public String toString() {
        return "SecurityDoor [id=" + this.id + ", doorIp=" + this.doorIp + ", doorName=" + this.doorName + ", alarmKind=" + this.alarmKind + ", alarmPlace=" + this.alarmPlace + ", todayPassNumber=" + this.todayPassNumber + ", todayAlarmNumber=" + this.todayAlarmNumber + ", picPath=" + this.picPath + ", passTime=" + this.passTime + ", alarmkindName=" + this.alarmkindName + "]";
    }
}

