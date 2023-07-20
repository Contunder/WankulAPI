package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String rarity;
    private String artist;
    private String effigy;

    public CardEntity(int cardNumber, String name, String rarity, String artist, String effigy) {
        this.id = cardNumber;
        this.name = name;
        this.rarity = rarity;
        this.artist = artist;
        this.effigy = effigy;
    }

    public CardEntity(int cardNumber, String name) {
        this.id = cardNumber;
        this.name = name;
    }

    public CardEntity() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getArtist() {
        return artist;
    }

    public String getEffigy() {
        return effigy;
    }
}
