package com.contunder.wankulapi.Data.Mapper;

import com.contunder.wankulapi.Application.Model.Combo;
import com.contunder.wankulapi.Data.Entity.ComboEntity;

public class ComboMapper {

    private EffectMapper effectMapper;

    public ComboMapper() {
        this.effectMapper = new EffectMapper();
    }

    public ComboEntity mapModelToData(int characterNumber, Combo combo){

        return new ComboEntity(
                characterNumber,
                effectMapper.mapModelToData(characterNumber, combo.getEffet()),
                combo.getDescription(),
                combo.getDuree()
        );
    }

    public Combo mapDataToModel(ComboEntity comboEntity){

        return new Combo(
                comboEntity.getDescription(),
                comboEntity.getDuree(),
                effectMapper.mapDataToModel(comboEntity.getEffect())
        );
    }
}
