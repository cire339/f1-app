package com.cire.formula1.packets.models.data;

import com.cire.formula1.packets.utils.PacketConstants;

import java.util.Arrays;

public class CarDamageData {

    public static final int SIZE = 39;

    private short tyresWear[] = new short[PacketConstants.TYRES];
    private short tyresDamage[] = new short[PacketConstants.TYRES];
    private short brakeDamage[] = new short[PacketConstants.BRAKES];
    private short frontLeftWingDamage;
    private short frontRightWingDamage;
    private short rearWingDamage;
    private short floorDamage;
    private short diffuserDamage;
    private short sidePodDamage;
    private short drsFault;
    private short gearBoxDamage;
    private short engineDamage;
    private short engineMGUHWear;
    private short engineESWear;
    private short engineCEWear;
    private short engineICEWear;
    private short engineTCWear;

    /**
     * @return Tyre wear percentage
     */
    public short[] getTyresWear() {
        return tyresWear;
    }

    public void setTyresWear(short[] tyresWear) {
        this.tyresWear = tyresWear;
    }

    /**
     * @return Tyre damage (percentage)
     */
    public short[] getTyresDamage() {
        return tyresDamage;
    }

    public void setTyresDamage(short[] tyresDamage) {
        this.tyresDamage = tyresDamage;
    }

    /**
     * @return Brake damage (percentage)
     */
    public short[] getBrakeDamage() {
        return brakeDamage;
    }

    public void setBrakeDamage(short[] brakeDamage) {
        this.brakeDamage = brakeDamage;
    }

    /**
     * @return Front left wing damage (percentage)
     */
    public short getFrontLeftWingDamage() {
        return frontLeftWingDamage;
    }

    public void setFrontLeftWingDamage(short frontLeftWingDamage) {
        this.frontLeftWingDamage = frontLeftWingDamage;
    }

    /**
     * @return Front right wing damage (percentage)
     */
    public short getFrontRightWingDamage() {
        return frontRightWingDamage;
    }

    public void setFrontRightWingDamage(short frontRightWingDamage) {
        this.frontRightWingDamage = frontRightWingDamage;
    }

    /**
     * @return Rear wing damage (percentage)
     */
    public short getRearWingDamage() {
        return rearWingDamage;
    }

    public void setRearWingDamage(short rearWingDamage) {
        this.rearWingDamage = rearWingDamage;
    }

    /**
     * @return Floor damage (percentage)
     */
    public short getFloorDamage() {
        return floorDamage;
    }

    public void setFloorDamage(short floorDamage) {
        this.floorDamage = floorDamage;
    }

    /**
     * @return Diffuser damage (percentage)
     */
    public short getDiffuserDamage() {
        return diffuserDamage;
    }

    public void setDiffuserDamage(short diffuserDamage) {
        this.diffuserDamage = diffuserDamage;
    }

    /**
     * @return Side pod damage (percentage)
     */
    public short getSidePodDamage() {
        return sidePodDamage;
    }

    public void setSidePodDamage(short sidePodDamage) {
        this.sidePodDamage = sidePodDamage;
    }

    /**
     * @return Indicator for DRS fault, 0 = OK, 1 = fault
     */
    public short getDrsFault() {
        return drsFault;
    }

    public void setDrsFault(short drsFault) {
        this.drsFault = drsFault;
    }

    /**
     * @return Gear box damage (percentage)
     */
    public short getGearBoxDamage() {
        return gearBoxDamage;
    }

    public void setGearBoxDamage(short gearBoxDamage) {
        this.gearBoxDamage = gearBoxDamage;
    }

    /**
     * @return Engine damage (percentage)
     */
    public short getEngineDamage() {
        return engineDamage;
    }

    public void setEngineDamage(short engineDamage) {
        this.engineDamage = engineDamage;
    }

    /**
     * @return Engine MGU-H damage (percentage)
     */
    public short getEngineMGUHWear() {
        return engineMGUHWear;
    }

    public void setEngineMGUHWear(short engineMGUHWear) {
        this.engineMGUHWear = engineMGUHWear;
    }

    /**
     * @return Engine ES damage (percentage)
     */
    public short getEngineESWear() {
        return engineESWear;
    }

    public void setEngineESWear(short engineESWear) {
        this.engineESWear = engineESWear;
    }

    /**
     * @return Engine CE damage (percentage)
     */
    public short getEngineCEWear() {
        return engineCEWear;
    }

    public void setEngineCEWear(short engineCEWear) {
        this.engineCEWear = engineCEWear;
    }

    /**
     * @return Engine ICE damage (percentage)
     */
    public short getEngineICEWear() {
        return engineICEWear;
    }

    public void setEngineICEWear(short engineICEWear) {
        this.engineICEWear = engineICEWear;
    }

    /**
     * @return Engine TC damage (percentage)
     */
    public short getEngineTCWear() {
        return engineTCWear;
    }

    public void setEngineTCWear(short engineTCWear) {
        this.engineTCWear = engineTCWear;
    }

    @Override
    public String toString(){
        return "CarDamageData[tyresWear=" + Arrays.toString(this.tyresWear) +
                ",tyresDamage=" + Arrays.toString(this.tyresDamage) +
                ",brakeDamage=" + Arrays.toString(this.brakeDamage) +
                ",frontLeftWingDamage=" + this.frontLeftWingDamage +
                ",frontRightWingDamage=" + this.frontRightWingDamage +
                ",rearWingDamage=" + this.rearWingDamage +
                ",floorDamage=" + this.floorDamage +
                ",diffuserDamage=" + this.diffuserDamage +
                ",sidePodDamage=" + this.sidePodDamage +
                ",drsFault=" + this.drsFault +
                ",gearBoxDamage=" + this.gearBoxDamage +
                ",engineDamage=" + this.engineDamage +
                ",engineMGUHWear=" + this.engineMGUHWear +
                ",engineESWear=" + this.engineESWear +
                ",engineCEWear=" + this.engineCEWear +
                ",engineICEWear=" + this.engineICEWear +
                ",engineTCWear=" + this.engineTCWear +
                "]";
    }
}
