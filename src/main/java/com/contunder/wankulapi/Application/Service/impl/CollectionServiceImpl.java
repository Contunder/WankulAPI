package com.contunder.wankulapi.Application.Service.impl;

import com.contunder.wankulapi.Application.Exception.WankulAPIException;
import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Application.Service.CollectionService;
import com.contunder.wankulapi.Application.Service.UserService;
import com.contunder.wankulapi.Data.Entity.CardEntity;
import com.contunder.wankulapi.Data.Entity.UserEntity;
import com.contunder.wankulapi.Data.Mapper.CardMapper;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import com.contunder.wankulapi.Data.Repository.CardRepository;
import com.contunder.wankulapi.Data.Repository.UserRepository;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.*;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final UserService userService;
    private final CardMapper cardMapper;

    public CollectionServiceImpl(UserRepository userRepository, CardRepository cardRepository, UserService userService) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.userService = userService;
        this.cardMapper = new CardMapper();
    }

    @Override
    public CardResponse getAllMyCard(Pageable pageable, String email) {
        UserEntity user = userService.getUserEntity(email);
        List<Card> card = user.getCollection().stream()
                .map(cardMapper::mapDataToModel)
                .sorted(Comparator.comparingInt(Card::getCardNumber))
                .collect(Collectors.toList());

        return cardMapper.mapModelToResponse(card, new PageImpl<>(user.getCollection(), pageable.getPage(), card.size()));
    }

    @Override
    public CardResponse getCollectionByPseudo(Pageable pageable, String pseudo) {
        UserEntity user = userService.getUserEntityByPseudo(pseudo);
        List<Card> card = user.getCollection().stream()
                .map(cardMapper::mapDataToModel)
                .sorted(Comparator.comparingInt(Card::getCardNumber))
                .collect(Collectors.toList());

        return cardMapper.mapModelToResponse(card, new PageImpl<>(user.getCollection(), pageable.getPage(), card.size()));
    }

    @Override
    public String addCardByCardNumber(int cardNumber, String email) {
        UserEntity user = userService.getUserEntity(email);
        CardEntity card = cardRepository.findById(cardNumber).orElseThrow(() ->
                new WankulAPIException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND + cardNumber));

        List<CardEntity> cardEntities = user.getCollection();
        cardEntities.add(card);
        user.setCollection(cardEntities);
        userRepository.save(user);

        return "La carte " + card.getEffigy() + " " + card.getName() + COLLECTION_ADD;
    }

    @Override
    public String deleteCardByCardNumber(int cardNumber, String email) {
        UserEntity user = userService.getUserEntity(email);
        CardEntity card = cardRepository.findById(cardNumber).orElseThrow(() ->
                new WankulAPIException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND + cardNumber));

        List<CardEntity> cardEntities = user.getCollection();
        cardEntities.remove(card);
        user.setCollection(cardEntities);
        userRepository.save(user);

        return "La carte " + card.getEffigy() + " " + card.getName() + COLLECTION_DELETE;
    }

}
