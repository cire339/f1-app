package com.cire.formula1.controller;

import com.cire.formula1.service.UdpClient;
import com.cire.formula1.service.UdpClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/test")
class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private final UdpClient udpClient;

    @Autowired
    public TestController(UdpClientImpl udpClient) {
        this.udpClient = udpClient;
    }

    @PostMapping(value = {"/packet"}, produces = {APPLICATION_JSON_VALUE})
    public void sendMessage(@RequestBody String message) {
        LOGGER.info("Received HTTP POST message: {}", message);
        //Convert message to
        udpClient.sendMessage(message);
    }

}
