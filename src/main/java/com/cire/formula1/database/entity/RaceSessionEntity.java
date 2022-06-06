package com.cire.formula1.database.entity;

import com.cire.formula1.model.RaceSession;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "race_session", schema = "public", catalog = "FormulaOne")
public class RaceSessionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "session_uid")
    private String sessionUid;

    public RaceSessionEntity(RaceSession raceSession){
        this.sessionUid = String.valueOf(raceSession.getSessionUid());
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
}
