package com.contunder.wankulapi.Application.Enum;

public enum EffigyEnum {

    LAINK("Laink"),
    TERRACID("Terracid"),
    GUEST("Guest"),
    RANDOM("Random");

    private String name;

    EffigyEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
