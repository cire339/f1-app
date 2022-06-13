package com.cire.formula1.controller;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.model.RaceSessions;
import com.cire.formula1.model.dto.PlayerDTO;
import com.cire.formula1.model.dto.RaceSessionDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    private final FormulaOneDao formulaOneDao;

    @Autowired
    public PlayerController(FormulaOneDao formulaOneDao) {
        this.formulaOneDao = formulaOneDao;
    }

    @GetMapping(value = {"/{sessionUid}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getPlayersBySessionId(@PathVariable BigInteger sessionUid) {
        List<PlayerEntity> playerEntities = formulaOneDao.getPlayerByRaceSessionUid(sessionUid);
        if(playerEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<PlayerDTO> players = new ArrayList<>();
        for(PlayerEntity playerEntity: playerEntities){
            players.add(new PlayerDTO(playerEntity));
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

}
