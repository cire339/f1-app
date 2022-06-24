package com.cire.formula1.model;

import com.cire.formula1.database.entity.LapHistoryEntity;
import com.cire.formula1.model.dto.CarSetupDTO;
import com.cire.formula1.model.dto.LapHistoryDTO;

public class FastestLapInfo {

    private String playerName;
    private String sessionType;
    private LapHistoryDTO lapData;
    private CarSetupDTO carSetup;

    public FastestLapInfo(LapHistoryEntity lapHistoryEntity) {
        this.playerName = lapHistoryEntity.getSessionHistoryData().getPlayer().getPlayerName();
        this.sessionType = lapHistoryEntity.getSessionHistoryData().getPlayer().getRaceSession().getSessionType();
        this.lapData = new LapHistoryDTO(lapHistoryEntity);
        if(lapHistoryEntity.getSessionHistoryData().getPlayer().getCarSetup() != null){
            this.carSetup = new CarSetupDTO(lapHistoryEntity.getSessionHistoryData().getPlayer().getCarSetup());
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public LapHistoryDTO getLapData() {
        return lapData;
    }

    public void setLapData(LapHistoryDTO lapData) {
        this.lapData = lapData;
    }

    public CarSetupDTO getCarSetup() {
        return carSetup;
    }

    public void setCarSetup(CarSetupDTO carSetup) {
        this.carSetup = carSetup;
    }
}
