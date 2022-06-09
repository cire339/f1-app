package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.LapHistoryDTO;
import com.cire.formula1.model.dto.SessionHistoryDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "session_history", schema = "public", catalog = "FormulaOne")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionHistoryEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "player_id", nullable = false, insertable = false, updatable = false)
    private Integer playerId;

    @Column(name = "number_laps")
    private Integer numberLaps;

    @Column(name = "number_tyre_stints")
    private Integer numberTyreStints;

    @Column(name = "best_lap_time_lap_number")
    private Integer bestLapTimeLapNumber;

    @Column(name = "best_sector_1_lap_number")
    private Integer bestSector1LapNumber;

    @Column(name = "best_sector_2_lap_number")
    private Integer bestSector2LapNumber;

    @Column(name = "best_sector_3_lap_number")
    private Integer bestSector3LapNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "sessionHistoryData", cascade = CascadeType.ALL)
    private Collection<LapHistoryEntity> lapHistory;

    @JsonBackReference("player")
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayerEntity player;

    public SessionHistoryEntity(SessionHistoryDTO data){
        if(data != null) {
            this.id = data.getId();
            this.numberLaps = (int) data.getNumLaps();
            this.numberTyreStints = (int) data.getNumTyreStints();
            this.bestLapTimeLapNumber = (int) data.getBestLapTimeLapNum();
            this.bestSector1LapNumber = (int) data.getBestSector1LapNum();
            this.bestSector2LapNumber = (int) data.getBestSector2LapNum();
            this.bestSector3LapNumber = (int) data.getBestSector3LapNum();
            if(data.getLapHistory() != null) {
                this.lapHistory = new ArrayList<>();
                for (LapHistoryDTO lapHistory : data.getLapHistory()) {
                    LapHistoryEntity lapHistoryDataEntity = new LapHistoryEntity(lapHistory);
                    lapHistoryDataEntity.setSessionHistoryData(this);
                    this.lapHistory.add(lapHistoryDataEntity);
                }
            }
        }

    }

    public SessionHistoryEntity() {

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
        SessionHistoryEntity that = (SessionHistoryEntity) o;
        return id == that.id && Objects.equals(playerId, that.playerId) && Objects.equals(numberLaps, that.numberLaps) && Objects.equals(numberTyreStints, that.numberTyreStints) && Objects.equals(bestLapTimeLapNumber, that.bestLapTimeLapNumber) && Objects.equals(bestSector1LapNumber, that.bestSector1LapNumber) && Objects.equals(bestSector2LapNumber, that.bestSector2LapNumber) && Objects.equals(bestSector3LapNumber, that.bestSector3LapNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerId, numberLaps, numberTyreStints, bestLapTimeLapNumber, bestSector1LapNumber, bestSector2LapNumber, bestSector3LapNumber);
    }

    public Collection<LapHistoryEntity> getLapHistory() {
        return lapHistory;
    }

    public void setLapHistory(Collection<LapHistoryEntity> lapHistory) {
        this.lapHistory = lapHistory;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
