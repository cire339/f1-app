package com.cire.formula1.controller;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.model.dto.RaceSessionDTO;
import com.cire.formula1.model.RaceSessions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/sessions")
public class RaceSessionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RaceSessionController.class);

    private final FormulaOneDao formulaOneDao;

    @Autowired
    public RaceSessionController(FormulaOneDao formulaOneDao) {
        this.formulaOneDao = formulaOneDao;
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllSessions() {
        List<BigInteger> raceSessions = formulaOneDao.getAllRaceSessions();
        if(raceSessions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new RaceSessions(raceSessions), HttpStatus.OK);
    }

    @GetMapping(value = {"/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSessionById(@PathVariable BigInteger sessionUid) {
        Optional<RaceSessionEntity> raceSession = formulaOneDao.getRaceSessionByUid(sessionUid);
        if(raceSession.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new RaceSessionDTO(raceSession.get()), HttpStatus.OK);
    }

    /*
    @DeleteMapping(value = {"/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteSession(@PathVariable BigInteger sessionUid) {

        Optional<RaceSessionEntity> session = formulaOneDao.getRaceSessionByUid(sessionUid);
        if(session.isPresent()) {
            formulaOneDao.deleteRaceSession(session.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    */

}
