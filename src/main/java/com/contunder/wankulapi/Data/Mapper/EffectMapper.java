package com.contunder.wankulapi.Data.Mapper;

import com.contunder.wankulapi.Application.Model.Effect;
import com.contunder.wankulapi.Data.Entity.EffectEntity;

public class EffectMapper {

    public EffectEntity mapModelToData(Effect effect, int characterNumber){

        return new EffectEntity(
                characterNumber,
                effect.getDescription(),
                effect.getPick(),
                effect.getDiscard(),
                effect.getPower(),
                effect.isCondition()
        );
    }

    public Effect mapDataToModel(EffectEntity effectEntity){

        return new Effect(
                effectEntity.getDescription(),
                effectEntity.getPick(),
                effectEntity.getDiscard(),
                effectEntity.getPower(),
                effectEntity.isTerm()
        );
    }
}
