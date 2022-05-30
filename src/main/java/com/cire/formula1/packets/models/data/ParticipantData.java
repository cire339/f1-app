package com.cire.formula1.packets.models.data;

import com.cire.formula1.packets.models.constants.Driver;
import com.cire.formula1.packets.models.constants.Nationality;
import com.cire.formula1.packets.models.constants.Team;

public class ParticipantData {

    public static final int NAME_LENGTH = 48;

    private short aiControlled;
    private Driver driverId;
    private short networkId;
    private Team teamId;
    private short raceNumber;
    private Nationality nationality;
    private String name;
    private short yourTelemetry;

    /**
     * @return Whether the vehicle is AI (1) or Human (0) controlled
     */
    public short getAiControlled() {
        return aiControlled;
    }

    public void setAiControlled(short aiControlled) {
        this.aiControlled = aiControlled;
    }

    /**
     * @return Driver Id
     */
    public Driver getDriverId() {
        return driverId;
    }

    public void setDriverId(Driver driverId) {
        this.driverId = driverId;
    }

    /**
     * @return Network Id
     */
    public short getNetworkId() {
        return networkId;
    }

    public void setNetworkId(short networkId) {
        this.networkId = networkId;
    }

    /**
     * @return Team Id
     */
    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    /**
     * @return Race number of the car
     */
    public short getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(short raceNumber) {
        this.raceNumber = raceNumber;
    }

    /**
     * @return Nationality of the driver
     */
    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    /**
     * @return Name of participant in UTF-8 format – null terminated
     * Will be truncated with … (U+2026) if too long
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The player's UDP setting, 0 = restricted, 1 = public
     */
    public short getYourTelemetry() {
        return yourTelemetry;
    }

    public void setYourTelemetry(short yourTelemetry) {
        this.yourTelemetry = yourTelemetry;
    }

    @Override
    public String toString() {
        return "ParticipantData[aiControlled=" + this.aiControlled +
                ",driverId=" + this.driverId +
                ",networkId=" + this.networkId +
                ",teamId=" + this.teamId +
                ",raceNumber=" + this.raceNumber +
                ",nationality=" + this.nationality +
                ",name=" + this.name +
                ",yourTelemetry=" + this.yourTelemetry +
                "]";
    }
}
