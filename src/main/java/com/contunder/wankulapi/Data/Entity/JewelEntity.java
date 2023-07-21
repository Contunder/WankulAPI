package com.contunder.wankulapi.Data.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Jewel")
public class JewelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String yellowPosition;
    private String purplePosition;

    public JewelEntity(int characterNumber, String yellowPosition, String purplePosition) {
        this.id = characterNumber;
        this.yellowPosition = yellowPosition;
        this.purplePosition = purplePosition;
    }

    public String getYellowPosition() {
        return yellowPosition;
    }

    public String getPurplePosition() {
        return purplePosition;
    }
}
