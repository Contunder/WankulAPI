package com.contunder.wankulapi.Application.Service.impl;

import com.contunder.wankulapi.Application.Exception.WankulAPIException;
import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Model.Collection;
import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Application.Service.CollectionService;
import com.contunder.wankulapi.Application.Service.UserService;
import com.contunder.wankulapi.Data.Entity.CardEntity;
import com.contunder.wankulapi.Data.Entity.CollectionEntity;
import com.contunder.wankulapi.Data.Entity.UserEntity;
import com.contunder.wankulapi.Data.Mapper.CardMapper;
import com.contunder.wankulapi.Data.Mapper.CollectionMapper;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import com.contunder.wankulapi.Data.Payload.CollectionResponse;
import com.contunder.wankulapi.Data.Repository.CardRepository;
import com.contunder.wankulapi.Data.Repository.CollectionRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.*;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final CardRepository cardRepository;
    private final UserService userService;
    private final CollectionMapper collectionMapper;
    private final CardMapper cardMapper;

    public CollectionServiceImpl(CollectionRepository collectionRepository, CardRepository cardRepository, UserService userService) {
        this.collectionRepository = collectionRepository;
        this.cardRepository = cardRepository;
        this.userService = userService;
        this.collectionMapper = new CollectionMapper();
        this.cardMapper = new CardMapper();
    }

    @Override
    public CollectionResponse getAllMyCard(Pageable pageable, String email) {
        UserEntity user = userService.getUserEntity(email);
        List<CollectionEntity> collection = collectionRepository.getAllByUser(user).orElseThrow(
                () -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)
        );
        List<Collection> collections = collection.stream()
                .map(collectionMapper::mapDataToModel)
                .sorted(Comparator.comparingInt(Collection::getCardNumber))
                .collect(Collectors.toList());

        return collectionMapper.mapModelToResponse(collections, pageable);
    }

    @Override
    public CollectionResponse getAllMyCardByRarity(Pageable pageable, String email, String rarity) {
        UserEntity user = userService.getUserEntity(email);
        List<CollectionEntity> collection = collectionRepository.getAllByUser(user).orElseThrow(
                () -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)
        );
        List<Collection> collections = Optional.of(collection.stream()
                .filter(collectionEntity -> collectionEntity.getCard().getRarity().toLowerCase().contains(rarity.toLowerCase()))
                .map(collectionMapper::mapDataToModel)
                .sorted(Comparator.comparingInt(Collection::getCardNumber))
                .collect(Collectors.toList()))
                .orElseThrow(() -> new WankulAPIException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND));


        return collectionMapper.mapModelToResponse(collections, pageable);
    }

    @Override
    public CollectionResponse getCollectionByPseudo(Pageable pageable, String pseudo) {
        UserEntity user = userService.getUserEntityByPseudo(pseudo);
        List<CollectionEntity> collection = collectionRepository.getAllByUser(user).orElseThrow(
                () -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)
        );
        List<Collection> collections = collection.stream()
                .map(collectionMapper::mapDataToModel)
                .sorted(Comparator.comparingInt(Collection::getCardNumber))
                .collect(Collectors.toList());

        return collectionMapper.mapModelToResponse(collections, pageable);
    }

    @Override
    public String addCardByCardNumber(int cardNumber, String email) {
        UserEntity user = userService.getUserEntity(email);
        List<CollectionEntity> collection = collectionRepository.getAllByUser(user).orElseThrow(
                () -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)
        );
        CardEntity card = cardRepository.findById(cardNumber).orElseThrow(() ->
                new WankulAPIException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND + cardNumber));

        Optional<CollectionEntity> update = collection.stream()
                .filter(collectionEntity -> collectionEntity.getCard().equals(card))
                .findFirst();

        update.ifPresent(collectionEntity -> collectionEntity.setNumberOfCard(collectionEntity.getNumberOfCard()+1));
        update.ifPresent(collectionRepository::save);

        collection.stream()
                .filter(collectionEntity -> collectionEntity.getCard().equals(card))
                .findAny()
                .orElseGet(() -> collectionRepository.save(new CollectionEntity(card, user, 1)));

        return "La carte " + card.getEffigy() + " " + card.getName() + COLLECTION_ADD;
    }

    @Override
    public String deleteCardByCardNumber(int cardNumber, String email) {
        UserEntity user = userService.getUserEntity(email);
        List<CollectionEntity> collection = collectionRepository.getAllByUser(user).orElseThrow(
                () -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)
        );
        CardEntity card = cardRepository.findById(cardNumber).orElseThrow(() ->
                new WankulAPIException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND + cardNumber));

        Optional<CollectionEntity> delete = collection.stream()
                .filter(collectionEntity -> collectionEntity.getCard().equals(card))
                .filter(collectionEntity -> collectionEntity.getNumberOfCard() < 1)
                .findFirst();

        delete.ifPresent(collectionRepository::delete);

        Optional<CollectionEntity> update = collection.stream()
                .filter(collectionEntity -> collectionEntity.getCard().equals(card))
                .filter(collectionEntity -> collectionEntity.getNumberOfCard() > 1)
                .findFirst();

        update.ifPresent(collectionEntity -> collectionEntity.setNumberOfCard(collectionEntity.getNumberOfCard()-1));
        update.ifPresent(collectionRepository::save);

        return "La carte " + card.getEffigy() + " " + card.getName() + COLLECTION_DELETE;
    }

}
