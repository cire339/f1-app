package com.cire.formula1.packets.models.data;

public class DriveThroughPenaltyServed {

    private short vehicleIdx;

    /**
     * @return Vehicle index of the vehicle serving drive through
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    @Override
    public String toString() {
        return "DriveThroughPenaltyServed[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
