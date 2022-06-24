package com.cire.formula1.database;

import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.model.dto.LapHistoryDTO;
import com.cire.formula1.model.dto.RaceSessionDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface FormulaOneDao {

    RaceSessionEntity createRaceSession(RaceSessionDTO session);

    Optional<RaceSessionEntity> getRaceSessionByUid(BigInteger sessionUid);

    List<BigInteger> getAllRaceSessions();

    RaceSessionEntity updateRaceSession(RaceSessionDTO session);

    void deleteRaceSession(RaceSessionEntity sessionUid);

    List<PlayerEntity> getPlayerByRaceSessionUid(BigInteger sessionUid);

    LapHistoryDTO getFastestLapByTrackName(String trackName);
}
