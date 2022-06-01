package com.cire.formula1.packet.model.data;

public class FastestLap {

    private short carIndex;
    private float lapTime;

    /**
     * @return Car index of car achieving fastest lap
     */
    public short getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(short carIndex) {
        this.carIndex = carIndex;
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
        return "FastestLap[carIndex=" + this.carIndex +
                ",lapTime=" + this.lapTime +
                "]";
    }
}
