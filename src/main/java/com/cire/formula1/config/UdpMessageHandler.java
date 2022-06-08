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

    @ServiceActivator(inputChannel = "inboundChannelCire")
    public void handleMessageCire(Message<byte[]> message, @Headers Map<String, Object> headerMap) {
        //LOGGER.info("Incoming packet from cire.");
        //TODO: better player name handling.
        handlePrimaryMessage(message, "cirelol");
    }

    @ServiceActivator(inputChannel = "inboundChannelTijeune")
    public void handleMessageTijeune(Message<byte[]> message, @Headers Map<String, Object> headerMap) {
        //LOGGER.info("Incoming packet from TiJeune.");
        //TODO: better player name handling.
        handleSecondaryMessage(message, "TiJeune");
    }

    private void handlePrimaryMessage(Message<byte[]> message, String playerName){
        //Testing hex formatting
        //String hexStr = HexFormat.of().formatHex(message.getPayload());
        //LOGGER.info(hexStr);

        //1. Decode the packet
        PacketDecoder packetDecoder = new PacketDecoder();
        ByteBuf buffer = Unpooled.wrappedBuffer(message.getPayload());
        Packet packet = packetDecoder.decode(buffer);

        //2. Send packet for processing
        dataProcessingService.processPrimaryData(packet, playerName);
    }

    private void handleSecondaryMessage(Message<byte[]> message, String playerName){
        //1. Decode the packet
        PacketDecoder packetDecoder = new PacketDecoder();
        ByteBuf buffer = Unpooled.wrappedBuffer(message.getPayload());
        Packet packet = packetDecoder.decode(buffer);

        //2. Send packet for processing
        dataProcessingService.processSecondaryData(packet, playerName);
    }

}