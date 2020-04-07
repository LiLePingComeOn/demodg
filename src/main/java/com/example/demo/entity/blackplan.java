package com.example.demo.entity;

import java.util.Date;

public class blackplan {

    private static final long serialVersionUID = 1L;

    private String id;

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    private String planname;

    private String name;

    private String modelname;

    private String model;

    private String election;

    private String power;

    private String size;

    private String weight;

    private String area;

    private Date cretime;

    private String sn_model;

    private String identification;

    @Override
    public String toString() {
        return "blackplan{" +
                "id='" + id + '\'' +
                ", planname='" + planname + '\'' +
                ", name='" + name + '\'' +
                ", modelname='" + modelname + '\'' +
                ", model='" + model + '\'' +
                ", election='" + election + '\'' +
                ", power='" + power + '\'' +
                ", size='" + size + '\'' +
                ", weight='" + weight + '\'' +
                ", area='" + area + '\'' +
                ", cretime=" + cretime +
                ", sn_model='" + sn_model + '\'' +
                ", identification='" + identification + '\'' +
                '}';
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getSn_model() {
        return sn_model;
    }

    public void setSn_model(String sn_model) {
        this.sn_model = sn_model;
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

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
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

}
