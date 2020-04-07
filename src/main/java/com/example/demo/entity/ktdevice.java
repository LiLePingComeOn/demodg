package com.example.demo.entity;

import java.util.List;

public class ktdevice {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String emissing;

    @Override
    public String toString() {
        return "ktdevice{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emissing='" + emissing + '\'' +
                ", spacing='" + spacing + '\'' +
                ", deviations=" + deviations +
                '}';
    }

    public String getEmissing() {
        return emissing;
    }

    public void setEmissing(String emissing) {
        this.emissing = emissing;
    }

    private String spacing;

    private List<deviation> deviations;

    public List<deviation> getDeviations() {
        return deviations;
    }

    public void setDeviations(List<deviation> deviations) {
        this.deviations = deviations;
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



    public String getSpacing() {
        return spacing;
    }

    public void setSpacing(String spacing) {
        this.spacing = spacing;
    }

}
