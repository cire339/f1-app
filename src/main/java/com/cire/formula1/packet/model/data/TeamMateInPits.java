
package com.cire.formula1.packet.model.data;

public class TeamMateInPits {

    private short carIndex;

    /**
     * @return Car index of team mate
     */
    public short getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(short carIndex) {
        this.carIndex = carIndex;
    }

    @Override
    public String toString() {
        return "TeamMateInPits[carIndex=" + this.carIndex +
                "]";
    }
}
