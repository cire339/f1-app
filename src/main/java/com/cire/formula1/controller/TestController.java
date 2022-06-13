package com.cire.formula1.controller;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.model.dto.RaceSessionDTO;
import com.cire.formula1.service.UdpClient;
import com.cire.formula1.service.UdpClientImpl;
import com.cire.formula1.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/test")
class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private final UdpClient udpClient;
    private final FormulaOneDao formulaOneDao;

    @Autowired
    public TestController(UdpClientImpl udpClient, FormulaOneDao formulaOneDao) {
        this.udpClient = udpClient;
        this.formulaOneDao = formulaOneDao;
    }

    @PostMapping(value = {"/packet"}, produces = {APPLICATION_JSON_VALUE})
    public void sendMessage(@RequestBody String message) {
        LOGGER.info("Received HTTP POST message: {}", message);
        //Convert message to
        udpClient.sendMessage(message);
    }

    //TEST ENDPOINTS - TODO: REMOVE
    @PostMapping(value = {"/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createTestSession(@PathVariable BigInteger sessionUid) {

        RaceSessionDTO raceSession = DataUtils.createTestSession(sessionUid);

        RaceSessionEntity raceSessionEntity = formulaOneDao.createRaceSession(raceSession);

        return new ResponseEntity<>(new RaceSessionDTO(raceSessionEntity), HttpStatus.OK);
    }

    @GetMapping(value = {"/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTestSession(@PathVariable BigInteger sessionUid) {

        RaceSessionDTO raceSession = DataUtils.createTestSession(sessionUid);

        return new ResponseEntity<>(raceSession, HttpStatus.OK);
    }

}
