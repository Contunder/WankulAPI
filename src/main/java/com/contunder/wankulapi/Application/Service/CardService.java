package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Data.Payload.CardResponse;

public interface CardService {

    CardResponse getAllCard(int pageNo, int pageSize, String sortBy, String sortDir);

    Card getCardById(int id);
}
