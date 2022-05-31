package com.cire.formula1.packet.model.data;

public class FastestLap {

    private short vehicleIdx;
    private float lapTime;

    /**
     * @return Vehicle index of car achieving fastest lap
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    /**
     * @return Lap time is in seconds
     */
    public float getLapTime() {
        return lapTime;
    }

    public void setLapTime(float lapTime) {
        this.lapTime = lapTime;
    }

    @Override
    public String toString() {
        return "FastestLap[vehicleIdx=" + this.vehicleIdx +
                ",lapTime=" + this.lapTime +
                "]";
    }
}
