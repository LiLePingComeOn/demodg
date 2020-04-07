package com.example.demo.entity;

import java.util.Date;

public class blackdevice {
    private static final long serialVersionUID = 1L;
        private String id ;

        private String name;

        private String bm;

        private String path;

        private String pid;

        private String modelname;

        private String model;

        private String power;

        private String size;

        private String weight;

        private String area;

        private Date cretime;

        private String sn_model;

        private String election;

        private String showtime;

        private String identification;

    @Override
    public String toString() {
        return "blackdevice{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bm='" + bm + '\'' +
                ", path='" + path + '\'' +
                ", pid='" + pid + '\'' +
                ", modelname='" + modelname + '\'' +
                ", model='" + model + '\'' +
                ", power='" + power + '\'' +
                ", size='" + size + '\'' +
                ", weight='" + weight + '\'' +
                ", area='" + area + '\'' +
                ", cretime=" + cretime +
                ", sn_model='" + sn_model + '\'' +
                ", election='" + election + '\'' +
                ", showtime='" + showtime + '\'' +
                ", identification='" + identification + '\'' +
                '}';
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    public String getSn_model() {
        return sn_model;
    }

    public void setSn_model(String sn_model) {
        this.sn_model = sn_model;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
