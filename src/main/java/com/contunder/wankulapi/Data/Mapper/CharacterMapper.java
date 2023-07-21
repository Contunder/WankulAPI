package com.contunder.wankulapi.Data.Mapper;

import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Model.Character;
import com.contunder.wankulapi.Data.Entity.CharacterEntity;


public class CharacterMapper {

    private CardMapper cardMapper;
    private EffectMapper effectMapper;
    private ComboMapper comboMapper;
    private JewelMapper jewelMapper;

    public CharacterMapper() {
        this.cardMapper = new CardMapper();
        this.effectMapper = new EffectMapper();
        this.comboMapper = new ComboMapper();
        this.jewelMapper = new JewelMapper();
    }

    public CharacterEntity mapModelToData(Character character, Card card){

        return new CharacterEntity(
                cardMapper.mapModelToData(card),
                character.getCouts(),
                character.getPower(),
                comboMapper.mapModelToData(card.getCardNumber(), character.getCombo()),
                effectMapper.mapModelToData(card.getCardNumber(), character.getEffect()),
                jewelMapper.mapModelToData(card.getCardNumber(), character.getJewels()),
                character.getDescription()
        );
    }

    public Character mapDataToModel(CharacterEntity characterEntity){

        return new Character(
                characterEntity.getCosts(),
                characterEntity.getPower(),
                effectMapper.mapDataToModel(characterEntity.getEffect()),
                comboMapper.mapDataToModel(characterEntity.getCombo()),
                characterEntity.getDescription(),
                jewelMapper.mapDataToModel(characterEntity.getJewel())
        );
    }
}
