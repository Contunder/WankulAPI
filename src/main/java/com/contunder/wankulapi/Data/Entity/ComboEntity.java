package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Combo")
public class ComboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "effecy", nullable = false)
    private EffectEntity effect;

    private String description;
    private int duree;

    public ComboEntity(long characterNumber, EffectEntity effect, String description, int duree) {
        this.id = characterNumber;
        this.effect = effect;
        this.description = description;
        this.duree = duree;
    }

    public long getId() {
        return id;
    }

    public EffectEntity getEffect() {
        return effect;
    }

    public String getDescription() {
        return description;
    }

    public int getDuree() {
        return duree;
    }
}
