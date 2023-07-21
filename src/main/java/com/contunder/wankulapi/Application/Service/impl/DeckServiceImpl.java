package com.contunder.wankulapi.Application.Service.impl;

import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Service.DeckService;
import com.contunder.wankulapi.Data.Entity.CardEntity;
import com.contunder.wankulapi.Data.Entity.UserEntity;
import com.contunder.wankulapi.Data.Mapper.CardMapper;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import com.contunder.wankulapi.Data.Repository.CardRepository;
import com.contunder.wankulapi.Data.Repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckServiceImpl implements DeckService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public DeckServiceImpl(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.cardMapper = new CardMapper();
    }

    @Override
    public CardResponse getAllMyCard(int pageNo, int pageSize, String sortBy, String sortDir, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: "+ email));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        List<Card> card = user.getCollection().stream().map(cardMapper::mapDataToModel).collect(Collectors.toList());
        Page<CardEntity> cardPage = new PageImpl(user.getCollection(), pageable, card.size());

        return cardMapper.mapModelToResponse(card, cardPage);
    }

    @Override
    public String addCardByCardNumber(int cardNumber, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: "+ email));
        CardEntity card = cardRepository.findById(cardNumber).orElseThrow(() ->
                new UsernameNotFoundException("Card not found with number: "+ cardNumber));

        List<CardEntity> cardEntities = user.getCollection();
        cardEntities.add(card);
        user.setCollection(cardEntities);
        userRepository.save(user);
        return "Card n°" + cardNumber + " add successfully!.";
    }
}
