package com.cire.formula1.packet.model.data;

import com.cire.formula1.packet.model.constants.ZoneFlag;

public class MarshalZone {

    private float zoneStart;
    public ZoneFlag zoneFlag;

    /**
     * @return Zone start
     * Fraction (0..1) of way through the lap the marshal zone starts
     */
    public float getZoneStart() {
        return zoneStart;
    }

    public void setZoneStart(float zoneStart) {
        this.zoneStart = zoneStart;
    }

    /**
     * @return Zone flag
     * -1 = invalid/unknown, 0 = none, 1 = green, 2 = blue, 3 = yellow, 4 = red
     */
    public ZoneFlag getZoneFlag() {
        return zoneFlag;
    }

    public void setZoneFlag(ZoneFlag zoneFlag) {
        this.zoneFlag = zoneFlag;
    }

    @Override
    public String toString() {
        return "MarshalZone[zoneStart=" + this.zoneStart +
                ",zoneFlag=" + this.zoneFlag +
                "]";
    }
}
