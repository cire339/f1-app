package com.cire.formula1.packets.models.data;

public class StopGoPenaltyServed {

    private short vehicleIdx;

    /**
     * @return Vehicle index of the vehicle serving stop go
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    @Override
    public String toString() {
        return "StopGoPenaltyServed[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
