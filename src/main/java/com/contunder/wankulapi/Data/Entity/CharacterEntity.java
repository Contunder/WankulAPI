package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Personage")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardNumber", nullable = false)
    private CardEntity card;

    private int costs;
    private int power;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combo", nullable = false)
    private ComboEntity combo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "effect", nullable = false)
    private EffectEntity effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewel", nullable = false)
    private JewelEntity jewel;

    private String description;

    public CharacterEntity(CardEntity card, int costs, int power, ComboEntity combo, EffectEntity effect, JewelEntity jewel, String description) {
        this.id = card.getId();
        this.card = card;
        this.costs = costs;
        this.power = power;
        this.combo = combo;
        this.effect = effect;
        this.jewel = jewel;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public CardEntity getCard() {
        return card;
    }

    public int getCosts() {
        return costs;
    }

    public int getPower() {
        return power;
    }

    public ComboEntity getCombo() {
        return combo;
    }

    public EffectEntity getEffect() {
        return effect;
    }

    public JewelEntity getJewel() {
        return jewel;
    }

    public String getDescription() {
        return description;
    }
}
