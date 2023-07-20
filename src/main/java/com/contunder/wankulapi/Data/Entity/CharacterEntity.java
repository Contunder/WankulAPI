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

}
