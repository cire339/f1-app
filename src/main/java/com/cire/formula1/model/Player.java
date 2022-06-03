package com.cire.formula1.model;

import com.cire.formula1.packet.model.data.CarSetupData;
import com.cire.formula1.packet.model.data.FinalClassificationData;
import com.cire.formula1.packet.model.data.ParticipantData;
import com.cire.formula1.packet.model.data.Penalty;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @JsonProperty
    private ParticipantData playerInfo;
    @JsonProperty
    private CarSetupData carSetup;
    @JsonProperty
    private List<Penalty> penalties = new ArrayList<>();
    @JsonProperty
    private FinalClassificationData classificationDetails;

    public Player(ParticipantData data){
        this.playerInfo = data;
    }

    public ParticipantData getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(ParticipantData playerInfo) {
        this.playerInfo = playerInfo;
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

    public FinalClassificationData getClassificationDetails() {
        return classificationDetails;
    }

    public void setClassificationDetails(FinalClassificationData classificationDetails) {
        this.classificationDetails = classificationDetails;
    }
}
