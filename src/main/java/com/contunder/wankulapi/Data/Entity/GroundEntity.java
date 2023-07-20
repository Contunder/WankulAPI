package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Ground")
public class GroundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardNumber", nullable = false)
    private CardEntity card;

    private String winner;
    private String loser;
    private String special;
}
