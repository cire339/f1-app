package com.cire.formula1.packet.model.data;

public class DriveThroughPenaltyServed {

    private short carIndex;

    /**
     * @return Car index of the car serving drive through
     */
    public short getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(short carIndex) {
        this.carIndex = carIndex;
    }

    @Override
    public String toString() {
        return "DriveThroughPenaltyServed[carIndex=" + this.carIndex +
                "]";
    }
}
