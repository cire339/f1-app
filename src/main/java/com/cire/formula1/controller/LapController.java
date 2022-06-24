package com.cire.formula1.controller;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.model.FastestLapInfo;
import com.cire.formula1.model.dto.LapHistoryDTO;
import com.cire.formula1.packet.model.constants.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/laps")
public class LapController {

    private final FormulaOneDao formulaOneDao;

    @Autowired
    public LapController(FormulaOneDao formulaOneDao) {
        this.formulaOneDao = formulaOneDao;
    }

    @GetMapping(value = {"/fastest-laps/{trackName}"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getFastestLapByTrack(@PathVariable String trackName, @RequestParam(value = "number-of-laps") Integer numberOfLaps) {

        try{
            Track.valueOf(trackName);
        }catch(IllegalArgumentException ex){
            //TODO: Implement error body
            return ResponseEntity.badRequest().body("Invalid track name.");
        }

        List<FastestLapInfo> fastestLaps = formulaOneDao.getFastestLapsByTrackName(trackName, numberOfLaps);
        if(fastestLaps == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(fastestLaps, HttpStatus.OK);
    }
}
