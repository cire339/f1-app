package com.cire.formula1.database;

import com.cire.formula1.controller.RaceSessionController;
import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.database.entity.PenaltyEntity;
import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.database.repository.LapHistoryEntityRepository;
import com.cire.formula1.database.repository.PlayerEntityRepository;
import com.cire.formula1.database.repository.RaceSessionEntityRepository;
import com.cire.formula1.model.dto.LapHistoryDTO;
import com.cire.formula1.model.dto.RaceSessionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormulaOneDaoImpl implements FormulaOneDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(FormulaOneDaoImpl.class);

    private final RaceSessionEntityRepository raceSessionRepo;
    private final PlayerEntityRepository playerRepo;
    private final LapHistoryEntityRepository lapHistoryRepo;

    @Autowired
    public FormulaOneDaoImpl(RaceSessionEntityRepository raceSessionRepo, PlayerEntityRepository playerRepo, LapHistoryEntityRepository lapHistoryRepo) {
        this.raceSessionRepo = raceSessionRepo;
        this.playerRepo = playerRepo;
        this.lapHistoryRepo = lapHistoryRepo;
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
            if(pe.getPenalties() != null){
                for(PenaltyEntity penalty: pe.getPenalties()){
                    penalty.setId(0);
                    penalty.setPlayerId(0);
                }
            }
            if(pe.getCarSetup() != null){
                pe.getCarSetup().setId(0);
                pe.getCarSetup().setPlayerId(0);
            }
            if(pe.getFinalClassification() != null){
                pe.getFinalClassification().setId(0);
                pe.getFinalClassification().setPlayerId(0);
            }
        }
        return raceSessionRepo.saveAndFlush(raceSessionEntity);
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
        return raceSessionRepo.saveAndFlush(new RaceSessionEntity(session));
    }

    @Override
    public List<PlayerEntity> getPlayerByRaceSessionUid(BigInteger sessionUid) {
        return playerRepo.findPlayerEntityByRaceSession_SessionUid(sessionUid.toString());
    }

    @Override
    public LapHistoryDTO getFastestLapByTrackName(String trackName) {
        Optional<List<LapHistoryEntity>> lapHistoryByTrack = lapHistoryRepo.findBySessionHistoryData_Player_RaceSession_TrackName(trackName);
        if(lapHistoryByTrack.isEmpty()){
            return null;
        }else{
            List<LapHistoryEntity> allLaps = lapHistoryByTrack.get();
            LapHistoryEntity fastestLap = null;
            for(LapHistoryEntity lap: allLaps){
                //Find the fastest lap. //TODO: Improve this logic to include ties
                if(fastestLap == null || (lap.getLapTime() > 0 && fastestLap.getLapTime() > lap.getLapTime() && lap.getLapValidFlag() == 15)){
                    fastestLap = lap;
                }
            }
            LOGGER.info("Found fastest lap for " + trackName + " by player " + fastestLap.getSessionHistoryData().getPlayer().getPlayerName() + " with a time of " + fastestLap.getLapTime());
            return new LapHistoryDTO(fastestLap);
        }
    }

}
