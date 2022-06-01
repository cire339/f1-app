package com.cire.formula1.packet.model.data;

public class RaceWinner {

    private short carIndex;

    /**
     * @return Car index of the race winner
     */
    public short getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(short carIndex) {
        this.carIndex = carIndex;
    }

    @Override
    public String toString() {
        return "RaceWinner[carIndex=" + this.carIndex +
                "]";
    }
}
