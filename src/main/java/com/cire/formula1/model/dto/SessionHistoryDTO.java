package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.database.entity.SessionHistoryEntity;
import com.cire.formula1.packet.model.PacketSessionHistoryData;
import com.cire.formula1.packet.model.data.LapHistoryData;
import com.cire.formula1.packet.model.data.TyreStintHistoryData;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class SessionHistoryDTO {
    private int id;
    private short numLaps;
    private short numTyreStints;
    private short bestLapTimeLapNum;
    private short bestSector1LapNum;
    private short bestSector2LapNum;
    private short bestSector3LapNum;

    @JsonIgnore
    private List<LapHistoryDTO> lapHistory = new ArrayList<>(PacketConstants.LAPS);

    @JsonIgnore
    private List<TyreStintHistoryData> tyreStintHistoryData = new ArrayList<>(PacketConstants.TYRE_STINTS);

    public SessionHistoryDTO(PacketSessionHistoryData data){
        this.numLaps = data.getNumLaps();
        this.numTyreStints = data.getNumTyreStints();
        this.bestLapTimeLapNum = data.getBestLapTimeLapNum();
        this.bestSector1LapNum = data.getBestSector1LapNum();
        this.bestSector2LapNum = data.getBestSector2LapNum();
        this.bestSector3LapNum = data.getBestSector3LapNum();
        if(data.getLapHistoryData() != null){
            List<LapHistoryDTO> laps = new ArrayList<>();
            for(LapHistoryData lapData : data.getLapHistoryData()){
                laps.add(new LapHistoryDTO(lapData));
            }
            this.lapHistory = laps;
        }
        this.tyreStintHistoryData = data.getTyreStintHistoryData();
    }

    public SessionHistoryDTO() {

    }

    public SessionHistoryDTO(SessionHistoryEntity sessionHistoryData) {
        this.id = sessionHistoryData.getId();
        if(sessionHistoryData.getNumberLaps() != null) {
            this.numLaps = sessionHistoryData.getNumberLaps().shortValue();
        }
        if(sessionHistoryData.getNumberTyreStints() != null) {
            this.numTyreStints = sessionHistoryData.getNumberTyreStints().shortValue();
        }
        if(sessionHistoryData.getBestLapTimeLapNumber() != null){
            this.bestLapTimeLapNum = sessionHistoryData.getBestLapTimeLapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector1LapNumber() != null){
            this.bestSector1LapNum = sessionHistoryData.getBestSector1LapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector2LapNumber() != null){
            this.bestSector2LapNum = sessionHistoryData.getBestSector2LapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector3LapNumber() != null){
            this.bestSector3LapNum = sessionHistoryData.getBestSector2LapNumber().shortValue();
        }
        if(sessionHistoryData.getLapHistory() != null) {
            this.lapHistory = new ArrayList<>();
            for (LapHistoryEntity lapHistoryDataEntity : sessionHistoryData.getLapHistory()) {
                this.lapHistory.add(new LapHistoryDTO(lapHistoryDataEntity));
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<LapHistoryDTO> getLapHistory() {
        return lapHistory;
    }

    public void setLapHistory(List<LapHistoryDTO> lapHistory) {
        this.lapHistory = lapHistory;
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
                "id=" + id +
                ", numLaps=" + numLaps +
                ", numTyreStints=" + numTyreStints +
                ", bestLapTimeLapNum=" + bestLapTimeLapNum +
                ", bestSector1LapNum=" + bestSector1LapNum +
                ", bestSector2LapNum=" + bestSector2LapNum +
                ", bestSector3LapNum=" + bestSector3LapNum +
                ", lapHistory=" + lapHistory +
                ", tyreStintHistoryData=" + tyreStintHistoryData +
                '}';
    }
}
