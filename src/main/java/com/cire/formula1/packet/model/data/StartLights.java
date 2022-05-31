package com.cire.formula1.packet.model.data;

public class StartLights {

    private short numLights;

    /**
     * @return Number of lights showing
     */
    public short getNumLights() {
        return numLights;
    }

    public void setNumLights(short numLights) {
        this.numLights = numLights;
    }

    @Override
    public String toString() {
        return "StartLights[numLights=" + this.numLights +
                "]";
    }

}
