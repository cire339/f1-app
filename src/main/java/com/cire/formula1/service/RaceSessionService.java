package com.cire.formula1.service;

import com.cire.formula1.model.dto.RaceSessionDTO;

import java.math.BigInteger;
import java.util.List;

public interface RaceSessionService {

    RaceSessionDTO createRaceSession(BigInteger sessionUid);

    RaceSessionDTO getRaceSessionByUid(BigInteger sessionUid);

    List<BigInteger> getRaceAllSessions();

    RaceSessionDTO updateRaceSession(RaceSessionDTO raceSession);
}
