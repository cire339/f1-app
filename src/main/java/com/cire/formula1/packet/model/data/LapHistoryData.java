package com.cire.formula1.packet.model.data;

import com.cire.formula1.database.entity.LapHistoryDataEntity;

public class LapHistoryData {

    private long lapTimeInMS;
    private int sector1TimeInMS;
    private int sector2TimeInMS;
    private int sector3TimeInMS;
    private short lapValidBitFlags;

    public LapHistoryData(LapHistoryDataEntity lapHistoryDataEntity) {
        this.lapTimeInMS = lapHistoryDataEntity.getLapTime();
        this.sector1TimeInMS = lapHistoryDataEntity.getSector1Time() != null ? Math.toIntExact(lapHistoryDataEntity.getSector1Time()) : null;
        this.sector2TimeInMS = lapHistoryDataEntity.getSector2Time() != null ? Math.toIntExact(lapHistoryDataEntity.getSector2Time()) : null;
        this.sector2TimeInMS = lapHistoryDataEntity.getSector3Time() != null ? Math.toIntExact(lapHistoryDataEntity.getSector3Time()) : null;
        this.lapValidBitFlags = lapHistoryDataEntity.getLapValidFlag() != null ? lapHistoryDataEntity.getLapValidFlag().shortValue() : null;
    }

    public LapHistoryData() {

    }

    /**
     * @return Lap time in milliseconds
     */
    public long getLapTimeInMS() {
        return lapTimeInMS;
    }

    public void setLapTimeInMS(long lapTimeInMS) {
        this.lapTimeInMS = lapTimeInMS;
    }

    /**
     * @return Sector 1 time in milliseconds
     */
    public int getSector1TimeInMS() {
        return sector1TimeInMS;
    }

    public void setSector1TimeInMS(int sector1TimeInMS) {
        this.sector1TimeInMS = sector1TimeInMS;
    }

    /**
     * @return Sector 2 time in milliseconds
     */
    public int getSector2TimeInMS() {
        return sector2TimeInMS;
    }

    public void setSector2TimeInMS(int sector2TimeInMS) {
        this.sector2TimeInMS = sector2TimeInMS;
    }

    /**
     * @return Sector 3 time in milliseconds
     */
    public int getSector3TimeInMS() {
        return sector3TimeInMS;
    }

    public void setSector3TimeInMS(int sector3TimeInMS) {
        this.sector3TimeInMS = sector3TimeInMS;
    }

    /**
     * @return Lap Bit Flags
     * 0x01 bit set-lap valid,      0x02 bit set-sector 1 valid
     * 0x04 bit set-sector 2 valid, 0x08 bit set-sector 3 valid
     */
    public short getLapValidBitFlags() {
        return lapValidBitFlags;
    }

    public void setLapValidBitFlags(short lapValidBitFlags) {
        this.lapValidBitFlags = lapValidBitFlags;
    }

    @Override
    public String toString() {
        return "LapHistoryData[lapTimeInMS=" + this.lapTimeInMS +
                ",sector1TimeInMS=" + this.sector1TimeInMS +
                ",sector2TimeInMS=" + this.sector2TimeInMS +
                ",sector3TimeInMS=" + this.sector3TimeInMS +
                ",lapValidBitFlags=" + this.lapValidBitFlags +
                "]";
    }

}
