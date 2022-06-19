package com.cire.formula1.controller;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.model.dto.RaceSessionDTO;
import com.cire.formula1.service.GraphService;
import com.cire.formula1.service.RaceSessionService;
import com.cire.formula1.service.UdpClient;
import com.cire.formula1.service.UdpClientImpl;
import com.cire.formula1.utils.DataUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.svg.SVGGraphics2D;
import org.jfree.svg.SVGUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/test")
class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private final UdpClient udpClient;
    private final FormulaOneDao formulaOneDao;
    private final RaceSessionService raceSessionService;
    private final GraphService graphService;

    @Autowired
    public TestController(UdpClientImpl udpClient, FormulaOneDao formulaOneDao, RaceSessionService raceSessionService, GraphService graphService) {
        this.udpClient = udpClient;
        this.formulaOneDao = formulaOneDao;
        this.raceSessionService = raceSessionService;
        this.graphService = graphService;
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

    @GetMapping(value = {"/{sessionUid}/player-position-graph"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTestPlayerPositionGraph(@PathVariable BigInteger sessionUid) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=PlayerPositions.svg");

        JFreeChart chart = graphService.createPlayerPositionGraph(raceSessionService.getRaceSessionByUid(sessionUid).getPlayerPositionDataSet());
        SVGGraphics2D g2 = new SVGGraphics2D(1800, 1200);
        g2.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        Rectangle r = new Rectangle(0, 0, 1800, 1200);
        chart.draw(g2, r);
        File f = new File("PlayerPositions.svg");
        SVGUtils.writeToSVG(f, g2.getSVGElement());

        InputStreamResource resource = new InputStreamResource(new FileInputStream(f));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(f.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }

    @GetMapping(value = {"/{sessionUid}/{carIndex}/car-motion-graph"}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCarMotionGraph(@PathVariable BigInteger sessionUid,
                                               @PathVariable Integer carIndex) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=CarMotion.svg");

        JFreeChart chart = graphService.createMotionGraph(raceSessionService.getRaceSessionByUid(sessionUid).getPlayers().get(carIndex).getMotionDataSet());
        SVGGraphics2D g2 = new SVGGraphics2D(1800, 1200);
        g2.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        Rectangle r = new Rectangle(0, 0, 1800, 1200);
        chart.draw(g2, r);
        File f = new File("CarMotion.svg");
        SVGUtils.writeToSVG(f, g2.getSVGElement());

        InputStreamResource resource = new InputStreamResource(new FileInputStream(f));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(f.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }

}
