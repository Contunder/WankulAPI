package com.contunder.wankulapi.Data.Mapper;

import com.contunder.wankulapi.Application.Enum.TypeEnum;
import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Model.Collection;
import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Data.Entity.CardEntity;
import com.contunder.wankulapi.Data.Entity.CollectionEntity;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import com.contunder.wankulapi.Data.Payload.CollectionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class CollectionMapper {

    private CardMapper cardMapper;

    public CollectionMapper() {
        this.cardMapper = new CardMapper();
    }

    public Collection mapDataToModel(CollectionEntity collectionEntity){

        if(collectionEntity.getCard().getRarity().equals("Terrain")){
            return new Collection(
                    collectionEntity.getCard().getId(),
                    collectionEntity.getCard().getName(),
                    collectionEntity.getCard().getArtist(),
                    collectionEntity.getCard().getRarity(),
                    TypeEnum.TERRAIN,
                    collectionEntity.getCard().getEffigy(),
                    collectionEntity.getNumberOfCard()
            );
        }

        return new Collection(
                collectionEntity.getCard().getId(),
                collectionEntity.getCard().getName(),
                collectionEntity.getCard().getArtist(),
                collectionEntity.getCard().getRarity(),
                TypeEnum.PERSONNAGE,
                collectionEntity.getCard().getEffigy(),
                collectionEntity.getNumberOfCard()
        );
    }


    public CollectionResponse mapModelToResponse(List<Collection> collections, Pageable pageable){
        Page<Collection> cardPage = new PageImpl<>(collections, pageable.getPage(), collections.size());
        return new CollectionResponse(
                collections,
                cardPage.getNumber(),
                cardPage.getSize(),
                cardPage.getTotalElements(),
                cardPage.getTotalPages(),
                cardPage.isLast()
        );
    }

}
