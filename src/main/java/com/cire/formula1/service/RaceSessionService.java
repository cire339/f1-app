package com.cire.formula1.service;

import com.cire.formula1.model.RaceSession;

import java.math.BigInteger;
import java.util.List;

public interface RaceSessionService {

    RaceSession createRaceSession(BigInteger sessionUid);

    RaceSession getRaceSessionByUid(BigInteger sessionUid);

    List<BigInteger> getRaceAllSessions();
}
