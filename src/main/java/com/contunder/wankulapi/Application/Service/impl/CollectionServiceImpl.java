package com.contunder.wankulapi.Application.Service.impl;

import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Application.Service.CollectionService;
import com.contunder.wankulapi.Data.Entity.CardEntity;
import com.contunder.wankulapi.Data.Entity.UserEntity;
import com.contunder.wankulapi.Data.Mapper.CardMapper;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import com.contunder.wankulapi.Data.Repository.CardRepository;
import com.contunder.wankulapi.Data.Repository.UserRepository;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.CARD_NOT_FOUND;
import static com.contunder.wankulapi.Application.Enum.MessageConstant.COLLECTION_ADD;
import static com.contunder.wankulapi.Application.Enum.MessageConstant.USER_NOT_FOUND;

@Service
@PropertySource("classpath:message.properties")
public class CollectionServiceImpl implements CollectionService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CollectionServiceImpl(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.cardMapper = new CardMapper();
    }

    @Override
    public CardResponse getAllMyCard(Pageable pageable, String email) {
        UserEntity user = getUserEntity(email);
        List<Card> card = user.getCollection().stream().map(cardMapper::mapDataToModel).collect(Collectors.toList());

        return cardMapper.mapModelToResponse(card, new PageImpl<>(user.getCollection(), pageable.getPage(), card.size()));
    }

    @Override
    public String addCardByCardNumber(int cardNumber, String email) {
        UserEntity user = getUserEntity(email);
        CardEntity card = cardRepository.findById(cardNumber).orElseThrow(() ->
                new UsernameNotFoundException(CARD_NOT_FOUND + cardNumber));

        List<CardEntity> cardEntities = user.getCollection();
        cardEntities.add(card);
        user.setCollection(cardEntities);
        userRepository.save(user);

        return "La carte " + card.getEffigy() + " " + card.getName() + COLLECTION_ADD;
    }

    public UserEntity getUserEntity(String email){

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }
}
