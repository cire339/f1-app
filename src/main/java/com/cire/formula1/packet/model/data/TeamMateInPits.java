
package com.cire.formula1.packet.model.data;

public class TeamMateInPits {

    private short vehicleIdx;

    /**
     * @return Vehicle index of team mate
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    @Override
    public String toString() {
        return "TeamMateInPits[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
