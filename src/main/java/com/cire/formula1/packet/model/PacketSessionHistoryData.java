package com.cire.formula1.packet.model;

import com.cire.formula1.packet.model.data.LapHistoryData;
import com.cire.formula1.packet.model.data.TyreStintHistoryData;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Session History Packet
 *
 * This packet contains lap times and tyre usage for the session.
 * This packet works slightly differently to other packets.
 * To reduce CPU and bandwidth, each packet relates to a specific car and is sent every 1/20 s,
 * and the car being sent is cycled through.
 * Therefore in a 20 car race you should receive an update for each car at least once per second.
 *
 * Note that at the end of the race, after the final classification packet has been sent,
 * a final bulk update of all the session histories for the cars in that session will be sent.
 *
 * Frequency: 20 per second but cycling through cars
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PacketSessionHistoryData extends Packet {

    private short carIdx;
    private short numLaps;
    private short numTyreStints;
    private short bestLapTimeLapNum;
    private short bestSector1LapNum;
    private short bestSector2LapNum;
    private short bestSector3LapNum;

    @JsonIgnore
    private List<LapHistoryData> lapHistoryData = new ArrayList<>(PacketConstants.LAPS);

    @JsonIgnore
    private List<TyreStintHistoryData> tyreStintHistoryData = new ArrayList<>(PacketConstants.TYRE_STINTS);

    /**
     * @return Index of the car this lap data relates to
     */
    public short getCarIdx() {
        return carIdx;
    }

    public void setCarIdx(short carIdx) {
        this.carIdx = carIdx;
    }

    /**
     * @return Num laps in the data (including current partial lap)
     */
    public short getNumLaps() {
        return numLaps;
    }

    public void setNumLaps(short numLaps) {
        this.numLaps = numLaps;
    }

    /**
     * @return Number of tyre stints in the data
     */
    public short getNumTyreStints() {
        return numTyreStints;
    }

    public void setNumTyreStints(short numTyreStints) {
        this.numTyreStints = numTyreStints;
    }

    /**
     * @return Lap the best lap time was achieved on
     */
    public short getBestLapTimeLapNum() {
        return bestLapTimeLapNum;
    }

    public void setBestLapTimeLapNum(short bestLapTimeLapNum) {
        this.bestLapTimeLapNum = bestLapTimeLapNum;
    }

    /**
     * @return Lap the best Sector 1 time was achieved on
     */
    public short getBestSector1LapNum() {
        return bestSector1LapNum;
    }

    public void setBestSector1LapNum(short bestSector1LapNum) {
        this.bestSector1LapNum = bestSector1LapNum;
    }

    /**
     * @return Lap the best Sector 2 time was achieved on
     */
    public short getBestSector2LapNum() {
        return bestSector2LapNum;
    }

    public void setBestSector2LapNum(short bestSector2LapNum) {
        this.bestSector2LapNum = bestSector2LapNum;
    }

    /**
     * @return Lap the best Sector 3 time was achieved on
     */
    public short getBestSector3LapNum() {
        return bestSector3LapNum;
    }

    public void setBestSector3LapNum(short bestSector3LapNum) {
        this.bestSector3LapNum = bestSector3LapNum;
    }

    public List<LapHistoryData> getLapHistoryData() {
        return lapHistoryData;
    }

    public void setLapHistoryData(List<LapHistoryData> lapHistoryData) {
        this.lapHistoryData = lapHistoryData;
    }

    public List<TyreStintHistoryData> getTyreStintHistoryData() {
        return tyreStintHistoryData;
    }

    public void setTyreStintHistoryData(List<TyreStintHistoryData> tyreStintHistoryData) {
        this.tyreStintHistoryData = tyreStintHistoryData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Session[");
        sb.append(super.toString());
        sb.append(",carIdx=").append(this.carIdx);
        sb.append(",numLaps=").append(this.numLaps);
        sb.append(",numTyreStints=").append(this.numTyreStints);
        sb.append(",bestLapTimeLapNum=").append(this.bestLapTimeLapNum);
        sb.append(",bestSector1LapNum=").append(this.bestSector1LapNum);
        sb.append(",bestSector2LapNum=").append(this.bestSector2LapNum);
        sb.append(",bestSector3LapNum=").append(this.bestSector3LapNum);
        sb.append(",lapHistoryData=");
        for (LapHistoryData lhd: lapHistoryData) {
            sb.append(lhd.toString()).append(",");
        }
        sb.append(",tyreStintHistoryData=");
        for (TyreStintHistoryData tshd : tyreStintHistoryData) {
            sb.append(tshd.toString()).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
