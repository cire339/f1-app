
package com.cire.formula1.packet.model.data;

import com.cire.formula1.packet.model.constants.*;

public class CarStatusData {

    private TractionControl tractionControl;
    private short antiLockBrakes;
    private FuelMix fuelMix;
    private short frontBrakeBias;
    private short pitLimiterStatus;
    private float fuelInTank;
    private float fuelCapacity;
    private float fuelRemainingLaps;
    private int maxRPM;
    private int idleRPM;
    private short maxGears;
    private DrsAllowed drsAllowed;
    private int drsActivationDistance;
    private ActualTyreCompound actualTyreCompound;
    private VisualTyreCompound visualTyreCompound;
    private short tyresAgeLaps;
    private CarFiaFlag carFiaFlags;
    private float ersStoreEnergy;
    private ErsDeployMode ersDeployMode;
    private float ersHarvestedThisLapMGUK;
    private float ersHarvestedThisLapMGUH;
    private float ersDeployedThisLap;
    private short networkPaused;

    /**
     * @return Traction control
     * 0 (off) - 2 (high)
     */
    public TractionControl getTractionControl() {
        return tractionControl;
    }

    public void setTractionControl(TractionControl tractionControl) {
        this.tractionControl = tractionControl;
    }

    /**
     * @return Antilock brakes
     * 0 (off) - 1 (on)
     */
    public short getAntiLockBrakes() {
        return antiLockBrakes;
    }

    public void setAntiLockBrakes(short antiLockBrakes) {
        this.antiLockBrakes = antiLockBrakes;
    }

    /**
     * @return Fuel mix
     * 0 = lean, 1 = standard, 2 = rich, 3 = max
     */
    public FuelMix getFuelMix() {
        return fuelMix;
    }

    public void setFuelMix(FuelMix fuelMix) {
        this.fuelMix = fuelMix;
    }

    /**
     * @return Front brake bias (percentage)
     */
    public short getFrontBrakeBias() {
        return frontBrakeBias;
    }

    public void setFrontBrakeBias(short frontBrakeBias) {
        this.frontBrakeBias = frontBrakeBias;
    }

    /**
     * @return Pit limiter status
     * 0 = off, 1 = on
     */
    public short getPitLimiterStatus() {
        return pitLimiterStatus;
    }

    public void setPitLimiterStatus(short pitLimiterStatus) {
        this.pitLimiterStatus = pitLimiterStatus;
    }

    /**
     * @return Current fuel mass
     */
    public float getFuelInTank() {
        return fuelInTank;
    }

    public void setFuelInTank(float fuelInTank) {
        this.fuelInTank = fuelInTank;
    }

