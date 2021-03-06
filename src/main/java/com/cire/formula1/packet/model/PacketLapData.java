package com.cire.formula1.packet.model;

import com.cire.formula1.packet.util.PacketConstants;
import com.cire.formula1.packet.model.data.LapData;

import java.util.ArrayList;
import java.util.List;

/**
 * Lap Data Packet
 * 
 * The lap data packet gives details of all the cars in the session.
 * Frequency: Rate as specified in menus
 */
public class PacketLapData extends Packet {
    
    private List<LapData> lapData = new ArrayList<>(PacketConstants.CARS);

    /**
     * @return Lap data for all cars on track
     */
    public List<LapData> getLapData() {
        return lapData;
    }

    public void setLapData(List<LapData> lapData) {
        this.lapData = lapData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LapData[");
        sb.append(super.toString());
        sb.append(",lapData=");
        for (LapData l : lapData) {
            sb.append(l.toString()).append(",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

}