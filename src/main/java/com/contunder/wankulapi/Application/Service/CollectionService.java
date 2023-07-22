package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Data.Payload.CardResponse;


public interface CollectionService {

    CardResponse getAllMyCard(Pageable pageable, String email);

    CardResponse getAllMyCardByRarity(Pageable pageable, String email, String rarity);

    CardResponse getCollectionByPseudo(Pageable pageable, String pseudo);

    String addCardByCardNumber(int cardNumber, String email);

    String deleteCardByCardNumber(int cardNumber, String email);
}
