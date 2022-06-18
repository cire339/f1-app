package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.PlayerEntity;
import com.cire.formula1.database.entity.RaceSessionEntity;
import com.cire.formula1.packet.model.data.FastestLap;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String sessionType;
    @JsonProperty
    private String track;
    @JsonProperty
    private short totalLaps;

    @JsonIgnore
    private boolean saveToDatabase = false;
    @JsonIgnore
    private short numberActiveCars;

    @JsonProperty
    private List<PlayerDTO> players = new ArrayList<>(PacketConstants.CARS);

    public RaceSessionDTO(){

    }

    public RaceSessionDTO(RaceSessionEntity entity){
        this.id = entity.getId();
        this.sessionUid = new BigInteger(entity.getSessionUid());
        this.sessionType = entity.getSessionType();
        this.track = entity.getTrackName();
        if(entity.getTotalLaps() != null) {
            this.totalLaps = entity.getTotalLaps().shortValue();
        }
        this.fastestSpeed = entity.getFastestSpeed();
        if(entity.getFastestSpeedCarIndex() != null) {
            this.fastestSpeedCarIndex = entity.getFastestSpeedCarIndex().shortValue();
        }
        if(entity.getFastestLapCarIndex() != null && entity.getFastestLap() != null) {
            FastestLap fl = new FastestLap();
            fl.setCarIndex(entity.getFastestLapCarIndex().shortValue());
            fl.setLapTime(entity.getFastestLap());
            this.fastestLap = fl;
        }
        this.players = new ArrayList<>();
        if(entity.getPlayers() != null) {
            for (PlayerEntity playerEntity : entity.getPlayers()) {
                PlayerDTO playerDTO = new PlayerDTO(playerEntity);
                this.players.add(playerDTO);
            }
            orderPlayersByCarIndex();
        }
        //Set this to true since it's in the DB.
        this.saveToDatabase = true;
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

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public short getTotalLaps() {
        return totalLaps;
    }

    public void setTotalLaps(short totalLaps) {
        this.totalLaps = totalLaps;
    }

    public boolean isSaveToDatabase() {
        return saveToDatabase;
    }

    public void setSaveToDatabase(boolean saveToDatabase) {
        this.saveToDatabase = saveToDatabase;
    }

    public short getNumberActiveCars() {
        return numberActiveCars;
    }

    public void setNumberActiveCars(short numberActiveCars) {
        this.numberActiveCars = numberActiveCars;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> playerDTOS) {
        this.players = playerDTOS;
    }

    @Override
    public String toString() {
        return "RaceSessionDTO{" +
                "id=" + id +
                ", sessionUid=" + sessionUid +
                ", raceWinnerCarIndex=" + raceWinnerCarIndex +
                ", fastestLap=" + fastestLap +
                ", fastestSpeedCarIndex=" + fastestSpeedCarIndex +
                ", fastestSpeed=" + fastestSpeed +
                ", raceStarted=" + raceStarted +
                ", raceEnded=" + raceEnded +
                ", sessionType='" + sessionType + '\'' +
                ", track='" + track + '\'' +
                ", totalLaps=" + totalLaps +
                ", saveToDatabase=" + saveToDatabase +
                ", numberActiveCars=" + numberActiveCars +
                ", players=" + players +
                '}';
    }

    private void orderPlayersByCarIndex(){
        boolean sorted = false;
        PlayerDTO temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < this.getPlayers().size() - 1; i++) {
                if (this.getPlayers().get(i).getCarIndex() > this.getPlayers().get(i+1).getCarIndex()) {
                    temp = this.getPlayers().get(i);
                    this.getPlayers().set(i, this.getPlayers().get(i+1));
                    this.getPlayers().set(i+1, temp);
                    sorted = false;
                }
            }
        }
    }
}
