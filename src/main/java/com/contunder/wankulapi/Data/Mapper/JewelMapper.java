package com.contunder.wankulapi.Data.Mapper;

import com.contunder.wankulapi.Application.Model.Jewel;
import com.contunder.wankulapi.Application.Model.JewelColor;
import com.contunder.wankulapi.Application.Model.JewelPosition;
import com.contunder.wankulapi.Data.Entity.JewelEntity;

import java.util.HashMap;

import static com.contunder.wankulapi.Application.Enum.JewelEnum.PURPLE;
import static com.contunder.wankulapi.Application.Enum.JewelEnum.YELLOW;

public class JewelMapper {

    public JewelEntity mapModelToData(int characterNumber, Jewel jewel){

        return new JewelEntity(
                characterNumber,
                jewel.getJewel().get(YELLOW.getName()).getPosition(),
                jewel.getJewel().get(PURPLE.getName()).getPosition()
        );
    }

    public Jewel mapDataToModel(JewelEntity jewelEntity){
        HashMap<JewelColor, JewelPosition> jewel = new HashMap<JewelColor, JewelPosition>();
        jewel.put(new JewelColor(YELLOW.getName()), new JewelPosition(jewelEntity.getYellowPosition()));
        jewel.put(new JewelColor(PURPLE.getName()), new JewelPosition(jewelEntity.getPurplePosition()));

        return new Jewel(jewel);
    }
}
