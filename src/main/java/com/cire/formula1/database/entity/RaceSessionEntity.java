package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.PlayerDTO;
import com.cire.formula1.model.dto.RaceSessionDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "raceSession", cascade = CascadeType.ALL)
    private Collection<PlayerEntity> players;

    public RaceSessionEntity(RaceSessionDTO raceSession){
        this.id = raceSession.getId();
        this.sessionUid = String.valueOf(raceSession.getSessionUid());
        this.players = new ArrayList<>();
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

    public Collection<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<PlayerEntity> players) {
        this.players = players;
    }
}
