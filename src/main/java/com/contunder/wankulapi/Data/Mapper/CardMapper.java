package com.contunder.wankulapi.Data.Mapper;


import com.contunder.wankulapi.Application.Enum.TypeEnum;
import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Data.Entity.CardEntity;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public class CardMapper {

    public CardEntity mapModelToData(Card card){

        return new CardEntity(
                card.getCardNumber(),
                card.getName(),
                card.getRarity(),
                card.getArtist(),
                card.getEffigy()
        );
    }

    public Card mapDataToModel(CardEntity cardEntity){

        if(cardEntity.getRarity().equals("Terrain")){
            return new Card(
                    cardEntity.getId(),
                    cardEntity.getName(),
                    cardEntity.getArtist(),
                    cardEntity.getRarity(),
                    TypeEnum.TERRAIN,
                    cardEntity.getEffigy()
            );
        }

        return new Card(
                cardEntity.getId(),
                cardEntity.getName(),
                cardEntity.getArtist(),
                cardEntity.getRarity(),
                TypeEnum.PERSONNAGE,
                cardEntity.getEffigy()
        );
    }

    public CardResponse mapModelToResponse(List<Card> cards, Page<CardEntity> cardPage){
        return new CardResponse(
                cards,
                cardPage.getNumber(),
                cardPage.getSize(),
                cardPage.getTotalElements(),
                cardPage.getTotalPages(),
                cardPage.isLast()
        );
    }
}
