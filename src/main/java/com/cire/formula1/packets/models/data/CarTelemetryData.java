package com.cire.formula1.packets.models.data;

import com.cire.formula1.packets.utils.PacketConstants;
import com.cire.formula1.packets.models.constants.SurfaceType;

import java.util.Arrays;

public class CarTelemetryData {

    public static final int SIZE = 60;

    private int speed;
    private float throttle;
    private float steer;
    private float brake;
    private short clutch;
    private short gear;
    private int engineRPM;
    private short drs;
    private short revLightsPercent;
    private int revLightsBitValue;
    private int brakesTemperature[] = new int[PacketConstants.TYRES];
    private short tyresSurfaceTemperature[] = new short[PacketConstants.TYRES];
    private short tyresInnerTemperature[] = new short[PacketConstants.TYRES];
    private int engineTemperature;
    private float tyresPressure[] = new float[PacketConstants.TYRES];
    private SurfaceType surfaceType[] = new SurfaceType[PacketConstants.TYRES];

    /**
     * @return Speed of car in kilometres per hour
     */
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return Amount of throttle applied (0.0 to 1.0)
     */
    public float getThrottle() {
        return throttle;
    }

    public void setThrottle(float throttle) {
        this.throttle = throttle;
    }

    /**
     * @return Steering (-1.0 (full lock left) to 1.0 (full lock right))
     */
    public float getSteer() {
        return steer;
    }

    public void setSteer(float steer) {
        this.steer = steer;
    }

    /**
     * @return Amount of brake applied (0.0 to 1.0)
     */
    public float getBrake() {
        return brake;
    }

    public void setBrake(float brake) {
        this.brake = brake;
    }

    /**
     * @return Amount of clutch applied (0 to 100)
     */
    public short getClutch() {
        return clutch;
    }

    public void setClutch(short clutch) {
        this.clutch = clutch;
    }

    /**
     * @return Gear selected (1-8, N=0, R=-1)
     */
    public short getGear() {
        return gear;
    }

    public void setGear(short gear) {
        this.gear = gear;
    }

    /**
     * @return Engine RPM
     */
    public int getEngineRPM() {
        return engineRPM;
    }

    public void setEngineRPM(int engineRPM) {
        this.engineRPM = engineRPM;
    }

    /**
     * @return DRS
     * 0 = off, 1 = on
     */
    public short getDrs() {
        return drs;
    }

    public void setDrs(short drs) {
        this.drs = drs;
    }

    /**
     * @return Rev lights indicator (percentage)
     */
    public short getRevLightsPercent() {
        return revLightsPercent;
    }

    public void setRevLightsPercent(short revLightsPercent) {
        this.revLightsPercent = revLightsPercent;
    }

    /**
     * @return Rev lights (bit 0 = leftmost LED, bit 14 = rightmost LED)
     */
    public int getRevLightsBitValue() {
        return revLightsBitValue;
    }

    public void setRevLightsBitValue(int revLightsBitValue) {
        this.revLightsBitValue = revLightsBitValue;
    }

    /**
     * @return Brakes temperature (celsius)
     */
    public int[] getBrakesTemperature() {
        return brakesTemperature;
    }

    public void setBrakesTemperature(int[] brakesTemperature) {
        this.brakesTemperature = brakesTemperature;
    }

    /**
     * @return Tyres surface temperature (celsius)
     */
    public short[] getTyresSurfaceTemperature() {
        return tyresSurfaceTemperature;
    }

    public void setTyresSurfaceTemperature(short[] tyresSurfaceTemperature) {
        this.tyresSurfaceTemperature = tyresSurfaceTemperature;
    }

    /**
     * @return Tyres inner temperature (celsius)
     */
    public short[] getTyresInnerTemperature() {
        return tyresInnerTemperature;
    }

    public void setTyresInnerTemperature(short[] tyresInnerTemperature) {
        this.tyresInnerTemperature = tyresInnerTemperature;
    }

    /**
     * @return Engine temperature (celsius)
     */
    public int getEngineTemperature() {
        return engineTemperature;
    }

    public void setEngineTemperature(int engineTemperature) {
        this.engineTemperature = engineTemperature;
    }

    /**
     * @return Tyres pressure (PSI)
     */
    public float[] getTyresPressure() {
        return tyresPressure;
    }

    public void setTyresPressure(float[] tyresPressure) {
        this.tyresPressure = tyresPressure;
    }

    /**
     * @return Driving surface
     */
    public SurfaceType[] getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(SurfaceType[] surfaceType) {
        this.surfaceType = surfaceType;
    }

    @Override
    public String toString() {
        return "CarTelemetryData[speed=" + this.speed +
                ",throttle=" + this.throttle +
                ",steer=" + this.steer +
                ",brake=" + this.brake +
                ",clutch=" + this.clutch +
                ",gear=" + this.gear +
                ",engineRPM=" + this.engineRPM +
                ",drs=" + this.drs +
                ",revLightsPercent=" + this.revLightsPercent +
                ",revLightsBitValue=" + this.revLightsBitValue +
                ",brakesTemperature=" + Arrays.toString(this.brakesTemperature) +
                ",tyresSurfaceTemperature=" + Arrays.toString(this.tyresSurfaceTemperature) +
                ",tyresInnerTemperature=" + Arrays.toString(this.tyresInnerTemperature) +
                ",engineTemperature=" + this.engineTemperature +
                ",tyresPressure=" + Arrays.toString(this.tyresPressure) +
                ",surfaceType=" + Arrays.toString(this.surfaceType) +
                "]";
    }
}
