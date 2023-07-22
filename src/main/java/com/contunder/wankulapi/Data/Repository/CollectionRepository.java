package com.contunder.wankulapi.Data.Repository;

import com.contunder.wankulapi.Data.Entity.CollectionEntity;
import com.contunder.wankulapi.Data.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Integer> {

    Optional<List<CollectionEntity>> getAllByUser(UserEntity userId);
}
