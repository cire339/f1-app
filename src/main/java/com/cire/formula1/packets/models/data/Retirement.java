package com.cire.formula1.packets.models.data;

public class Retirement {

    private short vehicleIdx;

    /**
     * @return Vehicle index of car retiring
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    @Override
    public String toString() {
        return "Retirement[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
