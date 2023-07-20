package com.contunder.wankulapi.Data.Repository;

import com.contunder.wankulapi.Data.Entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    Optional<CardEntity> findById(int id);
}
