package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.PenaltyDTO;
import com.cire.formula1.packet.model.data.Penalty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "penalty", schema = "public", catalog = "FormulaOne")
public class PenaltyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "player_id", nullable = false, insertable = false, updatable = false)
    private int playerId;
    @Basic
    @Column(name = "car_index")
    private Integer carIndex;
    @Basic
    @Column(name = "other_car_index")
    private Integer otherCarIndex;
    @Basic
    @Column(name = "penalty_type", length = -1)
    private String penaltyType;
    @Basic
    @Column(name = "infringement_type", length = -1)
    private String infringementType;
    @Basic
    @Column(name = "penalty_time")
    private Integer penaltyTime;
    @Basic
    @Column(name = "lap_number")
    private Integer lapNumber;
    @Basic
    @Column(name = "places_gained")
    private Integer placesGained;

    @JsonBackReference("player")
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayerEntity player;

    public PenaltyEntity(PenaltyDTO penalty){
        this.id = penalty.getId();
        this.playerId = penalty.getPlayerId();
        this.carIndex = penalty.getCarIndex();
        if(penalty.getOtherCarIndex() != 255) {
            this.otherCarIndex = penalty.getOtherCarIndex();
        }
        this.penaltyType = penalty.getPenaltyType().name();
        this.infringementType = penalty.getInfringementType().name();
        this.penaltyTime = (int) penalty.getPenaltyTime();
        this.lapNumber = (int) penalty.getLapNumber();
        this.placesGained = (int) penalty.getPlacesGained();
    }

    public PenaltyEntity() {

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

    public Integer getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(Integer carIndex) {
        this.carIndex = carIndex;
    }

    public Integer getOtherCarIndex() {
        return otherCarIndex;
    }

    public void setOtherCarIndex(Integer otherCarIndex) {
        this.otherCarIndex = otherCarIndex;
    }

    public String getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(String penaltyType) {
        this.penaltyType = penaltyType;
    }

    public String getInfringementType() {
        return infringementType;
    }

    public void setInfringementType(String infringementType) {
        this.infringementType = infringementType;
    }

    public Integer getPenaltyTime() {
        return penaltyTime;
    }

    public void setPenaltyTime(Integer penaltyTime) {
        this.penaltyTime = penaltyTime;
    }

    public Integer getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(Integer lapNumber) {
        this.lapNumber = lapNumber;
    }

    public Integer getPlacesGained() {
        return placesGained;
    }

    public void setPlacesGained(Integer placesGained) {
        this.placesGained = placesGained;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

}
