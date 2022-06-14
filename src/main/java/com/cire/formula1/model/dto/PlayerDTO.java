package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.PenaltyEntity;
import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.packet.model.data.CarSetupData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlayerDTO {

    @JsonProperty
    private int id;
    @JsonProperty
    private Integer carIndex;
    @JsonProperty
    private String playerName;
    @JsonProperty
    private Float fastestSpeed = 0F;
    @JsonProperty
    private CarSetupData carSetup;
    @JsonProperty
    private Set<PenaltyDTO> penalties = new HashSet<>();
    @JsonProperty
    private Set<PenaltyDTO> involvedPenalties = new HashSet<>();
    @JsonProperty
    private FinalClassificationDTO finalClassification;
    @JsonProperty
    private SessionHistoryDTO sessionHistory;

    public PlayerDTO(PlayerEntity playerEntity) {
        this.id = playerEntity.getId();
        this.carIndex = playerEntity.getCarIndex();
        this.playerName = playerEntity.getPlayerName();
        this.fastestSpeed = playerEntity.getFastestSpeed();
        for(PenaltyEntity penalty: playerEntity.getPenalties()){
            //TODO: Verify that this is correct.
            if(Objects.equals(penalty.getCarIndex(), this.carIndex)){
                this.penalties.add(new PenaltyDTO(penalty));
            }else{
                this.involvedPenalties.add(new PenaltyDTO(penalty));
            }
        }
        if(playerEntity.getFinalClassification() != null) {
            this.finalClassification = new FinalClassificationDTO(playerEntity.getFinalClassification());
        }
        if(playerEntity.getSessionHistory() != null) {
            this.sessionHistory = new SessionHistoryDTO(playerEntity.getSessionHistory());
        }
    }

    public PlayerDTO(int carIndex) {
        this.carIndex = carIndex;
        this.playerName = "Player " + carIndex;
    }

    public PlayerDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(Integer carIndex) {
        this.carIndex = carIndex;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Float getFastestSpeed() {
        return fastestSpeed;
    }

    public void setFastestSpeed(Float fastestSpeed) {
        this.fastestSpeed = fastestSpeed;
    }

    public CarSetupData getCarSetup() {
        return carSetup;
    }

    public void setCarSetup(CarSetupData carSetup) {
        this.carSetup = carSetup;
    }

    public Set<PenaltyDTO> getPenalties() {
        return penalties;
    }

    public void setPenalties(Set<PenaltyDTO> penalties) {
        this.penalties = penalties;
    }

    public Set<PenaltyDTO> getInvolvedPenalties() {
        return involvedPenalties;
    }

    public void setInvolvedPenalties(Set<PenaltyDTO> involvedPenalties) {
        this.involvedPenalties = involvedPenalties;
    }

    public SessionHistoryDTO getSessionHistory() {
        return sessionHistory;
    }

    public void setSessionHistory(SessionHistoryDTO sessionHistoryDTO) {
        this.sessionHistory = sessionHistoryDTO;
    }

    public FinalClassificationDTO getFinalClassification() {
        return finalClassification;
    }

    public void setFinalClassification(FinalClassificationDTO finalClassification) {
        this.finalClassification = finalClassification;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", carIndex=" + carIndex +
                ", playerName='" + playerName + '\'' +
                ", carSetup=" + carSetup +
                ", penalties=" + penalties +
                ", involvedPenalties=" + involvedPenalties +
                ", finalClassification=" + finalClassification +
                ", sessionHistory=" + sessionHistory +
                '}';
    }

    public void updatePlayer(PlayerDTO newPlayer){
        this.carIndex = newPlayer.getCarIndex();
        this.playerName = newPlayer.getPlayerName();
        this.carSetup = newPlayer.getCarSetup();
        for(int i=0;i<newPlayer.getPenalties().size();i++){
            this.penalties.stream().toList().get(i).updatePenalty(newPlayer.getPenalties().stream().toList().get(i));
        }
        for(int i=0;i<newPlayer.getInvolvedPenalties().size();i++){
            this.involvedPenalties.stream().toList().get(i).updatePenalty(newPlayer.getPenalties().stream().toList().get(i));
        }
        this.finalClassification.updateFinalClassification(newPlayer.getFinalClassification());
        this.sessionHistory.updateSessionHistory(newPlayer.getSessionHistory());
    }
}
