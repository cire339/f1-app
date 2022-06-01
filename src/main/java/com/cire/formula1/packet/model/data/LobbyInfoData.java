package com.cire.formula1.packet.model.data;

import com.cire.formula1.packet.model.constants.Nationality;
import com.cire.formula1.packet.model.constants.ReadyStatus;
import com.cire.formula1.packet.model.constants.Team;

public class LobbyInfoData {

    public static final int NAME_LENGTH = 48;

    private short aiControlled;
    private Team teamId;
    private Nationality nationality;
    private String name;
    private short carNumber;
    private ReadyStatus readyStatus;

    /**
     * @return Whether the car is AI (1) or Human (0) controlled
     */
    public short getAiControlled() {
        return aiControlled;
    }

    public void setAiControlled(short aiControlled) {
        this.aiControlled = aiControlled;
    }

    /**
     * @return Team id - see appendix (255 if no team currently selected)
     */
    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
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
     * Will be truncated with ... (U+2026) if too long
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Car number of the player
     */
    public short getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(short carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * @return Ready status
     * 0 = not ready, 1 = ready, 2 = spectating
     */
    public ReadyStatus getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(ReadyStatus readyStatus) {
        this.readyStatus = readyStatus;
    }

    @Override
    public String toString() {
        return "LobbyInfoData[" +
                ",aiControlled=" + this.aiControlled +
                ",teamId=" + this.teamId +
                ",nationality=" + this.nationality +
                ",name=" + this.name +
                ",carNumber=" + this.carNumber +
                ",readyStatus=" + this.readyStatus +
                "]";
    }
}
