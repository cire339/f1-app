package com.cire.formula1.model;

import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.packet.model.data.*;
import com.cire.formula1.packet.util.PacketConstants;
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
    private short raceWinnerCarIndex;
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
    private List<LobbyInfoData> lobby = new ArrayList<>();
    @JsonProperty
    private List<Player> players = new ArrayList<>();

    public RaceSession(){

    }

    public RaceSession(RaceSessionEntity entity){
        this.sessionUid = new BigInteger(entity.getSessionUid());
    }

    public BigInteger getSessionUid() {
        return sessionUid;
    }

    public short getRaceWinnerCarIndex() {
        return raceWinnerCarIndex;
    }

    public void setRaceWinnerCarIndex(short raceWinnerCarIndex) {
        this.raceWinnerCarIndex = raceWinnerCarIndex;
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

    public List<LobbyInfoData> getLobby() {
        return lobby;
    }

    public void setLobby(List<LobbyInfoData> lobby) {
        this.lobby = lobby;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "RaceSession{" +
                "sessionUid=" + sessionUid +
                ", raceWinnerCarIndex=" + raceWinnerCarIndex +
                ", fastestLapCarIndex=" + fastestLapCarIndex +
                ", fastestLapTime=" + fastestLapTime +
                ", fastestSpeedCarIndex=" + fastestSpeedCarIndex +
                ", fastestSpeed=" + fastestSpeed +
                ", raceStarted=" + raceStarted +
                ", raceEnded=" + raceEnded +
                ", lobby=" + lobby +
                ", players=" + players +
                '}';
    }
}
