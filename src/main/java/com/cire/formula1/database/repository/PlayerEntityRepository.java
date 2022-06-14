package com.cire.formula1.database.repository;

import com.cire.formula1.database.entity.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerEntityRepository extends CrudRepository<PlayerEntity, Long> {

    @Transactional
    List<PlayerEntity> findPlayerEntityByRaceSession_SessionUid(String sessionId);

}
