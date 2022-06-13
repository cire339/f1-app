package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.FinalClassificationEntity;
import com.cire.formula1.packet.model.data.FinalClassificationData;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalClassificationDTO {

    private int id;
    private short finalPosition;
    private short numberOfLaps;
    private short gridPosition;
    private short points;
    private short numberOfPitStops;
    private String resultStatus;
    private long bestLapTime;
    private double totalRaceTime;
    private short penaltiesTime;
    private short numberOfPenalties;
    private short numberOfTyreStints;
    @JsonIgnore
    private short[] tyreStintsActual = new short[PacketConstants.TYRE_STINTS];
    @JsonIgnore
    private short[] tyreStintsVisual = new short[PacketConstants.TYRE_STINTS];

    public FinalClassificationDTO(FinalClassificationData data){
        this.finalPosition = data.getPosition();
        this.numberOfLaps = data.getNumLaps();
        this.gridPosition = data.getGridPosition();
        this.points = data.getPoints();
        this.numberOfPitStops = data.getNumPitStops();
        this.resultStatus = data.getResultStatus().name();
        this.bestLapTime = data.getBestLapTimeInMS();
        this.totalRaceTime = data.getTotalRaceTime();
        this.penaltiesTime = data.getPenaltiesTime();
        this.numberOfPenalties = data.getNumPenalties();
        this.numberOfTyreStints = data.getNumTyreStints();
        this.tyreStintsActual = data.getTyreStintsActual();
        this.tyreStintsVisual = data.getTyreStintsVisual();
    }

    public FinalClassificationDTO(FinalClassificationEntity entity){
        this.id = entity.getId();

        if(entity.getFinalPosition() != null) {
            this.finalPosition = entity.getFinalPosition().shortValue();
        }
        if(entity.getNumberOfLaps() != null) {
            this.numberOfLaps = entity.getNumberOfLaps().shortValue();
        }
        if(entity.getGridPosition() != null) {
            this.gridPosition = entity.getGridPosition().shortValue();
        }
        if(entity.getPoints() != null) {
            this.points = entity.getPoints().shortValue();
        }
        if(entity.getNumberOfPitStops() != null) {
            this.numberOfPitStops = entity.getNumberOfPitStops().shortValue();
        }
        this.resultStatus = entity.getResultStatus();
        this.bestLapTime = entity.getBestLapTime();
        this.totalRaceTime = entity.getTotalRaceTime();
        if(entity.getPenaltiesTime() != null) {
            this.penaltiesTime = entity.getPenaltiesTime().shortValue();
        }
        if(entity.getNumberOfPenalties() != null) {
            this.numberOfPenalties = entity.getNumberOfPenalties().shortValue();
        }
        if(entity.getNumberOfTyreStints() != null) {
            this.numberOfTyreStints = entity.getNumberOfTyreStints().shortValue();
        }
    }

    public FinalClassificationDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(short finalPosition) {
        this.finalPosition = finalPosition;
    }

    public short getNumberOfLaps() {
        return numberOfLaps;
    }

    public void setNumberOfLaps(short numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public short getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(short gridPosition) {
        this.gridPosition = gridPosition;
    }

    public short getPoints() {
        return points;
    }

    public void setPoints(short points) {
        this.points = points;
    }

    public short getNumberOfPitStops() {
        return numberOfPitStops;
    }

    public void setNumberOfPitStops(short numberOfPitStops) {
        this.numberOfPitStops = numberOfPitStops;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public long getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(long bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public double getTotalRaceTime() {
        return totalRaceTime;
    }

    public void setTotalRaceTime(double totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    public short getPenaltiesTime() {
        return penaltiesTime;
    }

    public void setPenaltiesTime(short penaltiesTime) {
        this.penaltiesTime = penaltiesTime;
    }

    public short getNumberOfPenalties() {
        return numberOfPenalties;
    }

    public void setNumberOfPenalties(short numberOfPenalties) {
        this.numberOfPenalties = numberOfPenalties;
    }

    public short getNumberOfTyreStints() {
        return numberOfTyreStints;
    }

    public void setNumberOfTyreStints(short numberOfTyreStints) {
        this.numberOfTyreStints = numberOfTyreStints;
    }

    public short[] getTyreStintsActual() {
        return tyreStintsActual;
    }

    public void setTyreStintsActual(short[] tyreStintsActual) {
        this.tyreStintsActual = tyreStintsActual;
    }

    public short[] getTyreStintsVisual() {
        return tyreStintsVisual;
    }

    public void setTyreStintsVisual(short[] tyreStintsVisual) {
        this.tyreStintsVisual = tyreStintsVisual;
    }

    @Override
    public String toString() {
        return "FinalClassificationDTO{" +
                "finalPosition=" + finalPosition +
                ", numberOfLaps=" + numberOfLaps +
                ", gridPosition=" + gridPosition +
                ", points=" + points +
                ", numberOfPitStops=" + numberOfPitStops +
                ", resultStatus=" + resultStatus +
                ", bestLapTime=" + bestLapTime +
                ", totalRaceTime=" + totalRaceTime +
                ", penaltiesTime=" + penaltiesTime +
                ", numberOfPenalties=" + numberOfPenalties +
                ", numberOfTyreStints=" + numberOfTyreStints +
                ", tyreStintsActual=" + Arrays.toString(tyreStintsActual) +
                ", tyreStintsVisual=" + Arrays.toString(tyreStintsVisual) +
                '}';
    }
}
