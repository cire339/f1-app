package com.cire.formula1.model;

import com.cire.formula1.packet.model.data.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RaceSession {

    /**
     * Object that will keep track of data in a session
     */
    @JsonProperty
    private BigInteger sessionUid;
    @JsonProperty
    private RaceWinner raceWinner;
    @JsonProperty
    private short fastestLapCarIndex;
    @JsonProperty
    private Float fastestLapTime = 0F;
    @JsonProperty
    private short fastestSpeedCarIndex;
    @JsonProperty
    private Float fastestSpeed = 0F;
    @JsonProperty
    private boolean raceStarted = false;
    @JsonProperty
    private boolean raceEnded = false;
    @JsonProperty
    private List<ParticipantData> drivers = new ArrayList<>();
    @JsonProperty
    private List<CarSetupData> carSetups = new ArrayList<>();
    @JsonProperty
    private List<LobbyInfoData> lobby = new ArrayList<>();
    @JsonProperty
    private List<Penalty> penalties = new ArrayList<>();
    @JsonProperty
    private List<FinalClassificationData> finalClassification = new ArrayList<>();

    public BigInteger getSessionUid() {
        return sessionUid;
    }

    public RaceWinner getRaceWinner() {
        return raceWinner;
    }

    public void setRaceWinner(RaceWinner raceWinner) {
        this.raceWinner = raceWinner;
    }

    public void setSessionUid(BigInteger sessionUid) {
        this.sessionUid = sessionUid;
    }

    public short getFastestLapCarIndex() {
        return fastestLapCarIndex;
    }

    public void setFastestLapCarIndex(short fastestLapCarIndex) {
        this.fastestLapCarIndex = fastestLapCarIndex;
    }

    public Float getFastestLapTime() {
        return fastestLapTime;
    }

    public void setFastestLapTime(Float fastestLapTime) {
        this.fastestLapTime = fastestLapTime;
    }

    public short getFastestSpeedCarIndex() {
        return fastestSpeedCarIndex;
    }

    public void setFastestSpeedCarIndex(short fastestSpeedCarIndex) {
        this.fastestSpeedCarIndex = fastestSpeedCarIndex;
    }

    public Float getFastestSpeed() {
        return fastestSpeed;
    }

    public void setFastestSpeed(Float fastestSpeed) {
        this.fastestSpeed = fastestSpeed;
    }

    public boolean isRaceStarted() {
        return raceStarted;
    }

    public void setRaceStarted(boolean raceStarted) {
        this.raceStarted = raceStarted;
    }

    public boolean isRaceEnded() {
        return raceEnded;
    }

    public void setRaceEnded(boolean raceEnded) {
        this.raceEnded = raceEnded;
    }

    public List<ParticipantData> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<ParticipantData> drivers) {
        this.drivers = drivers;
    }

    public List<CarSetupData> getCarSetups() {
        return carSetups;
    }

    public void setCarSetups(List<CarSetupData> carSetups) {
        this.carSetups = carSetups;
    }

    public List<LobbyInfoData> getLobby() {
        return lobby;
    }

    public void setLobby(List<LobbyInfoData> lobby) {
        this.lobby = lobby;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }

    public List<FinalClassificationData> getFinalClassification() {
        return finalClassification;
    }

    public void setFinalClassification(List<FinalClassificationData> finalClassification) {
        this.finalClassification = finalClassification;
    }

    @Override
    public String toString() {
        return "RaceSession{" +
                "sessionUid=" + sessionUid +
                ", fastestLapCarIndex=" + fastestLapCarIndex +
                ", fastestLapTime=" + fastestLapTime +
                ", fastestSpeedCarIndex=" + fastestSpeedCarIndex +
                ", fastestSpeed=" + fastestSpeed +
                ", drivers=" + drivers +
                ", finalClassification=" + finalClassification +
                '}';
    }
}
