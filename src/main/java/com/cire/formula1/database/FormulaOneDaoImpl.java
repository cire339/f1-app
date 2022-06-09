package com.cire.formula1.database;

import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.database.repository.RaceSessionEntityRepository;
import com.cire.formula1.model.dto.RaceSessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormulaOneDaoImpl implements FormulaOneDao {

    private final RaceSessionEntityRepository raceSessionRepo;

    @Autowired
    public FormulaOneDaoImpl(RaceSessionEntityRepository raceSessionRepo) {
        this.raceSessionRepo = raceSessionRepo;
    }

    @Override
    public RaceSessionEntity createRaceSession(RaceSessionDTO session) {
        //Set IDs to 0 for some reason...
        RaceSessionEntity raceSessionEntity = new RaceSessionEntity(session);
        raceSessionEntity.setId(0);
        for(PlayerEntity pe: raceSessionEntity.getPlayers()){
            pe.setId(0);
            pe.setRaceSessionId(0);
            if(pe.getSessionHistory() != null) {
                pe.getSessionHistory().setId(0);
                pe.getSessionHistory().setPlayerId(0);
                if(pe.getSessionHistory().getLapHistory() != null) {
                    for (LapHistoryEntity ld : pe.getSessionHistory().getLapHistory()) {
                        ld.setId(0);
                        ld.setSessionHistoryDataId(0);
                    }
                }
            }
        }
        return raceSessionRepo.save(raceSessionEntity);
    }

    @Override
    public Optional<RaceSessionEntity> getRaceSessionByUid(BigInteger sessionUid) {
        return raceSessionRepo.findBySessionUid(sessionUid.toString());
    }

    @Override
    public List<BigInteger> getAllRaceSessions() {
        Iterable<RaceSessionEntity> sessions =  raceSessionRepo.findAll();
        List<BigInteger> sessionUidList = new ArrayList<>();
        sessions.forEach(session -> sessionUidList.add(new BigInteger(session.getSessionUid())));
        return sessionUidList;
    }

    @Override
    public void deleteRaceSession(RaceSessionEntity session) {
        raceSessionRepo.delete(session);
    }

    @Override
    public RaceSessionEntity updateRaceSession(RaceSessionDTO session) {
        return raceSessionRepo.save(new RaceSessionEntity(session));
    }


}
