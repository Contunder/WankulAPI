package com.contunder.wankulapi.Application.Model;

public class Character {

    private int couts;
    private int power;
    private Effect effect;
    private Combo combo;
    private String description;
    private Jewel jewels;

    public Character(int couts, int power, Effect effect, Combo combo, String description, Jewel jewels) {
        this.couts = couts;
        this.power = power;
        this.effect = effect;
        this.combo = combo;
        this.description = description;
        this.jewels = jewels;
    }

    public int getCouts() {
        return couts;
    }

    public int getPower() {
        return power;
    }

    public Effect getEffect() {
        return effect;
    }

    public Combo getCombo() {
        return combo;
    }

    public String getDescription() {
        return description;
    }

    public Jewel getJewels() {
        return jewels;
    }
}
