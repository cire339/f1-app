package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.CarSetupDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "car_setup", schema = "public", catalog = "FormulaOne")
public class CarSetupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "player_id", nullable = false, insertable = false, updatable = false)
    private int playerId;
    @Basic
    @Column(name = "front_wing")
    private Integer frontWing;
    @Basic
    @Column(name = "rear_wing")
    private Integer rearWing;
    @Basic
    @Column(name = "on_throttle")
    private Integer onThrottle;
    @Basic
    @Column(name = "off_throttle")
    private Integer offThrottle;
    @Basic
    @Column(name = "front_camber")
    private Float frontCamber;
    @Basic
    @Column(name = "rear_camber")
    private Float rearCamber;
    @Basic
    @Column(name = "front_toe")
    private Float frontToe;
    @Basic
    @Column(name = "rear_toe")
    private Float rearToe;
    @Basic
    @Column(name = "front_suspension")
    private Integer frontSuspension;
    @Basic
    @Column(name = "rear_suspension")
    private Integer rearSuspension;
    @Basic
    @Column(name = "front_anti_roll_bar")
    private Integer frontAntiRollBar;
    @Basic
    @Column(name = "rear_anti_roll_bar")
    private Integer rearAntiRollBar;
    @Basic
    @Column(name = "front_suspension_height")
    private Integer frontSuspensionHeight;
    @Basic
    @Column(name = "rear_suspension_height")
    private Integer rearSuspensionHeight;
    @Basic
    @Column(name = "brake_pressure")
    private Integer brakePressure;
    @Basic
    @Column(name = "brake_bias")
    private Integer brakeBias;
    @Basic
    @Column(name = "rear_left_tyre_pressure")
    private Float rearLeftTyrePressure;
    @Basic
    @Column(name = "rear_right_tyre_pressure")
    private Float rearRightTyrePressure;
    @Basic
    @Column(name = "front_left_tyre_pressure")
    private Float frontLeftTyrePressure;
    @Basic
    @Column(name = "front_right_tyre_pressure")
    private Float frontRightTyrePressure;
    @Basic
    @Column(name = "ballast")
    private Integer ballast;
    @Basic
    @Column(name = "fuel_load")
    private Float fuelLoad;
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private PlayerEntity player;

    public CarSetupEntity(CarSetupDTO carSetup) {
        this.id = carSetup.getId();
        this.frontWing = (int) carSetup.getFrontWing();
        this.rearWing = (int) carSetup.getRearWing();
        this.onThrottle = (int) carSetup.getOnThrottle();
        this.offThrottle = (int) carSetup.getOffThrottle();
        this.frontCamber = carSetup.getFrontCamber();
        this.rearCamber = carSetup.getRearCamber();
        this.frontToe = carSetup.getFrontToe();
        this.rearToe = carSetup.getRearToe();
        this.frontSuspension = (int) carSetup.getFrontSuspension();
        this.rearSuspension = (int) carSetup.getRearSuspension();
        this.frontAntiRollBar = (int) carSetup.getFrontAntiRollBar();
        this.rearAntiRollBar = (int) carSetup.getRearAntiRollBar();
        this.frontSuspensionHeight = (int) carSetup.getFrontSuspensionHeight();
        this.rearSuspensionHeight = (int) carSetup.getRearSuspensionHeight();
        this.brakePressure = (int) carSetup.getBrakePressure();
        this.brakeBias = (int) carSetup.getBrakeBias();
        this.rearLeftTyrePressure = carSetup.getRearLeftTyrePressure();
        this.rearRightTyrePressure = carSetup.getRearRightTyrePressure();
        this.frontLeftTyrePressure = carSetup.getFrontLeftTyrePressure();
        this.frontRightTyrePressure = carSetup.getFrontRightTyrePressure();
        this.ballast = (int) carSetup.getBallast();
        this.fuelLoad = carSetup.getFuelLoad();
    }

    public CarSetupEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Integer getFrontWing() {
        return frontWing;
    }

    public void setFrontWing(Integer frontWing) {
        this.frontWing = frontWing;
    }

    public Integer getRearWing() {
        return rearWing;
    }

    public void setRearWing(Integer rearWing) {
        this.rearWing = rearWing;
    }

    public Integer getOnThrottle() {
        return onThrottle;
    }

    public void setOnThrottle(Integer onThrottle) {
        this.onThrottle = onThrottle;
    }

    public Integer getOffThrottle() {
        return offThrottle;
    }

    public void setOffThrottle(Integer offThrottle) {
        this.offThrottle = offThrottle;
    }

    public Float getFrontCamber() {
        return frontCamber;
    }

    public void setFrontCamber(Float frontCamber) {
        this.frontCamber = frontCamber;
    }

    public Float getRearCamber() {
        return rearCamber;
    }

    public void setRearCamber(Float rearCamber) {
        this.rearCamber = rearCamber;
    }

    public Float getFrontToe() {
        return frontToe;
    }

    public void setFrontToe(Float frontToe) {
        this.frontToe = frontToe;
    }

    public Float getRearToe() {
        return rearToe;
    }

    public void setRearToe(Float rearToe) {
        this.rearToe = rearToe;
    }

    public Integer getFrontSuspension() {
        return frontSuspension;
    }

    public void setFrontSuspension(Integer frontSuspension) {
        this.frontSuspension = frontSuspension;
    }

    public Integer getRearSuspension() {
        return rearSuspension;
    }

    public void setRearSuspension(Integer rearSuspension) {
        this.rearSuspension = rearSuspension;
    }

    public Integer getFrontAntiRollBar() {
        return frontAntiRollBar;
    }

    public void setFrontAntiRollBar(Integer frontAntiRollBar) {
        this.frontAntiRollBar = frontAntiRollBar;
    }

    public Integer getRearAntiRollBar() {
        return rearAntiRollBar;
    }

    public void setRearAntiRollBar(Integer rearAntiRollBar) {
        this.rearAntiRollBar = rearAntiRollBar;
    }

    public Integer getFrontSuspensionHeight() {
        return frontSuspensionHeight;
    }

    public void setFrontSuspensionHeight(Integer frontSuspensionHeight) {
        this.frontSuspensionHeight = frontSuspensionHeight;
    }

    public Integer getRearSuspensionHeight() {
        return rearSuspensionHeight;
    }

    public void setRearSuspensionHeight(Integer rearSuspensionHeight) {
        this.rearSuspensionHeight = rearSuspensionHeight;
    }

    public Integer getBrakePressure() {
        return brakePressure;
    }

    public void setBrakePressure(Integer brakePressure) {
        this.brakePressure = brakePressure;
    }

    public Integer getBrakeBias() {
        return brakeBias;
    }

    public void setBrakeBias(Integer brakeBias) {
        this.brakeBias = brakeBias;
    }

    public Float getRearLeftTyrePressure() {
        return rearLeftTyrePressure;
    }

    public void setRearLeftTyrePressure(Float rearLeftTyrePressure) {
        this.rearLeftTyrePressure = rearLeftTyrePressure;
    }

    public Float getRearRightTyrePressure() {
        return rearRightTyrePressure;
    }

    public void setRearRightTyrePressure(Float rearRightTyrePressure) {
        this.rearRightTyrePressure = rearRightTyrePressure;
    }

    public Float getFrontLeftTyrePressure() {
        return frontLeftTyrePressure;
    }

    public void setFrontLeftTyrePressure(Float frontLeftTyrePressure) {
        this.frontLeftTyrePressure = frontLeftTyrePressure;
    }

    public Float getFrontRightTyrePressure() {
        return frontRightTyrePressure;
    }

    public void setFrontRightTyrePressure(Float frontRightTyrePressure) {
        this.frontRightTyrePressure = frontRightTyrePressure;
    }

    public Integer getBallast() {
        return ballast;
    }

    public void setBallast(Integer ballast) {
        this.ballast = ballast;
    }

    public Float getFuelLoad() {
        return fuelLoad;
    }

    public void setFuelLoad(Float fuelLoad) {
        this.fuelLoad = fuelLoad;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
