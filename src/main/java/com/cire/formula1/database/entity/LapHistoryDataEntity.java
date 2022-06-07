package com.cire.formula1.database.entity;

import com.cire.formula1.packet.model.data.LapHistoryData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lap_history_data", schema = "public", catalog = "FormulaOne")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LapHistoryDataEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "session_history_data_id", nullable = false, insertable = false, updatable = false)
    private Integer sessionHistoryDataId;

    @Column(name = "lap_time", nullable = true)
    private Long lapTime;

    @Column(name = "sector_1_time", nullable = true)
    private Long sector1Time;

    @Column(name = "sector_2_time", nullable = true)
    private Long sector2Time;

    @Column(name = "sector_3_time", nullable = true)
    private Long sector3Time;

    @Column(name = "lap_valid_flag", nullable = true)
    private Integer lapValidFlag;

    @JsonBackReference("sessionHistoryData")
    @ManyToOne
    @JoinColumn(name = "session_history_data_id", referencedColumnName = "id")
    private SessionHistoryDataEntity sessionHistoryData;

    public LapHistoryDataEntity(LapHistoryData lapHistoryData) {
        this.lapTime = lapHistoryData.getLapTimeInMS();
        this.sector1Time = (long) lapHistoryData.getSector1TimeInMS();
        this.sector2Time = (long) lapHistoryData.getSector2TimeInMS();
        this.sector3Time = (long) lapHistoryData.getSector3TimeInMS();
        this.lapValidFlag = (int) lapHistoryData.getLapValidBitFlags();
    }

    public LapHistoryDataEntity() {

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
        LapHistoryDataEntity that = (LapHistoryDataEntity) o;
        return id == that.id && Objects.equals(sessionHistoryDataId, that.sessionHistoryDataId) && Objects.equals(lapTime, that.lapTime) && Objects.equals(sector1Time, that.sector1Time) && Objects.equals(sector2Time, that.sector2Time) && Objects.equals(sector3Time, that.sector3Time) && Objects.equals(lapValidFlag, that.lapValidFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionHistoryDataId, lapTime, sector1Time, sector2Time, sector3Time, lapValidFlag);
    }

    public SessionHistoryDataEntity getSessionHistoryData() {
        return sessionHistoryData;
    }

    public void setSessionHistoryData(SessionHistoryDataEntity sessionHistoryData) {
        this.sessionHistoryData = sessionHistoryData;
    }
}
