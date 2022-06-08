package com.cire.formula1.service;

import com.cire.formula1.packet.model.Packet;

public interface DataProcessingService {
    void processPrimaryData(Packet packet, String playerName);

    void processSecondaryData(Packet packet, String playerName);
}
