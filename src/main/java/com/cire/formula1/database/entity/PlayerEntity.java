package com.cire.formula1.database.entity;

import com.cire.formula1.model.Player;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "player", schema = "public", catalog = "FormulaOne")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "race_session_id", nullable = false, insertable = false, updatable = false)
    private Integer raceSessionId;

    @Column(name = "player_name")
    private String playerName;

    @JsonBackReference("raceSession")
    @ManyToOne
    @JoinColumn(name = "race_session_id", referencedColumnName = "id")
    private RaceSessionEntity raceSession;

    @JsonManagedReference
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private SessionHistoryDataEntity sessionHistoryData;

    public PlayerEntity(Player player){
        this.playerName = player.getPlayerName();
        SessionHistoryDataEntity shde = new SessionHistoryDataEntity(player.getSessionHistoryData());
        shde.setPlayer(this);
        this.sessionHistoryData = shde;
    }

    public PlayerEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRaceSessionId() {
        return raceSessionId;
    }

    public void setRaceSessionId(Integer raceSessionId) {
        this.raceSessionId = raceSessionId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return id == that.id && Objects.equals(raceSessionId, that.raceSessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, raceSessionId);
    }

    public RaceSessionEntity getRaceSession() {
        return raceSession;
    }

    public void setRaceSession(RaceSessionEntity raceSession) {
        this.raceSession = raceSession;
    }

    public SessionHistoryDataEntity getSessionHistoryData() {
        return sessionHistoryData;
    }

    public void setSessionHistoryData(SessionHistoryDataEntity sessionHistoryData) {
        this.sessionHistoryData = sessionHistoryData;
    }
}
