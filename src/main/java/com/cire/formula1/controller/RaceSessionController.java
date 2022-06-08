package com.cire.formula1.controller;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.model.Player;
import com.cire.formula1.model.RaceSession;
import com.cire.formula1.model.RaceSessions;
import com.cire.formula1.model.SessionHistoryData;
import com.cire.formula1.packet.model.data.LapHistoryData;
import com.cire.formula1.service.RaceSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/sessions")
public class RaceSessionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RaceSessionController.class);

    private final RaceSessionService raceSessionService;

    private final FormulaOneDao formulaOneDao;

    @Autowired
    public RaceSessionController(RaceSessionService raceSessionService, FormulaOneDao formulaOneDao) {
        this.raceSessionService = raceSessionService;
        this.formulaOneDao = formulaOneDao;
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllSessions() {
        List<BigInteger> raceSessions = raceSessionService.getRaceAllSessions();
        if(raceSessions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new RaceSessions(raceSessions), HttpStatus.OK);
    }

    @GetMapping(value = {"/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSessionById(@PathVariable BigInteger sessionUid) {
        RaceSession raceSession = raceSessionService.getRaceSessionByUid(sessionUid);
        if(raceSession == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(raceSession, HttpStatus.OK);
    }

    @PostMapping(value = {"/db/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createSessionInDb(@PathVariable BigInteger sessionUid) {
        RaceSession raceSession = new RaceSession();
        raceSession.setSessionUid(sessionUid);

        RaceSessionEntity raceSessionEntity = formulaOneDao.createRaceSession(raceSession);

        return new ResponseEntity<>(new RaceSession(raceSessionEntity), HttpStatus.OK);
    }

    @GetMapping(value = {"/db/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSessionByIdFromDb(@PathVariable BigInteger sessionUid) {
        Optional<RaceSessionEntity> raceSession = formulaOneDao.getRaceSessionByUid(sessionUid);
        if(raceSession.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new RaceSession(raceSession.get()), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/db/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteSessionFromDb(@PathVariable BigInteger sessionUid) {

        Optional<RaceSessionEntity> session = formulaOneDao.getRaceSessionByUid(sessionUid);
        if(session.isPresent()) {
            formulaOneDao.deleteRaceSession(session.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //TEST ENDPOINT - TODO: REMOVE
    @PostMapping(value = {"/db/{sessionUid}/test"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createTestSessionInDb(@PathVariable BigInteger sessionUid) {
        RaceSession raceSession = new RaceSession();
        raceSession.setSessionUid(sessionUid);
        raceSession.setRaceStarted(true);
        raceSession.setRaceEnded(true);
        raceSession.setRaceWinnerCarIndex((short)0);
        raceSession.setFastestSpeed(321.0F);
        raceSession.setFastestSpeedCarIndex((short)0);
        List<Player> playerList = new ArrayList<>();
        Player player = new Player((short)0);
        SessionHistoryData sessionHistoryData = new SessionHistoryData();
        sessionHistoryData.setNumLaps((short) 16);
        sessionHistoryData.setNumTyreStints((short)2);
        sessionHistoryData.setBestLapTimeLapNum((short)3);
        sessionHistoryData.setBestSector1LapNum((short)4);
        sessionHistoryData.setBestSector2LapNum((short)2);
        sessionHistoryData.setBestSector3LapNum((short)5);
        List<LapHistoryData> lapHistoryDataList = new ArrayList<>();
        LapHistoryData lapHistoryData = new LapHistoryData();
        lapHistoryData.setLapTimeInMS(73546);
        lapHistoryData.setSector1TimeInMS(22515);
        lapHistoryData.setSector2TimeInMS(26515);
        lapHistoryData.setSector3TimeInMS(24516);
        lapHistoryDataList.add(lapHistoryData);

        sessionHistoryData.setLapHistoryData(lapHistoryDataList);
        player.setPlayerName("Eric");
        player.setSessionHistoryData(sessionHistoryData);
        playerList.add(player);
        raceSession.setPlayers(playerList);

        RaceSessionEntity raceSessionEntity = formulaOneDao.createRaceSession(raceSession);

        return new ResponseEntity<>(new RaceSession(raceSessionEntity), HttpStatus.OK);
    }

}
