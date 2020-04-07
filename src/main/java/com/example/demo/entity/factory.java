package com.example.demo.entity;

public class factory {
    private String id;

    private String name;

    private String isCode;

    private String type;

    private String regular;

    private String codesiteX;

    private String codesiteY;

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

    public String getIsCode() {
        return isCode;
    }

    public void setIsCode(String isCode) {
        this.isCode = isCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getCodesiteX() {
        return codesiteX;
    }

    public void setCodesiteX(String codesiteX) {
        this.codesiteX = codesiteX;
    }

    public String getCodesiteY() {
        return codesiteY;
    }

    public void setCodesiteY(String codesiteY) {
        this.codesiteY = codesiteY;
    }

    @Override
    public String toString() {
        return "factory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isCode='" + isCode + '\'' +
                ", type='" + type + '\'' +
                ", regular='" + regular + '\'' +
                ", codesiteX='" + codesiteX + '\'' +
                ", codesiteY='" + codesiteY + '\'' +
                '}';
    }
}
