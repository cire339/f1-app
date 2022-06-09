package com.cire.formula1.service;

import com.cire.formula1.model.dto.RaceSessionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RaceSessionServiceImpl implements RaceSessionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RaceSessionServiceImpl.class);

    Map<BigInteger, RaceSessionDTO> raceSessions = new HashMap<>();

    public RaceSessionServiceImpl() {
    }

    @Override
    public synchronized RaceSessionDTO createRaceSession(BigInteger sessionUid) {
        if(raceSessions.containsKey(sessionUid)){
            LOGGER.info("RaceSession with UID=" + sessionUid + " already exists.");
        }else{
            //Create new session and add it to the array. This will go away at some point.
            RaceSessionDTO raceSessionDTO = new RaceSessionDTO();
            raceSessionDTO.setSessionUid(sessionUid);
            raceSessions.put(sessionUid, raceSessionDTO);
        }
        return raceSessions.get(sessionUid);
    }

    @Override
    public RaceSessionDTO getRaceSessionByUid(BigInteger sessionUid) {
        if(raceSessions.containsKey(sessionUid)){
            return raceSessions.get(sessionUid);
        }else{
            LOGGER.debug("RaceSession with UID=" + sessionUid + " does not exist.");
            return createRaceSession(sessionUid);
        }
    }

    @Override
    public List<BigInteger> getRaceAllSessions() {
        return raceSessions.keySet().stream().toList();
    }

}
