package com.cire.formula1.packets.models.data;

public class SpeedTrap {

    private short vehicleIdx;
    private float speed;
    private short overallFastestInSession;
    private short driverFastestInSession;

    /**
     * @return Vehicle index of the vehicle triggering speed trap
     */
    public short getVehicleIdx() {
        return vehicleIdx;
    }

    public void setVehicleIdx(short vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }

    /**
     * @return Top speed achieved in kilometres per hour
     */
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * @return Overall fastest speed in session = 1, otherwise 0
     */
    public short getOverallFastestInSession() {
        return overallFastestInSession;
    }

    public void setOverallFastestInSession(short overallFastestInSession) {
        this.overallFastestInSession = overallFastestInSession;
    }

    /**
     * @return Fastest speed for driver in session = 1, otherwise 0
     */
    public short getDriverFastestInSession() {
        return driverFastestInSession;
    }

    public void setDriverFastestInSession(short driverFastestInSession) {
        this.driverFastestInSession = driverFastestInSession;
    }

    @Override
    public String toString() {
        return "SpeedTrap[vehicleIdx=" + this.vehicleIdx +
                ",speed=" + this.speed +
                ",overallFastestInSession=" + this.overallFastestInSession +
                ",driverFastestInSession=" + this.driverFastestInSession +
                "]";
    }
}
