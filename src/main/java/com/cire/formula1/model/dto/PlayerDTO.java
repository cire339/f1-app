package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.packet.model.data.CarSetupData;
import com.cire.formula1.packet.model.data.FinalClassificationData;
import com.cire.formula1.packet.model.data.Penalty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDTO {

    @JsonProperty
    private int id;
    @JsonProperty
    private String playerName;
    @JsonProperty
    private CarSetupData carSetup;
    @JsonProperty
    private List<Penalty> penalties = new ArrayList<>();
    @JsonProperty
    private FinalClassificationData classificationDetails;
    @JsonProperty
    private SessionHistoryDTO sessionHistoryDTO;

    public PlayerDTO(PlayerEntity playerEntity) {
        this.id = playerEntity.getId();
        this.playerName = playerEntity.getPlayerName();
        this.sessionHistoryDTO = new SessionHistoryDTO(playerEntity.getSessionHistory());
    }

    public PlayerDTO(short carIndex) {
        this.playerName = "Player " + carIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public SessionHistoryDTO getSessionHistory() {
        return sessionHistoryDTO;
    }

    public void setSessionHistory(SessionHistoryDTO sessionHistoryDTO) {
        this.sessionHistoryDTO = sessionHistoryDTO;
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
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", carSetup=" + carSetup +
                ", penalties=" + penalties +
                ", classificationDetails=" + classificationDetails +
                ", sessionHistory=" + sessionHistoryDTO +
                '}';
    }
}
