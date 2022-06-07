package com.cire.formula1.database.entity;

import com.cire.formula1.model.Player;
import com.cire.formula1.model.RaceSession;
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

    public RaceSessionEntity(RaceSession raceSession){
        this.sessionUid = String.valueOf(raceSession.getSessionUid());
        this.players = new ArrayList<>();
        for(Player player: raceSession.getPlayers()){
            PlayerEntity playerEntity = new PlayerEntity(player);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceSessionEntity that = (RaceSessionEntity) o;
        return id == that.id && Objects.equals(sessionUid, that.sessionUid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionUid);
    }

    public Collection<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<PlayerEntity> players) {
        this.players = players;
    }
}
