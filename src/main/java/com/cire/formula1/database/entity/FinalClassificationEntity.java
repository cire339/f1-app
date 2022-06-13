package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.FinalClassificationDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "final_classification", schema = "public", catalog = "FormulaOne")
public class FinalClassificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "player_id", nullable = false, insertable = false, updatable = false)
    private int playerId;
    @Basic
    @Column(name = "final_position")
    private Integer finalPosition;
    @Basic
    @Column(name = "number_of_laps")
    private Integer numberOfLaps;
    @Basic
    @Column(name = "grid_position")
    private Integer gridPosition;
    @Basic
    @Column(name = "points")
    private Integer points;
    @Basic
    @Column(name = "number_of_pit_stops")
    private Integer numberOfPitStops;
    @Basic
    @Column(name = "result_status", length = -1)
    private String resultStatus;
    @Basic
    @Column(name = "best_lap_time")
    private Long bestLapTime;
    @Basic
    @Column(name = "total_race_time")
    private Long totalRaceTime;
    @Basic
    @Column(name = "penalties_time")
    private Integer penaltiesTime;
    @Basic
    @Column(name = "number_of_penalties")
    private Integer numberOfPenalties;
    @Basic
    @Column(name = "number_of_tyre_stints")
    private Integer numberOfTyreStints;
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private PlayerEntity player;

    public FinalClassificationEntity(FinalClassificationDTO finalClassification) {
        this.id = finalClassification.getId();
        this.finalPosition = (int) finalClassification.getFinalPosition();
        this.numberOfLaps = (int) finalClassification.getNumberOfLaps();
        this.gridPosition = (int) finalClassification.getGridPosition();
        this.points = (int) finalClassification.getPoints();
        this.numberOfPitStops = (int) finalClassification.getNumberOfPitStops();
        this.resultStatus = finalClassification.getResultStatus();
        this.bestLapTime = finalClassification.getBestLapTime();
        this.totalRaceTime = (long) finalClassification.getTotalRaceTime();
        this.penaltiesTime = (int) finalClassification.getPenaltiesTime();
        this.numberOfPenalties = (int) finalClassification.getNumberOfPenalties();
        this.numberOfTyreStints = (int) finalClassification.getNumberOfTyreStints();
    }

    public FinalClassificationEntity() {

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

    public Integer getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(Integer finalPosition) {
        this.finalPosition = finalPosition;
    }

    public Integer getNumberOfLaps() {
        return numberOfLaps;
    }

    public void setNumberOfLaps(Integer numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public Integer getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(Integer gridPosition) {
        this.gridPosition = gridPosition;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getNumberOfPitStops() {
        return numberOfPitStops;
    }

    public void setNumberOfPitStops(Integer numberOfPitStops) {
        this.numberOfPitStops = numberOfPitStops;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Long getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(Long bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public Long getTotalRaceTime() {
        return totalRaceTime;
    }

    public void setTotalRaceTime(Long totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    public Integer getPenaltiesTime() {
        return penaltiesTime;
    }

    public void setPenaltiesTime(Integer penaltiesTime) {
        this.penaltiesTime = penaltiesTime;
    }

    public Integer getNumberOfPenalties() {
        return numberOfPenalties;
    }

    public void setNumberOfPenalties(Integer numberOfPenalties) {
        this.numberOfPenalties = numberOfPenalties;
    }

    public Integer getNumberOfTyreStints() {
        return numberOfTyreStints;
    }

    public void setNumberOfTyreStints(Integer numberOfTyreStints) {
        this.numberOfTyreStints = numberOfTyreStints;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
