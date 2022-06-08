package com.cire.formula1.model;

import com.cire.formula1.database.entity.LapHistoryDataEntity;
import com.cire.formula1.database.entity.SessionHistoryDataEntity;
import com.cire.formula1.packet.model.PacketSessionHistoryData;
import com.cire.formula1.packet.model.data.LapHistoryData;
import com.cire.formula1.packet.model.data.TyreStintHistoryData;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class SessionHistoryData {
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

    public SessionHistoryData(PacketSessionHistoryData data){
        this.numLaps = data.getNumLaps();
        this.numTyreStints = data.getNumTyreStints();
        this.bestLapTimeLapNum = data.getBestLapTimeLapNum();
        this.bestSector1LapNum = data.getBestSector1LapNum();
        this.bestSector2LapNum = data.getBestSector2LapNum();
        this.bestSector3LapNum = data.getBestSector3LapNum();
        this.lapHistoryData = data.getLapHistoryData();
        this.tyreStintHistoryData = data.getTyreStintHistoryData();
    }

    public SessionHistoryData() {

    }


    public SessionHistoryData(SessionHistoryDataEntity sessionHistoryData) {
        if(sessionHistoryData.getNumberLaps() != null) {
            this.numLaps = sessionHistoryData.getNumberLaps().shortValue();
        }
        if(sessionHistoryData.getNumberTyreStints() != null) {
            this.numTyreStints = sessionHistoryData.getNumberTyreStints() != null ? sessionHistoryData.getNumberTyreStints().shortValue() : null;
        }
        if(sessionHistoryData.getBestLapTimeLapNumber() != null){
            this.bestLapTimeLapNum = sessionHistoryData.getBestLapTimeLapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector1LapNumber() != null){
            sessionHistoryData.getBestSector1LapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector2LapNumber() != null){
            sessionHistoryData.getBestSector2LapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector3LapNumber() != null){
            sessionHistoryData.getBestSector2LapNumber().shortValue();
        }
        if(sessionHistoryData.getLapHistoryData() != null) {
            this.lapHistoryData = new ArrayList<>();
            for (LapHistoryDataEntity lapHistoryDataEntity : sessionHistoryData.getLapHistoryData()) {
                this.lapHistoryData.add(new LapHistoryData(lapHistoryDataEntity));
            }
        }
    }

    public short getNumLaps() {
        return numLaps;
    }

    public void setNumLaps(short numLaps) {
        this.numLaps = numLaps;
    }

    public short getNumTyreStints() {
        return numTyreStints;
    }

    public void setNumTyreStints(short numTyreStints) {
        this.numTyreStints = numTyreStints;
    }

    public short getBestLapTimeLapNum() {
        return bestLapTimeLapNum;
    }

    public void setBestLapTimeLapNum(short bestLapTimeLapNum) {
        this.bestLapTimeLapNum = bestLapTimeLapNum;
    }

    public short getBestSector1LapNum() {
        return bestSector1LapNum;
    }

    public void setBestSector1LapNum(short bestSector1LapNum) {
        this.bestSector1LapNum = bestSector1LapNum;
    }

    public short getBestSector2LapNum() {
        return bestSector2LapNum;
    }

    public void setBestSector2LapNum(short bestSector2LapNum) {
        this.bestSector2LapNum = bestSector2LapNum;
    }

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
        return "SessionHistoryData{" +
                "numLaps=" + numLaps +
                ", numTyreStints=" + numTyreStints +
                ", bestLapTimeLapNum=" + bestLapTimeLapNum +
                ", bestSector1LapNum=" + bestSector1LapNum +
                ", bestSector2LapNum=" + bestSector2LapNum +
                ", bestSector3LapNum=" + bestSector3LapNum +
                ", lapHistoryData=" + lapHistoryData +
                ", tyreStintHistoryData=" + tyreStintHistoryData +
                '}';
    }
}
