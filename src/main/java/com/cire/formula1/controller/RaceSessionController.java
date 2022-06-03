package com.cire.formula1.controller;

import com.cire.formula1.model.RaceSession;
import com.cire.formula1.model.RaceSessions;
import com.cire.formula1.service.RaceSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/sessions")
public class RaceSessionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RaceSessionController.class);

    private final RaceSessionService raceSessionService;

    @Autowired
    public RaceSessionController(RaceSessionService raceSessionService) {
        this.raceSessionService = raceSessionService;
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

}
