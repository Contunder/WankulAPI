package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Enum.RarityEnum;
import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import org.springframework.data.domain.Page;

public interface CardService {

    CardResponse getAllCard(Pageable pageable);

    CardResponse getAllCardByRarity(Pageable pageable, String rarity);

    Card getCardById(int id);
}
