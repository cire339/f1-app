package com.cire.formula1.service;

import com.cire.formula1.packets.utils.PacketDecoder;
import com.cire.formula1.packets.models.Packet;
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

    @ServiceActivator(inputChannel = "inboundChannel")
    public void handleMessage(Message<byte[]> message, @Headers Map<String, Object> headerMap) {

        PacketDecoder packetDecoder = new PacketDecoder();

        ByteBuf buffer = Unpooled.wrappedBuffer(message.getPayload());

        Packet packet = packetDecoder.decode(buffer);
        //LOGGER.info(packet.toString());
        LOGGER.info(packet.toJSON());
        //JsonObject jsonObject = JsonParser.parseString(packet.toJSON()).getAsJsonObject();

    }
}