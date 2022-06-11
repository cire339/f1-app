package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.PenaltyEntity;
import com.cire.formula1.packet.model.constants.InfringementType;
import com.cire.formula1.packet.model.constants.PenaltyType;
import com.cire.formula1.packet.model.data.Penalty;

public class PenaltyDTO {
    private int id;
    private int playerId;
    private int carIndex;
    private int otherCarIndex;
    private PenaltyType penaltyType;
    private InfringementType infringementType;
    private short penaltyTime;
    private short lapNumber;
    private short placesGained;

    public PenaltyDTO(PenaltyEntity penalty) {
        this.id = penalty.getId();
        this.playerId = penalty.getPlayerId();
        if(penalty.getCarIndex() != null) {
            this.carIndex = penalty.getCarIndex();
        }
        if(penalty.getOtherCarIndex() != null) {
            this.otherCarIndex = penalty.getOtherCarIndex();
        }
        if(penalty.getPenaltyType() != null) {
            this.penaltyType = PenaltyType.valueOf(penalty.getPenaltyType());
        }
        if(penalty.getInfringementType() != null) {
            this.infringementType = InfringementType.valueOf(penalty.getInfringementType());
        }
        if(penalty.getPenaltyTime() != null) {
            this.penaltyTime = penalty.getPenaltyTime().shortValue();
        }
        if(penalty.getLapNumber() != null) {
            this.lapNumber = penalty.getLapNumber().shortValue();
        }
        if(penalty.getPlacesGained() != null) {
            this.placesGained = penalty.getPlacesGained().shortValue();
        }
    }

    public PenaltyDTO(Penalty penalty) {
        this.carIndex = penalty.getCarIndex();
        this.otherCarIndex = penalty.getOtherCarIndex();
        this.penaltyType = penalty.getPenaltyType();
        this.infringementType = penalty.getInfringementType();
        this.penaltyTime = penalty.getTime();
        this.lapNumber = penalty.getLapNum();
        this.placesGained = penalty.getPlacesGained();
    }

    public PenaltyDTO() {

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

    public int getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(int carIndex) {
        this.carIndex = carIndex;
    }

    public int getOtherCarIndex() {
        return otherCarIndex;
    }

    public void setOtherCarIndex(int otherCarIndex) {
        this.otherCarIndex = otherCarIndex;
    }

    public PenaltyType getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(PenaltyType penaltyType) {
        this.penaltyType = penaltyType;
    }

    public InfringementType getInfringementType() {
        return infringementType;
    }

    public void setInfringementType(InfringementType infringementType) {
        this.infringementType = infringementType;
    }

    public short getPenaltyTime() {
        return penaltyTime;
    }

    public void setPenaltyTime(short penaltyTime) {
        this.penaltyTime = penaltyTime;
    }

    public short getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(short lapNumber) {
        this.lapNumber = lapNumber;
    }

    public short getPlacesGained() {
        return placesGained;
    }

    public void setPlacesGained(short placesGained) {
        this.placesGained = placesGained;
    }

    @Override
    public String toString() {
        return "PenaltyDTO{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", carIndex=" + carIndex +
                ", otherCarIndex=" + otherCarIndex +
                ", penaltyType=" + penaltyType +
                ", infringementType=" + infringementType +
                ", penaltyTime=" + penaltyTime +
                ", lapNumber=" + lapNumber +
                ", placesGained=" + placesGained +
                '}';
    }
}
