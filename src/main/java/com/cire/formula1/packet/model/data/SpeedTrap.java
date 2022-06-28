package com.cire.formula1.packet.model.data;

public class SpeedTrap {

    private short carIndex;
    private float speed;
    private short isOverallFastestInSession;
    private short isDriverFastestInSession;
    private short fastestVehicleIdxInSession;
    private float fastestSpeedInSession;

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
    public short getIsOverallFastestInSession() {
        return isOverallFastestInSession;
    }

    public void setIsOverallFastestInSession(short isOverallFastestInSession) {
        this.isOverallFastestInSession = isOverallFastestInSession;
    }

    /**
     * @return Fastest speed for driver in session = 1, otherwise 0
     */
    public short getIsDriverFastestInSession() {
        return isDriverFastestInSession;
    }

    public void setIsDriverFastestInSession(short isDriverFastestInSession) {
        this.isDriverFastestInSession = isDriverFastestInSession;
    }

    /**
     * @return Vehicle index of the vehicle that is the fastest in this session
     */
    public short getFastestVehicleIdxInSession() {
        return fastestVehicleIdxInSession;
    }

    public void setFastestVehicleIdxInSession(short fastestVehicleIdxInSession) {
        this.fastestVehicleIdxInSession = fastestVehicleIdxInSession;
    }

    /**
     * @return Speed of the vehicle that is the fastest in this session
     */
    public float getFastestSpeedInSession() {
        return fastestSpeedInSession;
    }

    public void setFastestSpeedInSession(float fastestSpeedInSession) {
        this.fastestSpeedInSession = fastestSpeedInSession;
    }

    @Override
    public String toString() {
        return "SpeedTrap{" +
                "carIndex=" + carIndex +
                ", speed=" + speed +
                ", isOverallFastestInSession=" + isOverallFastestInSession +
                ", isDriverFastestInSession=" + isDriverFastestInSession +
                ", fastestVehicleIdxInSession=" + fastestVehicleIdxInSession +
                ", fastestSpeedInSession=" + fastestSpeedInSession +
                '}';
    }
}
