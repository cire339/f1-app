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

    @Value("${udp.channel.cire:EricChannel}")
    private String cireChannel;
    @Value("${udp.port.cire:11111}")
    private Integer cirePort;

    @Value("${udp.channel.tijeune:tijeuneChannel}")
    private String tijeuneChannel;
    @Value("${udp.port.tijeune:22222}")
    private Integer tijeunePort;

    @Bean(name = "inboundChannelCire")
    public MessageChannel inboundChannelCire() {
        return new DirectChannel();
    }

    @Bean(name = "inboundChannelTijeune")
    public MessageChannel inboundChannelTijeune() {
        return new DirectChannel();
    }

    @Bean(name = "udpReceivingAdapterCire")
    public UnicastReceivingChannelAdapter udpReceivingAdapter() {
        UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(cirePort);
        adapter.setOutputChannel(inboundChannelCire());
        adapter.setOutputChannelName(cireChannel);
        return adapter;
    }

    @Bean(name = "udpReceivingAdapterTijeune")
    public UnicastReceivingChannelAdapter udpReceivingAdapterTijeune() {
        UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(tijeunePort);
        adapter.setOutputChannel(inboundChannelTijeune());
        adapter.setOutputChannelName(tijeuneChannel);
        return adapter;
    }

    @Bean(name = "udpSendingAdapterCire")
    public UnicastSendingMessageHandler udpSendingAdapter() {
        return new UnicastSendingMessageHandler("localhost", tijeunePort);
    }

}