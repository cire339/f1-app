package com.cire.formula1.database;

import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.model.RaceSession;

import java.math.BigInteger;
import java.util.Optional;

public interface FormulaOneDao {

    RaceSessionEntity createRaceSession(RaceSession session);

    Optional<RaceSessionEntity> getRaceSessionByUid(BigInteger sessionUid);

    void deleteRaceSession(RaceSessionEntity sessionUid);

}
