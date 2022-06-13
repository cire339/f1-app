package com.cire.formula1.database.repository;

import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface LapHistoryEntityRepository extends CrudRepository<LapHistoryEntity, Long> {

}
