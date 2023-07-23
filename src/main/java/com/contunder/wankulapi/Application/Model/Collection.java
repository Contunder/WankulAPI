package com.contunder.wankulapi.Application.Model;

import com.contunder.wankulapi.Application.Enum.TypeEnum;

public class Collection {

    private int cardNumber;
    private String name;
    private String artist;
    private String rarity;
    private TypeEnum type;
    private String effigy;
    private int numberOfCard;

    public Collection(int cardNumber, String name, String artist, String rarity, TypeEnum type, String effigy, int numberOfCard) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.artist = artist;
        this.rarity = rarity;
        this.type = type;
        this.effigy = effigy;
        this.numberOfCard = numberOfCard;
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

    public TypeEnum getType() {
        return type;
    }

    public String getEffigy() {
        return effigy;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }
}
