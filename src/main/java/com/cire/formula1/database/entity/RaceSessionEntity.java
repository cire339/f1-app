package com.cire.formula1.database.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "race_session", schema = "public", catalog = "FormulaOne")
public class RaceSessionEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "session_uid")
    private String sessionUid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
