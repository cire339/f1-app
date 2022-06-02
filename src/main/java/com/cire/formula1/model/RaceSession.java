package com.cire.formula1.model;

import com.cire.formula1.packet.model.data.FinalClassificationData;
import com.cire.formula1.packet.model.data.LobbyInfoData;
import com.cire.formula1.packet.model.data.ParticipantData;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RaceSession {

    /**
     * Object that will keep track of data in a session
     */
    private BigInteger sessionUid;
    private short fastestLapCarIndex;
    private Float fastestLapTime = 0F;
    private short fastestSpeedCarIndex;
    private Float fastestSpeed = 0F;
    private boolean raceStarted = false;
    private boolean raceEnded = false;
    private List<ParticipantData> drivers = new ArrayList<>();
    private List<LobbyInfoData> lobby = new ArrayList<>();
    private List<FinalClassificationData> finalClassification = new ArrayList<>();

    public BigInteger getSessionUid() {
        return sessionUid;
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

    public List<LobbyInfoData> getLobby() {
        return lobby;
    }

    public void setLobby(List<LobbyInfoData> lobby) {
        this.lobby = lobby;
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
