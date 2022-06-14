package com.cire.formula1.model.dto;

import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.database.entity.SessionHistoryEntity;
import com.cire.formula1.packet.model.PacketSessionHistoryData;
import com.cire.formula1.packet.model.data.TyreStintHistoryData;
import com.cire.formula1.packet.util.PacketConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionHistoryDTO {
    private int id;
    private short numLaps;
    private short numTyreStints;
    private short bestLapTimeLapNum;
    private short bestSector1LapNum;
    private short bestSector2LapNum;
    private short bestSector3LapNum;

    @JsonIgnore
    private Set<LapHistoryDTO> lapHistory = new HashSet<>(PacketConstants.LAPS);

    @JsonIgnore
    private List<TyreStintHistoryData> tyreStintHistoryData = new ArrayList<>(PacketConstants.TYRE_STINTS);

    public SessionHistoryDTO(PacketSessionHistoryData data){
        this.numLaps = data.getNumLaps();
        this.numTyreStints = data.getNumTyreStints();
        this.bestLapTimeLapNum = data.getBestLapTimeLapNum();
        this.bestSector1LapNum = data.getBestSector1LapNum();
        this.bestSector2LapNum = data.getBestSector2LapNum();
        this.bestSector3LapNum = data.getBestSector3LapNum();
        if(data.getLapHistoryData() != null && !data.getLapHistoryData().isEmpty()){
            Set<LapHistoryDTO> laps = new HashSet<>();
            //Only get the laps that have data.
            for(int i=0; i<numLaps;i++){
                laps.add(new LapHistoryDTO(data.getLapHistoryData().get(i), i));
            }
            this.lapHistory = laps;
        }
        this.tyreStintHistoryData = data.getTyreStintHistoryData();
    }

    public SessionHistoryDTO() {

    }

    public SessionHistoryDTO(SessionHistoryEntity sessionHistoryData) {
        this.id = sessionHistoryData.getId();
        if(sessionHistoryData.getNumberLaps() != null) {
            this.numLaps = sessionHistoryData.getNumberLaps().shortValue();
        }
        if(sessionHistoryData.getNumberTyreStints() != null) {
            this.numTyreStints = sessionHistoryData.getNumberTyreStints().shortValue();
        }
        if(sessionHistoryData.getBestLapTimeLapNumber() != null){
            this.bestLapTimeLapNum = sessionHistoryData.getBestLapTimeLapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector1LapNumber() != null){
            this.bestSector1LapNum = sessionHistoryData.getBestSector1LapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector2LapNumber() != null){
            this.bestSector2LapNum = sessionHistoryData.getBestSector2LapNumber().shortValue();
        }
        if(sessionHistoryData.getBestSector3LapNumber() != null){
            this.bestSector3LapNum = sessionHistoryData.getBestSector2LapNumber().shortValue();
        }
        if(sessionHistoryData.getLapHistory() != null && !sessionHistoryData.getLapHistory().isEmpty()) {
            this.lapHistory = new HashSet<>();
            for (LapHistoryEntity lapHistoryDataEntity : sessionHistoryData.getLapHistory()) {
                this.lapHistory.add(new LapHistoryDTO(lapHistoryDataEntity));
            }
            orderLapHistoryByLapNumber();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getNumLaps() {
        return numLaps;
    }

    public void setNumLaps(short numLaps) {
        this.numLaps = numLaps;
    }

    public short getNumTyreStints() {
        return numTyreStints;
    }

    public void setNumTyreStints(short numTyreStints) {
        this.numTyreStints = numTyreStints;
    }

    public short getBestLapTimeLapNum() {
        return bestLapTimeLapNum;
    }

    public void setBestLapTimeLapNum(short bestLapTimeLapNum) {
        this.bestLapTimeLapNum = bestLapTimeLapNum;
    }

    public short getBestSector1LapNum() {
        return bestSector1LapNum;
    }

    public void setBestSector1LapNum(short bestSector1LapNum) {
        this.bestSector1LapNum = bestSector1LapNum;
    }

    public short getBestSector2LapNum() {
        return bestSector2LapNum;
    }

    public void setBestSector2LapNum(short bestSector2LapNum) {
        this.bestSector2LapNum = bestSector2LapNum;
    }

    public short getBestSector3LapNum() {
        return bestSector3LapNum;
    }

    public void setBestSector3LapNum(short bestSector3LapNum) {
        this.bestSector3LapNum = bestSector3LapNum;
    }

    public Set<LapHistoryDTO> getLapHistory() {
        return lapHistory;
    }

    public void setLapHistory(Set<LapHistoryDTO> lapHistory) {
        this.lapHistory = lapHistory;
    }

    public List<TyreStintHistoryData> getTyreStintHistoryData() {
        return tyreStintHistoryData;
    }

    public void setTyreStintHistoryData(List<TyreStintHistoryData> tyreStintHistoryData) {
        this.tyreStintHistoryData = tyreStintHistoryData;
    }

    @Override
    public String toString() {
        return "SessionHistoryData{" +
                "id=" + id +
                ", numLaps=" + numLaps +
                ", numTyreStints=" + numTyreStints +
                ", bestLapTimeLapNum=" + bestLapTimeLapNum +
                ", bestSector1LapNum=" + bestSector1LapNum +
                ", bestSector2LapNum=" + bestSector2LapNum +
                ", bestSector3LapNum=" + bestSector3LapNum +
                ", lapHistory=" + lapHistory +
                ", tyreStintHistoryData=" + tyreStintHistoryData +
                '}';
    }

    public void updateSessionHistory(SessionHistoryDTO newSH){
        this.setNumLaps(newSH.getNumLaps());
        this.setNumTyreStints(newSH.getNumTyreStints());
        this.setBestLapTimeLapNum(newSH.getBestLapTimeLapNum());
        this.setBestSector1LapNum(newSH.getBestSector1LapNum());
        this.setBestSector2LapNum(newSH.getBestSector2LapNum());
        this.setBestSector3LapNum(newSH.getBestSector3LapNum());
        List<LapHistoryDTO> lapHistoryList = new ArrayList<>(this.getLapHistory());
        List<LapHistoryDTO> newLapHistoryList = new ArrayList<>(newSH.getLapHistory());
        //Update old laps data.
        for(int i=0;i<lapHistoryList.size();i++){
            lapHistoryList.get(i).updateLapHistory(newLapHistoryList.get(i));
        }
        //Add new lap data
        for(int i=this.getLapHistory().size(); i<newLapHistoryList.size();i++){
            lapHistoryList.add(newLapHistoryList.get(i));
        }
        this.getLapHistory().clear();
        this.getLapHistory().addAll(lapHistoryList);
        this.setTyreStintHistoryData(newSH.getTyreStintHistoryData());
    }

    private void orderLapHistoryByLapNumber(){
        boolean sorted = false;
        LapHistoryDTO temp;
        List<LapHistoryDTO> lapHistoryList = new ArrayList<>(this.getLapHistory());
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < lapHistoryList.size() - 1; i++) {
                if (lapHistoryList.get(i).getLapNumber() > lapHistoryList.get(i+1).getLapNumber()) {
                    temp = lapHistoryList.get(i);
                    lapHistoryList.set(i, lapHistoryList.get(i+1));
                    lapHistoryList.set(i+1, temp);
                    sorted = false;
                }
            }
        }
        this.lapHistory.clear();
        this.lapHistory.addAll(lapHistoryList);
    }
}
