package com.cire.formula1.packet.model.data;

public class Retirement {

    private short carIndex;

    /**
     * @return Car index of car retiring
     */
    public short getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(short carIndex) {
        this.carIndex = carIndex;
    }

    @Override
    public String toString() {
        return "Retirement[carIndex=" + this.carIndex +
                "]";
    }
}
