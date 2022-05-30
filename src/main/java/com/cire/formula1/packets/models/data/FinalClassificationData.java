package com.cire.formula1.packets.models.data;

import com.cire.formula1.packets.utils.PacketConstants;
import com.cire.formula1.packets.models.constants.ResultStatus;

import java.util.Arrays;

public class FinalClassificationData {

    private short position;
    private short numLaps;
    private short gridPosition;
    private short points;
    private short numPitStops;
    private ResultStatus resultStatus;
    private long bestLapTimeInMS;
    private double totalRaceTime;
    private short penaltiesTime;
    private short numPenalties;
    private short numTyreStints;
    private short tyreStintsActual[] = new short[PacketConstants.TYRE_STINTS];
    private short tyreStintsVisual[] = new short[PacketConstants.TYRE_STINTS];

    /**
     * @return Finishing position
     */
    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    /**
     * @return Number of laps completed
     */
    public short getNumLaps() {
        return numLaps;
    }

    public void setNumLaps(short numLaps) {
        this.numLaps = numLaps;
    }

    /**
     * @return Grid position of the car
     */
    public short getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(short gridPosition) {
        this.gridPosition = gridPosition;
    }

    /**
     * @return Number of points scored
     */
    public short getPoints() {
        return points;
    }

    public void setPoints(short points) {
        this.points = points;
    }

    /**
     * @return Number of pit stops made
     */
    public short getNumPitStops() {
        return numPitStops;
    }

    public void setNumPitStops(short numPitStops) {
        this.numPitStops = numPitStops;
    }

    /**
     * @return Result status
     * 0 = invalid, 1 = inactive, 2 = active
     * 3 = finished, 4 = disqualified, 5 = not classified
     * 6 = retired
     */
    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    /**
     * @return Best lap time of the session in ms
     */
    public long getBestLapTimeInMS() {
        return bestLapTimeInMS;
    }

    public void setBestLapTimeInMS(long bestLapTimeInMS) {
        this.bestLapTimeInMS = bestLapTimeInMS;
    }

    /**
     * @return Total race time in seconds without penalties
     */
    public double getTotalRaceTime() {
        return totalRaceTime;
    }

    public void setTotalRaceTime(double totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    /**
     * @return Total penalties accumulated in seconds
     */
    public short getPenaltiesTime() {
        return penaltiesTime;
    }

    public void setPenaltiesTime(short penaltiesTime) {
        this.penaltiesTime = penaltiesTime;
    }

    /**
     * @return Number of penalties applied to this driver
     */
    public short getNumPenalties() {
        return numPenalties;
    }

    public void setNumPenalties(short numPenalties) {
        this.numPenalties = numPenalties;
    }

    /**
     * @return Number of tyres stints up to maximum
     */
    public short getNumTyreStints() {
        return numTyreStints;
    }

    public void setNumTyreStints(short numTyreStints) {
        this.numTyreStints = numTyreStints;
    }

    /**
     * @return Actual tyres used by this driver
     */
    public short[] getTyreStintsActual() {
        return tyreStintsActual;
    }

    public void setTyreStintsActual(short[] tyreStintsActual) {
        this.tyreStintsActual = tyreStintsActual;
    }

    /**
     * @return Visual tyres used by this driver
     */
    public short[] getTyreStintsVisual() {
        return tyreStintsVisual;
    }

    public void setTyreStintsVisual(short[] tyreStintsVisual) {
        this.tyreStintsVisual = tyreStintsVisual;
    }

    @Override
    public String toString() {
        return "FinalClassificationData[" +
                ",position=" + this.position +
                ",numLaps=" + this.numLaps +
                ",gridPosition=" + this.gridPosition +
                ",points=" + this.points +
                ",numPitStops=" + this.numPitStops +
                ",resultStatus=" + this.resultStatus +
                ",bestLapTimeInMS=" + this.bestLapTimeInMS +
                ",totalRaceTime=" + this.totalRaceTime +
                ",penaltiesTime=" + this.penaltiesTime +
                ",numPenalties=" + this.numPenalties +
                ",numTyreStints=" + this.numTyreStints +
                ",tyreStintsActual=" + Arrays.toString(tyreStintsActual) +
                ",tyreStintsVisual=" + Arrays.toString(this.tyreStintsVisual) +
                "]";
    }
}
