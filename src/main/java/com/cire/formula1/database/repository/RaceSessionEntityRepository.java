package com.cire.formula1.database.repository;

import com.cire.formula1.database.entity.RaceSessionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RaceSessionEntityRepository extends CrudRepository<RaceSessionEntity, Long> {

    Optional<RaceSessionEntity> findBySessionUid(String uid);

}
