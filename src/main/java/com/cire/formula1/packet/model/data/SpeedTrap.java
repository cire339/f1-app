package com.cire.formula1.packet.model.data;

public class SpeedTrap {

    private short carIndex;
    private float speed;
    private short overallFastestInSession;
    private short driverFastestInSession;

    /**
     * @return Car index of the car triggering speed trap
     */
    public short getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(short carIndex) {
        this.carIndex = carIndex;
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
        return "SpeedTrap[carIndex=" + this.carIndex +
                ",speed=" + this.speed +
                ",overallFastestInSession=" + this.overallFastestInSession +
                ",driverFastestInSession=" + this.driverFastestInSession +
                "]";
    }
}
