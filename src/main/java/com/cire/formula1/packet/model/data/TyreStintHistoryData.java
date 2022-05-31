package com.cire.formula1.packet.model.data;

public class TyreStintHistoryData {

    private short endLap;
    private short tyreActualCompound;
    private short tyreVisualCompound;

    /**
     * @return Lap the tyre usage ends on (255 of current tyre)
     */
    public short getEndLap() {
        return endLap;
    }

    public void setEndLap(short endLap) {
        this.endLap = endLap;
    }

    /**
     * @return Actual tyres used by this driver
     */
    public short getTyreActualCompound() {
        return tyreActualCompound;
    }

    public void setTyreActualCompound(short tyreActualCompound) {
        this.tyreActualCompound = tyreActualCompound;
    }

    /**
     * @return Visual tyres used by this driver
     */
    public short getTyreVisualCompound() {
        return tyreVisualCompound;
    }

    public void setTyreVisualCompound(short tyreVisualCompound) {
        this.tyreVisualCompound = tyreVisualCompound;
    }

    @Override
    public String toString() {
        return "Retirement[endLap=" + this.endLap +
                "tyreActualCompound=" + this.tyreActualCompound +
                "tyreVisualCompound=" + this.tyreVisualCompound +
                "]";
    }

}
