package com.cire.formula1.config;

import com.cire.formula1.packet.util.PacketDecoder;
import com.cire.formula1.packet.model.Packet;
import com.cire.formula1.service.DataProcessingService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

@MessageEndpoint
public class UdpMessageHandler
{
    private final static Logger LOGGER = LoggerFactory.getLogger(UdpMessageHandler.class);

    private final DataProcessingService dataProcessingService;

    public UdpMessageHandler(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    @ServiceActivator(inputChannel = "inboundChannel")
    public void handleMessage(Message<byte[]> message, @Headers Map<String, Object> headerMap) {
        //1. Decode the packet
        PacketDecoder packetDecoder = new PacketDecoder();
        ByteBuf buffer = Unpooled.wrappedBuffer(message.getPayload());
        Packet packet = packetDecoder.decode(buffer);

        //2. Send packet for processing
        dataProcessingService.processData(packet);
    }

}