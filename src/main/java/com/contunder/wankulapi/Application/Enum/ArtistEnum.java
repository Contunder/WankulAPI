package com.contunder.wankulapi.Application.Enum;

public enum ArtistEnum {

    LEONARD_LAM("Léonard Lam"),
    YURIIICK("Yuriiick");

    private String name;

    ArtistEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
