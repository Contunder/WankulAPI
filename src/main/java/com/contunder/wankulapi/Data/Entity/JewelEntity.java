package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Jewel")
public class JewelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean yellow;
    private boolean purple;
    private boolean positionLeft;
    private boolean positionRight;
}
