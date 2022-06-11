package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.PenaltyDTO;
import com.cire.formula1.model.dto.PlayerDTO;
import com.cire.formula1.packet.model.data.Penalty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    @Column(name = "car_index")
    private Integer carIndex;

    @Column(name = "player_name")
    private String playerName;

    @JsonBackReference("raceSession")
    @ManyToOne
    @JoinColumn(name = "race_session_id", referencedColumnName = "id")
    private RaceSessionEntity raceSession;

    @JsonManagedReference
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private SessionHistoryEntity sessionHistory;

    @JsonManagedReference
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Collection<PenaltyEntity> penalties;

    public PlayerEntity(PlayerDTO playerDTO){
        this.id = playerDTO.getId();
        this.carIndex = playerDTO.getCarIndex();
        this.playerName = playerDTO.getPlayerName();
        SessionHistoryEntity shde = new SessionHistoryEntity(playerDTO.getSessionHistory());
        shde.setPlayer(this);
        this.sessionHistory = shde;
        //Merge the 2 penalty lists and convert.
        List<PenaltyDTO> penalties = playerDTO.getPenalties();
        penalties.addAll(playerDTO.getInvolvedPenalties());
        List<PenaltyEntity> penaltyEntities = new ArrayList<>();
        for(PenaltyDTO penalty: penalties){
            PenaltyEntity penaltyEntity = new PenaltyEntity(penalty);
            penaltyEntity.setPlayer(this);
            penaltyEntities.add(penaltyEntity);
        }
        this.penalties = penaltyEntities;
    }

    public PlayerEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(Integer carIndex) {
        this.carIndex = carIndex;
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

    public RaceSessionEntity getRaceSession() {
        return raceSession;
    }

    public void setRaceSession(RaceSessionEntity raceSession) {
        this.raceSession = raceSession;
    }

    public SessionHistoryEntity getSessionHistory() {
        return sessionHistory;
    }

    public void setSessionHistory(SessionHistoryEntity sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    public Collection<PenaltyEntity> getPenalties() {
        return penalties;
    }

    public void setPenalties(Collection<PenaltyEntity> penalties) {
        this.penalties = penalties;
    }
}