    /**
     * @return Fuel capacity
     */
    public float getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(float fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    /**
     * @return Fuel remaining in terms of laps (value on MFD)
     */
    public float getFuelRemainingLaps() {
        return fuelRemainingLaps;
    }

    public void setFuelRemainingLaps(float fuelRemainingLaps) {
        this.fuelRemainingLaps = fuelRemainingLaps;
    }

    /**
     * @return Cars max RPM, point of rev limiter
     */
    public int getMaxRPM() {
        return maxRPM;
    }

    public void setMaxRPM(int maxRPM) {
        this.maxRPM = maxRPM;
    }

    /**
     * @return Cars idle RPM
     */
    public int getIdleRPM() {
        return idleRPM;
    }

    public void setIdleRPM(int idleRPM) {
        this.idleRPM = idleRPM;
    }

    /**
     * @return Maximum number of gears
     */
    public short getMaxGears() {
        return maxGears;
    }

    public void setMaxGears(short maxGears) {
        this.maxGears = maxGears;
    }

    /**
     * @return DRS allowed
     * 0 = not allowed, 1 = allowed, -1 = unknown
     */
    public DrsAllowed getDrsAllowed() {
        return drsAllowed;
    }

    public void setDrsAllowed(DrsAllowed drsAllowed) {
        this.drsAllowed = drsAllowed;
    }

    /**
     * @return DRS activation distance
     * 0 = DRS not available, non-zero - DRS will be available in [X] metres
     */
    public int getDrsActivationDistance() {
        return drsActivationDistance;
    }

    public void setDrsActivationDistance(int drsActivationDistance) {
        this.drsActivationDistance = drsActivationDistance;
    }

    /**
     * @return Actual tyre compound
     * F1 Modern - 16 = C5, 17 = C4, 18 = C3, 19 = C2, 20 = C1, 7 = inter, 8 = wet
     * F1 Classic - 9 = dry, 10 = wet
     * F2 – 11 = super soft, 12 = soft, 13 = medium, 14 = hard, 15 = wet
     */
    public ActualTyreCompound getActualTyreCompound() {
        return actualTyreCompound;
    }

    public void setActualTyreCompound(ActualTyreCompound actualTyreCompound) {
        this.actualTyreCompound = actualTyreCompound;
    }

    /**
     * @return Visual tyre compound
     * F1 visual (can be different from actual compound) 16 = soft, 17 = medium, 18 = hard, 7 = inter, 8 = wet
     * F1 Classic – same as above
     * F2 – same as above
     */
    public VisualTyreCompound getVisualTyreCompound() {
        return visualTyreCompound;
    }

    public void setVisualTyreCompound(VisualTyreCompound visualTyreCompound) {
        this.visualTyreCompound = visualTyreCompound;
    }

    /**
     * @return Age in laps of the current set of tyres
     */
    public short getTyresAgeLaps() {
        return tyresAgeLaps;
    }

    public void setTyresAgeLaps(short tyresAgeLaps) {
        this.tyresAgeLaps = tyresAgeLaps;
    }

    /**
     * @return Car FIA flags
     * -1 = invalid/unknown, 0 = none, 1 = green
     * 2 = blue, 3 = yellow, 4 = red
     */
    public CarFiaFlag getCarFiaFlags() {
        return carFiaFlags;
    }

    public void setCarFiaFlags(CarFiaFlag carFiaFlags) {
        this.carFiaFlags = carFiaFlags;
    }

    /**
     * @return ERS energy store in Joules
     */
    public float getErsStoreEnergy() {
        return ersStoreEnergy;
    }

    public void setErsStoreEnergy(float ersStoreEnergy) {
        this.ersStoreEnergy = ersStoreEnergy;
    }

    /**
     * @return ERS deploy mode
     * 0 = none, 1 = medium, 2 = overtake, 3 = hotlap
     */
    public ErsDeployMode getErsDeployMode() {
        return ersDeployMode;
    }

    public void setErsDeployMode(ErsDeployMode ersDeployMode) {
        this.ersDeployMode = ersDeployMode;
    }

    /**
     * @return ERS energy harvested this lap by MGU-K
     */
    public float getErsHarvestedThisLapMGUK() {
        return ersHarvestedThisLapMGUK;
    }

    public void setErsHarvestedThisLapMGUK(float ersHarvestedThisLapMGUK) {
        this.ersHarvestedThisLapMGUK = ersHarvestedThisLapMGUK;
    }

    /**
     * @return ERS energy harvested this lap by MGU-H
     */
    public float getErsHarvestedThisLapMGUH() {
        return ersHarvestedThisLapMGUH;
    }

    public void setErsHarvestedThisLapMGUH(float ersHarvestedThisLapMGUH) {
        this.ersHarvestedThisLapMGUH = ersHarvestedThisLapMGUH;
    }

    /**
     * @return ERS energy deployed this lap
     */
    public float getErsDeployedThisLap() {
        return ersDeployedThisLap;
    }

    public void setErsDeployedThisLap(float ersDeployedThisLap) {
        this.ersDeployedThisLap = ersDeployedThisLap;
    }

    /**
     * @return Whether the car is paused in a network game
     */
    public short getNetworkPaused() {
        return networkPaused;
    }

    public void setNetworkPaused(short networkPaused) {
        this.networkPaused = networkPaused;
    }

    @Override
    public String toString() {
        return "CarStatusData[tractionControl=" + this.tractionControl +
                ",antiLockBrakes=" + this.antiLockBrakes +
                ",fuelMix=" + this.fuelMix +
                ",frontBrakeBias=" + this.frontBrakeBias +
                ",pitLimiterStatus=" + this.pitLimiterStatus +
                ",fuelInTank=" + this.fuelInTank +
                ",fuelCapacity=" + this.fuelCapacity +
                ",fuelRemainingLaps=" + this.fuelRemainingLaps +
                ",maxRPM=" + this.maxRPM +
                ",idleRPM=" + this.idleRPM +
                ",maxGears=" + this.maxGears +
                ",drsAllowed=" + this.drsAllowed +
                ",drsActivationDistance=" + this.drsActivationDistance +
                ",actualTyreCompound=" + this.actualTyreCompound +
                ",visualTyreCompound=" + this.visualTyreCompound +
                ",tyresAgeLaps=" + this.tyresAgeLaps +
                ",carFiaFlags=" + this.carFiaFlags +
                ",ersStoreEnergy=" + this.ersStoreEnergy +
                ",ersDeployMode=" + this.ersDeployMode +
                ",ersHarvestedThisLapMGUK=" + this.ersHarvestedThisLapMGUK +
                ",ersHarvestedThisLapMGUH=" + this.ersHarvestedThisLapMGUH +
                ",ersDeployedThisLap=" + this.ersDeployedThisLap +
                ",networkPaused=" + this.networkPaused +
                "]";
    }
}
