package com.contunder.wankulapi.Application.Model;

public class Combo {

    private String description;
    private int duree;
    private Effect effet;

    public Combo(String description, int duree, Effect effet) {
        this.description = description;
        this.duree = duree;
        this.effet = effet;
    }

    public String getDescription() {
        return description;
    }

    public int getDuree() {
        return duree;
    }

    public Effect getEffet() {
        return effet;
    }

}
