package com.cire.formula1.database.entity;

import com.cire.formula1.model.dto.LapHistoryDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lap_history_data", schema = "public", catalog = "FormulaOne")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LapHistoryEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "session_history_id", nullable = false, insertable = false, updatable = false)
    private Integer sessionHistoryDataId;

    @Column(name = "lap_time")
    private Long lapTime;

    @Column(name = "sector_1_time")
    private Long sector1Time;

    @Column(name = "sector_2_time")
    private Long sector2Time;

    @Column(name = "sector_3_time")
    private Long sector3Time;

    @Column(name = "lap_valid_flag")
    private Integer lapValidFlag;

    @JsonBackReference("sessionHistoryData")
    @ManyToOne
    @JoinColumn(name = "session_history_id", referencedColumnName = "id")
    private SessionHistoryEntity sessionHistoryData;

    public LapHistoryEntity(LapHistoryDTO lapHistory) {
        this.lapTime = lapHistory.getLapTimeInMS();
        this.sector1Time = (long) lapHistory.getSector1TimeInMS();
        this.sector2Time = (long) lapHistory.getSector2TimeInMS();
        this.sector3Time = (long) lapHistory.getSector3TimeInMS();
        this.lapValidFlag = (int) lapHistory.getLapValidBitFlags();
    }

    public LapHistoryEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSessionHistoryDataId() {
        return sessionHistoryDataId;
    }

    public void setSessionHistoryDataId(Integer sessionHistoryDataId) {
        this.sessionHistoryDataId = sessionHistoryDataId;
    }

    public Long getLapTime() {
        return lapTime;
    }

    public void setLapTime(Long lapTime) {
        this.lapTime = lapTime;
    }

    public Long getSector1Time() {
        return sector1Time;
    }

    public void setSector1Time(Long sector1Time) {
        this.sector1Time = sector1Time;
    }

    public Long getSector2Time() {
        return sector2Time;
    }

    public void setSector2Time(Long sector2Time) {
        this.sector2Time = sector2Time;
    }

    public Long getSector3Time() {
        return sector3Time;
    }

    public void setSector3Time(Long sector3Time) {
        this.sector3Time = sector3Time;
    }

    public Integer getLapValidFlag() {
        return lapValidFlag;
    }

    public void setLapValidFlag(Integer lapValidFlag) {
        this.lapValidFlag = lapValidFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LapHistoryEntity that = (LapHistoryEntity) o;
        return id == that.id && Objects.equals(sessionHistoryDataId, that.sessionHistoryDataId) && Objects.equals(lapTime, that.lapTime) && Objects.equals(sector1Time, that.sector1Time) && Objects.equals(sector2Time, that.sector2Time) && Objects.equals(sector3Time, that.sector3Time) && Objects.equals(lapValidFlag, that.lapValidFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionHistoryDataId, lapTime, sector1Time, sector2Time, sector3Time, lapValidFlag);
    }

    public SessionHistoryEntity getSessionHistoryData() {
        return sessionHistoryData;
    }

    public void setSessionHistoryData(SessionHistoryEntity sessionHistoryData) {
        this.sessionHistoryData = sessionHistoryData;
    }
}
