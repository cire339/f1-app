package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.PlayerDTO;
import com.cire.formula1.model.dto.RaceSessionDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "race_session", schema = "public", catalog = "FormulaOne")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RaceSessionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "session_uid", nullable = false, length = -1)
    private String sessionUid;

    @Column(name = "session_type", length = -1)
    private String sessionType;

    @Column(name = "track_name", length = -1)
    private String trackName;

    @Column(name = "total_laps")
    private Integer totalLaps;

    @Column(name = "fastest_speed")
    private Float fastestSpeed;

    @Column(name = "fastest_speed_car_index")
    private Integer fastestSpeedCarIndex;

    @Column(name = "fastest_lap")
    private Float fastestLap;

    @Column(name = "fastest_lap_car_index")
    private Integer fastestLapCarIndex;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "game_version")
    private String gameVersion;

    @JsonManagedReference
    @OneToMany(mappedBy = "raceSession", cascade = CascadeType.ALL)
    private Set<PlayerEntity> players;

    public RaceSessionEntity(RaceSessionDTO raceSession){
        this.id = raceSession.getId();
        this.sessionUid = String.valueOf(raceSession.getSessionUid());
        this.sessionType = raceSession.getSessionType();
        this.trackName = raceSession.getTrack();
        this.totalLaps = (int) raceSession.getTotalLaps();
        this.fastestSpeed = raceSession.getFastestSpeed();
        this.fastestSpeedCarIndex = (int) raceSession.getFastestSpeedCarIndex();
        this.startTime = raceSession.getStartTime();
        this.gameVersion = raceSession.getGameVersion();
        if(raceSession.getFastestLap() != null) {
            this.fastestLap = raceSession.getFastestLap().getLapTime();
            this.fastestSpeedCarIndex = (int) raceSession.getFastestLap().getCarIndex();
        }
        this.players = new LinkedHashSet<>();
        for(PlayerDTO playerDTO : raceSession.getPlayers()){
            PlayerEntity playerEntity = new PlayerEntity(playerDTO);
            playerEntity.setRaceSession(this);
            this.players.add(playerEntity);
        }
    }

    public RaceSessionEntity(BigInteger sessionUid) {
        this.sessionUid = String.valueOf(sessionUid);
    }

    public RaceSessionEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionUid() {
        return sessionUid;
    }

    public void setSessionUid(String sessionUid) {
        this.sessionUid = sessionUid;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Integer getTotalLaps() {
        return totalLaps;
    }

    public void setTotalLaps(Integer totalLaps) {
        this.totalLaps = totalLaps;
    }

    public Float getFastestSpeed() {
        return fastestSpeed;
    }

    public void setFastestSpeed(Float fastestSpeed) {
        this.fastestSpeed = fastestSpeed;
    }

    public Integer getFastestSpeedCarIndex() {
        return fastestSpeedCarIndex;
    }

    public void setFastestSpeedCarIndex(Integer fastestSpeedCarIndex) {
        this.fastestSpeedCarIndex = fastestSpeedCarIndex;
    }

    public Float getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(Float fastestLap) {
        this.fastestLap = fastestLap;
    }

    public Integer getFastestLapCarIndex() {
        return fastestLapCarIndex;
    }

    public void setFastestLapCarIndex(Integer fastestLapCarIndex) {
        this.fastestLapCarIndex = fastestLapCarIndex;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public Set<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerEntity> players) {
        this.players = players;
    }
}
