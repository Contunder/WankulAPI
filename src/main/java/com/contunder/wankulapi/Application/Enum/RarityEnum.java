package com.contunder.wankulapi.Application.Enum;

public enum RarityEnum {

    TERRAIN("Terrain"),
    COMMUNE("Commune"),
    PEU_COMMUNE("Peu Commune"),
    RARE("Rare"),
    ULTRA_RARE_HOLO_1("Ultra Rare Holo 1"),
    ULTRA_RARE_HOLO_2("Ultra Rare Holo 2"),
    LEGENDAIRE_BRONZE("Legendaire Bronze"),
    LEGENDAIRE_ARGENT("Legendaire Argent"),
    LEGENDAIRE_OR("Légendaire Or");

    private String rarity;

    RarityEnum(String rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }
}
