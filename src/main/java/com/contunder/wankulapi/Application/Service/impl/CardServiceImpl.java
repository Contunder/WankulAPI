package com.contunder.wankulapi.Application.Service.impl;

import com.contunder.wankulapi.Application.Exception.WankulAPIException;
import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Service.CardService;
import com.contunder.wankulapi.Data.Entity.CardEntity;
import com.contunder.wankulapi.Data.Mapper.CardMapper;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import com.contunder.wankulapi.Data.Repository.CardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.CARD_NOT_FOUND;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        this.cardMapper = new CardMapper();
    }

    @Override
    public CardResponse getAllCard(int pageNo, int pageSize, String sortBy, String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<CardEntity> cardPage = cardRepository.findAll(pageable);

        return cardMapper.mapModelToResponse(
                cardPage.getContent().stream()
                        .map(cardMapper::mapDataToModel)
                        .collect(Collectors.toList()),
                cardPage
        );

    }

    @Override
    public Card getCardById(int cardNumber){

        return cardMapper.mapDataToModel(
                cardRepository.findById(cardNumber)
                        .orElseThrow(() ->
                                new WankulAPIException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND + cardNumber)
                        )
        );
    }
}
