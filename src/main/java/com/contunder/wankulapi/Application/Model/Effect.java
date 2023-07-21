package com.contunder.wankulapi.Application.Model;

import com.contunder.wankulapi.Application.Enum.TypeEnum;

public class Effect {

    private String description;
    private int pick;
    private int discard;
    private int power;
    private boolean condition;

    public Effect(String description, int pick, int discard, int power, boolean condition) {
        this.description = description;
        this.pick = pick;
        this.discard = discard;
        this.power = power;
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public int getPick() {
        return pick;
    }

    public int getDiscard() {
        return discard;
    }

    public int getPower() {
        return power;
    }

    public boolean isCondition() {
        return condition;
    }
}
