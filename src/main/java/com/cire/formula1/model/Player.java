package com.cire.formula1.model;

import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.packet.model.data.CarSetupData;
import com.cire.formula1.packet.model.data.FinalClassificationData;
import com.cire.formula1.packet.model.data.Penalty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @JsonProperty
    private String playerName;
    @JsonProperty
    private CarSetupData carSetup;
    @JsonProperty
    private List<Penalty> penalties = new ArrayList<>();
    @JsonProperty
    private FinalClassificationData classificationDetails;
    @JsonProperty
    private SessionHistoryData sessionHistoryData;

    public Player(PlayerEntity playerEntity) {
        this.playerName = playerEntity.getPlayerName();
        this.sessionHistoryData = new SessionHistoryData(playerEntity.getSessionHistoryData());
    }

    public Player(short carIndex) {
        this.playerName = "Player " + carIndex;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public CarSetupData getCarSetup() {
        return carSetup;
    }

    public void setCarSetup(CarSetupData carSetup) {
        this.carSetup = carSetup;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }

    public SessionHistoryData getSessionHistoryData() {
        return sessionHistoryData;
    }

    public void setSessionHistoryData(SessionHistoryData sessionHistoryData) {
        this.sessionHistoryData = sessionHistoryData;
    }

    public FinalClassificationData getClassificationDetails() {
        return classificationDetails;
    }

    public void setClassificationDetails(FinalClassificationData classificationDetails) {
        this.classificationDetails = classificationDetails;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", carSetup=" + carSetup +
                ", penalties=" + penalties +
                ", classificationDetails=" + classificationDetails +
                ", sessionHistoryData=" + sessionHistoryData +
                '}';
    }
}
