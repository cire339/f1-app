package com.cire.formula1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.MessageChannel;

@Configuration
public class UdpConfig {

    @Value("${udp.channel.name:packetChannel}")
    private String channelName;
    @Value("${udp.port.cire:11111}")
    private Integer port;

    @Bean(name = "inboundChannel")
    public MessageChannel inboundChannel() {
        return new DirectChannel();
    }

    @Bean(name = "udpReceivingAdapter")
    public UnicastReceivingChannelAdapter udpReceivingAdapter() {
        UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(port);
        adapter.setOutputChannel(inboundChannel());
        adapter.setOutputChannelName(channelName);
        return adapter;
    }

    @Bean(name = "udpSendingAdapter")
    public UnicastSendingMessageHandler udpSendingAdapter() {
        return new UnicastSendingMessageHandler("localhost", port);
    }

}