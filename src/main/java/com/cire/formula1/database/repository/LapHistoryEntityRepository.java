package com.cire.formula1.database.repository;

import com.cire.formula1.database.entity.LapHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface LapHistoryEntityRepository extends JpaRepository<LapHistoryEntity, Long> {

    Optional<List<LapHistoryEntity>> findBySessionHistoryData_Player_RaceSession_TrackNameOrderByLapTimeAsc(String trackName);

}
