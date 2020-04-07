package com.example.demo.entity;

import java.util.Date;

public class report {
    private static final long serialVersionUID = 1L;

    private String id;

    private String devicefk;

    private String comtemperature;

    private String calibrationvalue;

    private String ktdevicefk;

    private String meanvalue;

    private String variancevalue;

    private String fluctucationrange;

    private String status;

    private String sys_user;

    private Date cretime;

    private String emssing;

    private String absolutedeviation;

    private String maxvalue;

    private String minvalue;

    private String spacing;

    private String factory;

    @Override
    public String toString() {
        return "report{" +
                "id='" + id + '\'' +
                ", devicefk='" + devicefk + '\'' +
                ", comtemperature='" + comtemperature + '\'' +
                ", calibrationvalue='" + calibrationvalue + '\'' +
                ", ktdevicefk='" + ktdevicefk + '\'' +
                ", meanvalue='" + meanvalue + '\'' +
                ", variancevalue='" + variancevalue + '\'' +
                ", fluctucationrange='" + fluctucationrange + '\'' +
                ", status='" + status + '\'' +
                ", sys_user='" + sys_user + '\'' +
                ", cretime=" + cretime +
                ", emssing='" + emssing + '\'' +
                ", absolutedeviation='" + absolutedeviation + '\'' +
                ", maxvalue='" + maxvalue + '\'' +
                ", minvalue='" + minvalue + '\'' +
                ", spacing='" + spacing + '\'' +
                ", factory='" + factory + '\'' +
                '}';
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSpacing() {
        return spacing;
    }

    public void setSpacing(String spacing) {
        this.spacing = spacing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevicefk() {
        return devicefk;
    }

    public void setDevicefk(String devicefk) {
        this.devicefk = devicefk;
    }

    public String getComtemperature() {
        return comtemperature;
    }

    public void setComtemperature(String comtemperature) {
        this.comtemperature = comtemperature;
    }

    public String getCalibrationvalue() {
        return calibrationvalue;
    }

    public void setCalibrationvalue(String calibrationvalue) {
        this.calibrationvalue = calibrationvalue;
    }

    public String getKtdevicefk() {
        return ktdevicefk;
    }

    public void setKtdevicefk(String ktdevicefk) {
        this.ktdevicefk = ktdevicefk;
    }

    public String getMeanvalue() {
        return meanvalue;
    }

    public void setMeanvalue(String meanvalue) {
        this.meanvalue = meanvalue;
    }

    public String getVariancevalue() {
        return variancevalue;
    }

    public void setVariancevalue(String variancevalue) {
        this.variancevalue = variancevalue;
    }


    public String getFluctucationrange() {
        return fluctucationrange;
    }

    public void setFluctucationrange(String fluctucationrange) {
        this.fluctucationrange = fluctucationrange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSys_user() {
        return sys_user;
    }

    public void setSys_user(String sys_user) {
        this.sys_user = sys_user;
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    public String getEmssing() {
        return emssing;
    }

    public void setEmssing(String emssing) {
        this.emssing = emssing;
    }

    public String getAbsolutedeviation() {
        return absolutedeviation;
    }

    public void setAbsolutedeviation(String absolutedeviation) {
        this.absolutedeviation = absolutedeviation;
    }

    public String getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(String maxvalue) {
        this.maxvalue = maxvalue;
    }

    public String getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(String minvalue) {
        this.minvalue = minvalue;
    }
}
