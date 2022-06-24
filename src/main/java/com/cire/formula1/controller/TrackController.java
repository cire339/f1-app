package com.cire.formula1.controller;

import com.cire.formula1.packet.model.constants.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/tracks")
public class TrackController {

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTracks() {
        List<Track> tracks = Arrays.stream(Track.values()).toList();

        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }
}
