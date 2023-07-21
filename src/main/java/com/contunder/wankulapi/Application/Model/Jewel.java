package com.contunder.wankulapi.Application.Model;

import java.util.Map;

public class Jewel {

    Map<JewelColor, JewelPosition> jewel;

    public Jewel(Map<JewelColor, JewelPosition> jewel) {
        this.jewel = jewel;
    }

    public Map<JewelColor, JewelPosition> getJewel() {
        return jewel;
    }
}
