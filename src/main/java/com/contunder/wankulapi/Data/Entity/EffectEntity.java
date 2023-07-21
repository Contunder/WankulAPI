package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Effect")
public class EffectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private int pick;
    private int discard;
    private int power;
    private boolean term;

    public EffectEntity(String description, int pick, int discard, int power, boolean term) {
        this.description = description;
        this.pick = pick;
        this.discard = discard;
        this.power = power;
        this.term = term;
    }

    public long getId() {
        return id;
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

    public boolean isTerm() {
        return term;
    }
}
