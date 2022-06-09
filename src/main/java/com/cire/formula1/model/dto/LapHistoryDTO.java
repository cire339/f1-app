package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.packet.model.data.LapHistoryData;

public class LapHistoryDTO {

    private int id;
    private long lapTimeInMS;
    private int sector1TimeInMS;
    private int sector2TimeInMS;
    private int sector3TimeInMS;
    private short lapValidBitFlags;


    public LapHistoryDTO(LapHistoryEntity lapHistoryDataEntity) {
        this.id = lapHistoryDataEntity.getId();
        this.lapTimeInMS = lapHistoryDataEntity.getLapTime();
        if(lapHistoryDataEntity.getSector1Time() != null){
            this.sector1TimeInMS = Math.toIntExact(lapHistoryDataEntity.getSector1Time());
        }
        if(lapHistoryDataEntity.getSector2Time() != null){
            this.sector2TimeInMS = Math.toIntExact(lapHistoryDataEntity.getSector2Time());
        }
        if(lapHistoryDataEntity.getSector3Time() != null){
            this.sector3TimeInMS = Math.toIntExact(lapHistoryDataEntity.getSector3Time());
        }
        if(lapHistoryDataEntity.getLapValidFlag() != null){
            this.lapValidBitFlags = lapHistoryDataEntity.getLapValidFlag().shortValue();
        }
    }

    public LapHistoryDTO(LapHistoryData lapData) {
        this.lapTimeInMS = lapData.getLapTimeInMS();
        this.sector1TimeInMS = lapData.getSector1TimeInMS();
        this.sector2TimeInMS = lapData.getSector2TimeInMS();
        this.sector3TimeInMS = lapData.getSector3TimeInMS();
        this.lapValidBitFlags = lapData.getLapValidBitFlags();
    }

    public LapHistoryDTO(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLapTimeInMS() {
        return lapTimeInMS;
    }

    public void setLapTimeInMS(long lapTimeInMS) {
        this.lapTimeInMS = lapTimeInMS;
    }

    public int getSector1TimeInMS() {
        return sector1TimeInMS;
    }

    public void setSector1TimeInMS(int sector1TimeInMS) {
        this.sector1TimeInMS = sector1TimeInMS;
    }

    public int getSector2TimeInMS() {
        return sector2TimeInMS;
    }

    public void setSector2TimeInMS(int sector2TimeInMS) {
        this.sector2TimeInMS = sector2TimeInMS;
    }

    public int getSector3TimeInMS() {
        return sector3TimeInMS;
    }

    public void setSector3TimeInMS(int sector3TimeInMS) {
        this.sector3TimeInMS = sector3TimeInMS;
    }

    public short getLapValidBitFlags() {
        return lapValidBitFlags;
    }

    public void setLapValidBitFlags(short lapValidBitFlags) {
        this.lapValidBitFlags = lapValidBitFlags;
    }

    @Override
    public String toString() {
        return "LapHistory{" +
                "id=" + id +
                ", lapTimeInMS=" + lapTimeInMS +
                ", sector1TimeInMS=" + sector1TimeInMS +
                ", sector2TimeInMS=" + sector2TimeInMS +
                ", sector3TimeInMS=" + sector3TimeInMS +
                ", lapValidBitFlags=" + lapValidBitFlags +
                '}';
    }
}
