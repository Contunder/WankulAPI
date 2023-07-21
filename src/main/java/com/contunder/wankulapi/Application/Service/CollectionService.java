package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Data.Payload.CardResponse;


public interface CollectionService {

    CardResponse getAllMyCard(Pageable pageable, String email);

    String addCardByCardNumber(int cardNumber, String email);
}
