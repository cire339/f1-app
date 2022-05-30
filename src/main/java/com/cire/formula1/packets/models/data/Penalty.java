package com.cire.formula1.packets.models.data;

import com.cire.formula1.packets.models.constants.InfringementType;
import com.cire.formula1.packets.models.constants.PenaltyType;

public class Penalty {

    private PenaltyType penaltyType;
    private InfringementType infringementType;
    private short vehicleIdx;
    private short otherVehicleIdx;
    private short time;
    private short lapNum;
    private short placesGained;

    /**
     * @return Penalty type
     */
    public PenaltyType getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(PenaltyType penaltyType) {
        this.penaltyType = penaltyType;
    }

    /**
     * @return Infringement type
     */
    public InfringementType getInfringementType() {
        return infringementType;
    }

    public void setInfringementType(InfringementType infringementType) {
        this.infringementType = infringementType;
    }

    /**
     * @return Vehicle index of the car the penalty is applied to
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    /**
     * @return Vehicle index of the other car involved
     */
    public short getOtherVehicleIdx() {
        return otherVehicleIdx;
    }

    public void setOtherVehicleIdx(short otherVehicleIdx) {
        this.otherVehicleIdx = otherVehicleIdx;
    }

    /**
     * @return Time gained, or time spent doing action in seconds
     */
    public short getTime() {
        return time;
    }

    public void setTime(short time) {
        this.time = time;
    }

    /**
     * @return Lap the penalty occurred on
     */
    public short getLapNum() {
        return lapNum;
    }

    public void setLapNum(short lapNum) {
        this.lapNum = lapNum;
    }

    /**
     * @return Number of places gained by this
     */
    public short getPlacesGained() {
        return placesGained;
    }

    public void setPlacesGained(short placesGained) {
        this.placesGained = placesGained;
    }

    @Override
    public String toString() {
        return "Penalty[penaltyType=" + this.penaltyType +
                ",infringementType=" + this.infringementType +
                ",vehicleIdx=" + this.vehicleIdx +
                ",otherVehicleIdx=" + this.otherVehicleIdx +
                ",time=" + this.time +
                ",lapNum=" + this.lapNum +
                ",placesGained=" + this.placesGained +
                "]";
    }
}
