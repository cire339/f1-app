package com.cire.formula1.database;

import com.cire.formula1.database.entity.LapHistoryDataEntity;
import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.database.entity.SessionHistoryDataEntity;
import com.cire.formula1.database.repository.RaceSessionEntityRepository;
import com.cire.formula1.model.RaceSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class FormulaOneDaoImpl implements FormulaOneDao {

    private RaceSessionEntityRepository raceSessionRepo;

    @Autowired
    public FormulaOneDaoImpl(RaceSessionEntityRepository raceSessionRepo) {
        this.raceSessionRepo = raceSessionRepo;
    }

    @Override
    public RaceSessionEntity createRaceSession(RaceSession session) {
        //Set IDs to 0 for some reason...
        RaceSessionEntity raceSessionEntity = new RaceSessionEntity(session);
        raceSessionEntity.setId(0);
        for(PlayerEntity pe: raceSessionEntity.getPlayers()){
            pe.setId(0);
            pe.setRaceSessionId(0);
            if(pe.getSessionHistoryData() != null) {
                pe.getSessionHistoryData().setId(0);
                pe.getSessionHistoryData().setPlayerId(0);
                if(pe.getSessionHistoryData().getLapHistoryData() != null) {
                    for (LapHistoryDataEntity ld : pe.getSessionHistoryData().getLapHistoryData()) {
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
    public void deleteRaceSession(RaceSessionEntity session) {
        raceSessionRepo.delete(session);
    }


}
