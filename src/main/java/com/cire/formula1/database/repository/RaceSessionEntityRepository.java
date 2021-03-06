package com.cire.formula1.database.repository;

import com.cire.formula1.database.entity.RaceSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RaceSessionEntityRepository extends JpaRepository<RaceSessionEntity, Long> {

    @Transactional
    Optional<RaceSessionEntity> findBySessionUid(String uid);

}
