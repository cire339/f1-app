package com.cire.formula1.service;

import com.cire.formula1.model.RaceSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class RaceSessionServiceImpl implements RaceSessionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RaceSessionServiceImpl.class);

    Map<BigInteger, RaceSession> raceSessions = new HashMap<>();

    @Override
    public synchronized RaceSession createRaceSession(BigInteger sessionUid) {
        if(raceSessions.containsKey(sessionUid)){
            LOGGER.info("RaceSession with UID=" + sessionUid + " already exists.");
        }else{
            //Create new session and add it to the array.
            RaceSession raceSession = new RaceSession();
            raceSession.setSessionUid(sessionUid);
            raceSessions.put(sessionUid, raceSession);
        }
        return raceSessions.get(sessionUid);
    }

    @Override
    public RaceSession getRaceSessionByUid(BigInteger sessionUid) {
        if(raceSessions.containsKey(sessionUid)){
            return raceSessions.get(sessionUid);
        }else{
            LOGGER.info("RaceSession with UID=" + sessionUid + " does not exist.");
            return createRaceSession(sessionUid);
        }
    }


}
