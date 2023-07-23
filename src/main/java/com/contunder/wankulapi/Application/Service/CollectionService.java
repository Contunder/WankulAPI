package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import com.contunder.wankulapi.Data.Payload.CollectionResponse;


public interface CollectionService {

    CollectionResponse getAllMyCard(Pageable pageable, String email);

    CollectionResponse getAllMyCardByRarity(Pageable pageable, String email, String rarity);

    CollectionResponse getCollectionByPseudo(Pageable pageable, String pseudo);

    String addCardByCardNumber(int cardNumber, String email);

    String deleteCardByCardNumber(int cardNumber, String email);
}
