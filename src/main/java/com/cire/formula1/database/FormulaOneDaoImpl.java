package com.cire.formula1.database;

import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.database.entity.PenaltyEntity;
import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.database.repository.LapHistoryEntityRepository;
import com.cire.formula1.database.repository.PlayerEntityRepository;
import com.cire.formula1.database.repository.RaceSessionEntityRepository;
import com.cire.formula1.model.FastestLapInfo;
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
    public List<FastestLapInfo> getFastestLapsByTrackName(String trackName, Integer numberOfLaps) {
        Optional<List<LapHistoryEntity>> lapHistoryByTrack = lapHistoryRepo.findBySessionHistoryData_Player_RaceSession_TrackNameOrderByLapTimeAsc(trackName);
        if(lapHistoryByTrack.isEmpty()){
            return null;
        }else{
            List<LapHistoryEntity> allLaps = lapHistoryByTrack.get();
            List<LapHistoryEntity> validLaps = new ArrayList<>();
            List<FastestLapInfo> fastestLapsInfo = new ArrayList<>();

            //Exclude incomplete / invalid laps.
            for(LapHistoryEntity lap: allLaps){
                if(lap.getLapTime() != 0 && lap.getSector1Time() != 0 && lap.getSector2Time() != 0 && lap.getSector3Time() != 0 && lap.getLapValidFlag() == 15){
                    validLaps.add(lap);
                }
            }

            //Can't return more than the number of laps.
            if(numberOfLaps > validLaps.size()){
                numberOfLaps = validLaps.size();
            }

            for(int i = 0; i<numberOfLaps; i++){
                fastestLapsInfo.add(new FastestLapInfo(validLaps.get(i)));
            }

            return fastestLapsInfo;
        }
    }

}
