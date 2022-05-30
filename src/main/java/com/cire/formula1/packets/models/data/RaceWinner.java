package com.cire.formula1.packets.models.data;

public class RaceWinner {

    private short vehicleIdx;

    /**
     * @return Vehicle index of the race winner
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    @Override
    public String toString() {
        return "RaceWinner[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
