package com.cire.formula1.packet.model;

import com.cire.formula1.packet.model.data.ParticipantData;
import com.cire.formula1.packet.util.PacketConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Participants Packet
 * 
 * This is a list of participants in the race. If the car is controlled by
 * AI, then the name will be the driver name. If this is a multiplayer game, the
 * names will be the Steam Id on PC, or the LAN name if appropriate.
 * Frequency: Every 5 seconds
 */
public class PacketParticipantsData extends Packet {
    
    private short numActiveCars;
    private List<ParticipantData> participants = new ArrayList<>(PacketConstants.CARS);

    /**
     * @return Number of active cars in the data – should match number of cats on HUD
     */
    public short getNumActiveCars() {
        return numActiveCars;
    }

    public void setNumActiveCars(short numActiveCars) {
        this.numActiveCars = numActiveCars;
    }

    /**
     * @return Participants
     */
    public List<ParticipantData> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantData> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Participants[");
        sb.append(super.toString());
        sb.append(",numActiveCars=" + this.numActiveCars);
        sb.append(",participants=");
        for (ParticipantData p : participants) {
            sb.append(p.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }
}