package com.contunder.wankulapi.Application.Enum;

public enum JewelEnum {

    YELLOW("Yellow"),
    PURPLE("Purple"),
    LEFT("Postion_Left"),
    RIGHT("Position_Right");

    private String name;

    JewelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
