package com.cire.formula1.packet.model.data;

public class StopGoPenaltyServed {

    private short carIndex;

    /**
     * @return Car index of the car serving stop go
     */
    public short getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(short carIndex) {
        this.carIndex = carIndex;
    }

    @Override
    public String toString() {
        return "StopGoPenaltyServed[carIndex=" + this.carIndex +
                "]";
    }
}
