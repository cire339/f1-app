package com.cire.formula1.database.entity;

import com.cire.formula1.model.SessionHistoryData;
import com.cire.formula1.packet.model.data.LapHistoryData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "session_history_data", schema = "public", catalog = "FormulaOne")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionHistoryDataEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "player_id", nullable = false, insertable = false, updatable = false)
    private Integer playerId;

    @Column(name = "number_laps", nullable = true)
    private Integer numberLaps;

    @Column(name = "number_tyre_stints", nullable = true)
    private Integer numberTyreStints;

    @Column(name = "best_lap_time_lap_number", nullable = true)
    private Integer bestLapTimeLapNumber;

    @Column(name = "best_sector_1_lap_number", nullable = true)
    private Integer bestSector1LapNumber;

    @Column(name = "best_sector_2_lap_number", nullable = true)
    private Integer bestSector2LapNumber;

    @Column(name = "best_sector_3_lap_number", nullable = true)
    private Integer bestSector3LapNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "sessionHistoryData", cascade = CascadeType.ALL)
    private Collection<LapHistoryDataEntity> lapHistoryData;

    @JsonBackReference("player")
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayerEntity player;

    public SessionHistoryDataEntity(SessionHistoryData data){
        this.numberLaps = (int) data.getNumLaps();
        this.numberTyreStints = (int) data.getNumTyreStints();
        this.bestLapTimeLapNumber = (int) data.getBestLapTimeLapNum();
        this.bestSector1LapNumber = (int) data.getBestSector1LapNum();
        this.bestSector2LapNumber = (int) data.getBestSector2LapNum();
        this.bestSector3LapNumber = (int) data.getBestSector3LapNum();
        this.lapHistoryData = new ArrayList<>();
        for(LapHistoryData lapHistoryData: data.getLapHistoryData()){
            LapHistoryDataEntity lapHistoryDataEntity = new LapHistoryDataEntity(lapHistoryData);
            lapHistoryDataEntity.setSessionHistoryData(this);
            this.lapHistoryData.add(lapHistoryDataEntity);
        }

    }

    public SessionHistoryDataEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getNumberLaps() {
        return numberLaps;
    }

    public void setNumberLaps(Integer numberLaps) {
        this.numberLaps = numberLaps;
    }

    public Integer getNumberTyreStints() {
        return numberTyreStints;
    }

    public void setNumberTyreStints(Integer numberTyreStints) {
        this.numberTyreStints = numberTyreStints;
    }

    public Integer getBestLapTimeLapNumber() {
        return bestLapTimeLapNumber;
    }

    public void setBestLapTimeLapNumber(Integer bestLapTimeLapNumber) {
        this.bestLapTimeLapNumber = bestLapTimeLapNumber;
    }

    public Integer getBestSector1LapNumber() {
        return bestSector1LapNumber;
    }

    public void setBestSector1LapNumber(Integer bestSector1LapNumber) {
        this.bestSector1LapNumber = bestSector1LapNumber;
    }

    public Integer getBestSector2LapNumber() {
        return bestSector2LapNumber;
    }

    public void setBestSector2LapNumber(Integer bestSector2LapNumber) {
        this.bestSector2LapNumber = bestSector2LapNumber;
    }

    public Integer getBestSector3LapNumber() {
        return bestSector3LapNumber;
    }

    public void setBestSector3LapNumber(Integer bestSector3LapNumber) {
        this.bestSector3LapNumber = bestSector3LapNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionHistoryDataEntity that = (SessionHistoryDataEntity) o;
        return id == that.id && Objects.equals(playerId, that.playerId) && Objects.equals(numberLaps, that.numberLaps) && Objects.equals(numberTyreStints, that.numberTyreStints) && Objects.equals(bestLapTimeLapNumber, that.bestLapTimeLapNumber) && Objects.equals(bestSector1LapNumber, that.bestSector1LapNumber) && Objects.equals(bestSector2LapNumber, that.bestSector2LapNumber) && Objects.equals(bestSector3LapNumber, that.bestSector3LapNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerId, numberLaps, numberTyreStints, bestLapTimeLapNumber, bestSector1LapNumber, bestSector2LapNumber, bestSector3LapNumber);
    }

    public Collection<LapHistoryDataEntity> getLapHistoryData() {
        return lapHistoryData;
    }

    public void setLapHistoryData(Collection<LapHistoryDataEntity> lapHistoryData) {
        this.lapHistoryData = lapHistoryData;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
