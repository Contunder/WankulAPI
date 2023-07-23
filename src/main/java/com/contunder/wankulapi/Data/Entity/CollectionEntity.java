package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Collection")
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardNumber", nullable = false)
    private CardEntity card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;


    private int numberOfCard;

    public CollectionEntity(CardEntity card, UserEntity user, int numberOfCard) {
        this.card = card;
        this.user = user;
        this.numberOfCard = numberOfCard;
    }

    public CollectionEntity() {

    }

    public long getId() {
        return id;
    }

    public CardEntity getCard() {
        return card;
    }

    public UserEntity getUser() {
        return user;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public void setNumberOfCard(int numberOfCard) {
        this.numberOfCard = numberOfCard;
    }
}
