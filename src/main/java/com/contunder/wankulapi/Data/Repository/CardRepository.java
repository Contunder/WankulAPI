package com.contunder.wankulapi.Data.Repository;

import com.contunder.wankulapi.Data.Entity.CardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    Optional<CardEntity> findById(int id);
    Optional<Page<CardEntity>> findAllByRarityIsContaining(String rarity, Pageable page);
}
