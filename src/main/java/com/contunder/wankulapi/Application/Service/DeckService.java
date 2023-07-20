package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Data.Payload.CardResponse;


public interface DeckService {

    CardResponse getAllMyCard(int pageNo, int pageSize, String sortBy, String sortDir, String email);

    String addCardByCardNumber(int cardNumber, String email);
}
