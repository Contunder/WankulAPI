package com.contunder.wankulapi.Application.Model;


import com.contunder.wankulapi.Application.Enum.TypeEnum;

public class Card {

    private int cardNumber;
    private String name;
    private String artist;
    private String rarity;
    private TypeEnum type;
    private String effigy;

    public Card(int cardNumber, String name, String artist, String rarity, TypeEnum type, String effigy) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.artist = artist;
        this.rarity = rarity;
        this.type = type;
        this.effigy = effigy;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getRarity() {
        return rarity;
    }

    public String getTypeName() {
        return type.name();
    }

    public String getEffigy() {
        return effigy;
    }
}
