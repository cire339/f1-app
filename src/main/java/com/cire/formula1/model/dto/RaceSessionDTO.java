package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.packet.model.data.FastestLap;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RaceSessionDTO {

    /**
     * Object that will keep track of data in a session
     */
    @JsonProperty
    private int id;
    @JsonProperty
    private BigInteger sessionUid;
    @JsonProperty
    private short raceWinnerCarIndex;
    @JsonProperty
    private FastestLap fastestLap;
    @JsonProperty
    private short fastestSpeedCarIndex;
    @JsonProperty
    private Float fastestSpeed = 0F;
    @JsonProperty
    private boolean raceStarted = false;
    @JsonProperty
    private boolean raceEnded = false;
    @JsonProperty
    private List<PlayerDTO> playerDTOS = new ArrayList<>(PacketConstants.CARS);

    public RaceSessionDTO(){
        for(int i=0;i<PacketConstants.CARS;i++){
            this.playerDTOS.add(new PlayerDTO((short) i));
        }
    }

    public RaceSessionDTO(RaceSessionEntity entity){
        this.id = entity.getId();
        this.sessionUid = new BigInteger(entity.getSessionUid());
        this.playerDTOS = new ArrayList<>();
        if(entity.getPlayers() != null) {
            for (PlayerEntity playerEntity : entity.getPlayers()) {
                PlayerDTO playerDTO = new PlayerDTO(playerEntity);
                this.playerDTOS.add(playerDTO);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getSessionUid() {
        return sessionUid;
    }

    public short getRaceWinnerCarIndex() {
        return raceWinnerCarIndex;
    }

    public void setRaceWinnerCarIndex(short raceWinnerCarIndex) {
        this.raceWinnerCarIndex = raceWinnerCarIndex;
    }

    public void setSessionUid(BigInteger sessionUid) {
        this.sessionUid = sessionUid;
    }

    public FastestLap getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(FastestLap fastestLap) {
        this.fastestLap = fastestLap;
    }

    public short getFastestSpeedCarIndex() {
        return fastestSpeedCarIndex;
    }

    public void setFastestSpeedCarIndex(short fastestSpeedCarIndex) {
        this.fastestSpeedCarIndex = fastestSpeedCarIndex;
    }

    public Float getFastestSpeed() {
        return fastestSpeed;
    }

    public void setFastestSpeed(Float fastestSpeed) {
        this.fastestSpeed = fastestSpeed;
    }

    public boolean isRaceStarted() {
        return raceStarted;
    }

    public void setRaceStarted(boolean raceStarted) {
        this.raceStarted = raceStarted;
    }

    public boolean isRaceEnded() {
        return raceEnded;
    }

    public void setRaceEnded(boolean raceEnded) {
        this.raceEnded = raceEnded;
    }

    public List<PlayerDTO> getPlayers() {
        return playerDTOS;
    }

    public void setPlayers(List<PlayerDTO> playerDTOS) {
        this.playerDTOS = playerDTOS;
    }

    @Override
    public String toString() {
        return "RaceSession{" +
                "id=" + id +
                ", sessionUid=" + sessionUid +
                ", raceWinnerCarIndex=" + raceWinnerCarIndex +
                ", fastestLap=" + fastestLap +
                ", fastestSpeedCarIndex=" + fastestSpeedCarIndex +
                ", fastestSpeed=" + fastestSpeed +
                ", raceStarted=" + raceStarted +
                ", raceEnded=" + raceEnded +
                ", players=" + playerDTOS +
                '}';
    }
}