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
    private short timeTrialPBCarIdx;
    private short timeTrialRivalCarIdx;

    /**
     * @return Lap data for all cars on track
     */
    public List<LapData> getLapData() {
        return lapData;
    }

    public void setLapData(List<LapData> lapData) {
        this.lapData = lapData;
    }

    /**
     * @return Index of Personal Best car in time trial (255 if invalid)
     */
    public short getTimeTrialPBCarIdx() {
        return timeTrialPBCarIdx;
    }

    public void setTimeTrialPBCarIdx(short timeTrialPBCarIdx) {
        this.timeTrialPBCarIdx = timeTrialPBCarIdx;
    }

    /**
     * @return Index of Rival car in time trial (255 if invalid)
     */
    public short getTimeTrialRivalCarIdx() {
        return timeTrialRivalCarIdx;
    }

    public void setTimeTrialRivalCarIdx(short timeTrialRivalCarIdx) {
        this.timeTrialRivalCarIdx = timeTrialRivalCarIdx;
    }

    @Override
    public String toString() {
        return "PacketLapData{" +
                "lapData=" + lapData +
                ", timeTrialPBCarIdx=" + timeTrialPBCarIdx +
                ", timeTrialRivalCarIdx=" + timeTrialRivalCarIdx +
                '}';
    }
}