package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.CarSetupEntity;
import com.cire.formula1.packet.model.data.CarSetupData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarSetupDTO {

    @JsonProperty
    private int id;
    @JsonProperty
    private short frontWing;
    @JsonProperty
    private short rearWing;
    @JsonProperty
    private short onThrottle;
    @JsonProperty
    private short offThrottle;
    @JsonProperty
    private float frontCamber;
    @JsonProperty
    private float rearCamber;
    @JsonProperty
    private float frontToe;
    @JsonProperty
    private float rearToe;
    @JsonProperty
    private short frontSuspension;
    @JsonProperty
    private short rearSuspension;
    @JsonProperty
    private short frontAntiRollBar;
    @JsonProperty
    private short rearAntiRollBar;
    @JsonProperty
    private short frontSuspensionHeight;
    @JsonProperty
    private short rearSuspensionHeight;
    @JsonProperty
    private short brakePressure;
    @JsonProperty
    private short brakeBias;
    @JsonProperty
    private float rearLeftTyrePressure;
    @JsonProperty
    private float rearRightTyrePressure;
    @JsonProperty
    private float frontLeftTyrePressure;
    @JsonProperty
    private float frontRightTyrePressure;
    @JsonProperty
    private short ballast;
    @JsonProperty
    private float fuelLoad;

    public CarSetupDTO(CarSetupEntity carSetup) {
        this.id = carSetup.getId();
        this.frontWing = carSetup.getFrontWing().shortValue();
        this.rearWing = carSetup.getRearWing().shortValue();
        this.onThrottle = carSetup.getOnThrottle().shortValue();
        this.offThrottle = carSetup.getOffThrottle().shortValue();
        this.frontCamber = carSetup.getFrontCamber().floatValue();
        this.rearCamber = carSetup.getRearCamber().floatValue();
        this.frontToe = carSetup.getFrontToe().floatValue();
        this.rearToe = carSetup.getRearToe().floatValue();
        this.frontSuspension = carSetup.getFrontSuspension().shortValue();
        this.rearSuspension = carSetup.getRearSuspension().shortValue();
        this.frontAntiRollBar = carSetup.getFrontAntiRollBar().shortValue();
        this.rearAntiRollBar = carSetup.getRearAntiRollBar().shortValue();
        this.frontSuspensionHeight = carSetup.getFrontSuspensionHeight().shortValue();
        this.rearSuspensionHeight = carSetup.getRearSuspensionHeight().shortValue();
        this.brakePressure = carSetup.getBrakePressure().shortValue();
        this.brakeBias = carSetup.getBrakeBias().shortValue();
        this.rearLeftTyrePressure = carSetup.getRearLeftTyrePressure().floatValue();
        this.rearRightTyrePressure = carSetup.getRearRightTyrePressure().floatValue();
        this.frontLeftTyrePressure = carSetup.getFrontLeftTyrePressure().floatValue();
        this.frontRightTyrePressure = carSetup.getFrontRightTyrePressure().floatValue();
        this.ballast = carSetup.getBallast().shortValue();
        this.fuelLoad = carSetup.getFuelLoad().floatValue();
    }

    public CarSetupDTO(CarSetupData carSetup) {
        this.frontWing = carSetup.getFrontWing();
        this.rearWing = carSetup.getRearWing();
        this.onThrottle = carSetup.getOnThrottle();
        this.offThrottle = carSetup.getOffThrottle();
        this.frontCamber = carSetup.getFrontCamber();
        this.rearCamber = carSetup.getRearCamber();
        this.frontToe = carSetup.getFrontToe();
        this.rearToe = carSetup.getRearToe();
        this.frontSuspension = carSetup.getFrontSuspension();
        this.rearSuspension = carSetup.getRearSuspension();
        this.frontAntiRollBar = carSetup.getFrontAntiRollBar();
        this.rearAntiRollBar = carSetup.getRearAntiRollBar();
        this.frontSuspensionHeight = carSetup.getFrontSuspensionHeight();
        this.rearSuspensionHeight = carSetup.getRearSuspensionHeight();
        this.brakePressure = carSetup.getBrakePressure();
        this.brakeBias = carSetup.getBrakeBias();
        this.rearLeftTyrePressure = carSetup.getRearLeftTyrePressure();
        this.rearRightTyrePressure = carSetup.getRearRightTyrePressure();
        this.frontLeftTyrePressure = carSetup.getFrontLeftTyrePressure();
        this.frontRightTyrePressure = carSetup.getFrontRightTyrePressure();
        this.ballast = carSetup.getBallast();
        this.fuelLoad = carSetup.getFuelLoad();
    }

    public CarSetupDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getFrontWing() {
        return frontWing;
    }

    public void setFrontWing(short frontWing) {
        this.frontWing = frontWing;
    }

    public short getRearWing() {
        return rearWing;
    }

    public void setRearWing(short rearWing) {
        this.rearWing = rearWing;
    }

    public short getOnThrottle() {
        return onThrottle;
    }

    public void setOnThrottle(short onThrottle) {
        this.onThrottle = onThrottle;
    }

    public short getOffThrottle() {
        return offThrottle;
    }

    public void setOffThrottle(short offThrottle) {
        this.offThrottle = offThrottle;
    }

    public float getFrontCamber() {
        return frontCamber;
    }

    public void setFrontCamber(float frontCamber) {
        this.frontCamber = frontCamber;
    }

    public float getRearCamber() {
        return rearCamber;
    }

    public void setRearCamber(float rearCamber) {
        this.rearCamber = rearCamber;
    }

    public float getFrontToe() {
        return frontToe;
    }

    public void setFrontToe(float frontToe) {
        this.frontToe = frontToe;
    }

    public float getRearToe() {
        return rearToe;
    }

    public void setRearToe(float rearToe) {
        this.rearToe = rearToe;
    }

    public short getFrontSuspension() {
        return frontSuspension;
    }

    public void setFrontSuspension(short frontSuspension) {
        this.frontSuspension = frontSuspension;
    }

    public short getRearSuspension() {
        return rearSuspension;
    }

    public void setRearSuspension(short rearSuspension) {
        this.rearSuspension = rearSuspension;
    }

    public short getFrontAntiRollBar() {
        return frontAntiRollBar;
    }

    public void setFrontAntiRollBar(short frontAntiRollBar) {
        this.frontAntiRollBar = frontAntiRollBar;
    }

    public short getRearAntiRollBar() {
        return rearAntiRollBar;
    }

    public void setRearAntiRollBar(short rearAntiRollBar) {
        this.rearAntiRollBar = rearAntiRollBar;
    }

    public short getFrontSuspensionHeight() {
        return frontSuspensionHeight;
    }

    public void setFrontSuspensionHeight(short frontSuspensionHeight) {
        this.frontSuspensionHeight = frontSuspensionHeight;
    }

    public short getRearSuspensionHeight() {
        return rearSuspensionHeight;
    }

    public void setRearSuspensionHeight(short rearSuspensionHeight) {
        this.rearSuspensionHeight = rearSuspensionHeight;
    }

    public short getBrakePressure() {
        return brakePressure;
    }

    public void setBrakePressure(short brakePressure) {
        this.brakePressure = brakePressure;
    }

    public short getBrakeBias() {
        return brakeBias;
    }

    public void setBrakeBias(short brakeBias) {
        this.brakeBias = brakeBias;
    }

    public float getRearLeftTyrePressure() {
        return rearLeftTyrePressure;
    }

    public void setRearLeftTyrePressure(float rearLeftTyrePressure) {
        this.rearLeftTyrePressure = rearLeftTyrePressure;
    }

    public float getRearRightTyrePressure() {
        return rearRightTyrePressure;
    }

    public void setRearRightTyrePressure(float rearRightTyrePressure) {
        this.rearRightTyrePressure = rearRightTyrePressure;
    }

    public float getFrontLeftTyrePressure() {
        return frontLeftTyrePressure;
    }

    public void setFrontLeftTyrePressure(float frontLeftTyrePressure) {
        this.frontLeftTyrePressure = frontLeftTyrePressure;
    }

    public float getFrontRightTyrePressure() {
        return frontRightTyrePressure;
    }

    public void setFrontRightTyrePressure(float frontRightTyrePressure) {
        this.frontRightTyrePressure = frontRightTyrePressure;
    }

    public short getBallast() {
        return ballast;
    }

    public void setBallast(short ballast) {
        this.ballast = ballast;
    }

    public float getFuelLoad() {
        return fuelLoad;
    }

    public void setFuelLoad(float fuelLoad) {
        this.fuelLoad = fuelLoad;
    }
}
