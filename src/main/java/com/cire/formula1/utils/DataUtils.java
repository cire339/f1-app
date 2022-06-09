package com.cire.formula1.utils;

import com.cire.formula1.model.dto.LapHistoryDTO;
import com.cire.formula1.model.dto.PlayerDTO;
import com.cire.formula1.model.dto.RaceSessionDTO;
import com.cire.formula1.model.dto.SessionHistoryDTO;
import com.cire.formula1.packet.model.constants.InfringementType;
import com.cire.formula1.packet.model.constants.PenaltyType;
import com.cire.formula1.packet.model.data.CarSetupData;
import com.cire.formula1.packet.model.data.Penalty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {


    public static RaceSessionDTO createTestSession(BigInteger sessionUid){
        //Race Session
        RaceSessionDTO raceSession = new RaceSessionDTO();
        raceSession.setSessionUid(sessionUid);
        raceSession.setRaceStarted(true);
        raceSession.setRaceEnded(true);
        raceSession.setRaceWinnerCarIndex((short)0);
        raceSession.setFastestSpeed(321.0F);
        raceSession.setFastestSpeedCarIndex((short)0);

        //Player
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        PlayerDTO playerDTO = new PlayerDTO((short)0);
        playerDTO.setPlayerName("Test Player");

        //Car setup
        CarSetupData carSetupData = new CarSetupData();
        carSetupData.setFrontWing((short)1);
        carSetupData.setRearWing((short)1);
        carSetupData.setOnThrottle((short)1);
        carSetupData.setOffThrottle((short)1);
        carSetupData.setFrontCamber((float)1);
        carSetupData.setRearCamber((float)1);
        carSetupData.setFrontToe((float)1);
        carSetupData.setRearToe((float)1);
        carSetupData.setFrontSuspension((short)1);
        carSetupData.setRearSuspension((short)1);
        carSetupData.setFrontAntiRollBar((short)1);
        carSetupData.setRearAntiRollBar((short)1);
        carSetupData.setFrontSuspensionHeight((short)1);
        carSetupData.setRearSuspensionHeight((short)1);
        carSetupData.setBrakePressure((short)1);
        carSetupData.setBrakeBias((short)1);
        carSetupData.setRearLeftTyrePressure((float)1);
        carSetupData.setRearRightTyrePressure((float)1);
        carSetupData.setFrontLeftTyrePressure((float)1);
        carSetupData.setFrontRightTyrePressure((float)1);
        carSetupData.setBallast((short)1);
        carSetupData.setFuelLoad((float)1);

        //Penalties
        List<Penalty> penalties = new ArrayList<>();
        Penalty penalty1 = new Penalty();
        penalty1.setPenaltyType(PenaltyType.WARNING);
        penalty1.setInfringementType(InfringementType.CORNER_CUTTING_GAINED_TIME);
        penalty1.setCarIndex((short)0);
        penalty1.setOtherCarIndex((short)1);
        penalty1.setTime((short)28466);
        penalty1.setLapNum((short)3);
        penalty1.setPlacesGained((short)2);
        penalties.add(penalty1);

        Penalty penalty2 = new Penalty();
        penalty2.setPenaltyType(PenaltyType.TIME_PENALTY);
        penalty2.setInfringementType(InfringementType.IGNORING_DRIVE_THROUGH);
        penalty2.setCarIndex((short)0);
        penalty2.setOtherCarIndex((short)2);
        penalty2.setTime((short)10947);
        penalty2.setLapNum((short)2);
        penalty2.setPlacesGained((short)0);
        penalties.add(penalty2);

        //Session History Data
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO();
        sessionHistoryDTO.setNumLaps((short) 16);
        sessionHistoryDTO.setNumTyreStints((short)2);
        sessionHistoryDTO.setBestLapTimeLapNum((short)3);
        sessionHistoryDTO.setBestSector1LapNum((short)4);
        sessionHistoryDTO.setBestSector2LapNum((short)2);
        sessionHistoryDTO.setBestSector3LapNum((short)5);

        //Lap Data
        List<LapHistoryDTO> lapHistoryDataList = new ArrayList<>();
        LapHistoryDTO lapHistoryDTO = new LapHistoryDTO();
        lapHistoryDTO.setLapTimeInMS(73546);
        lapHistoryDTO.setSector1TimeInMS(22515);
        lapHistoryDTO.setSector2TimeInMS(26515);
        lapHistoryDTO.setSector3TimeInMS(24516);
        lapHistoryDataList.add(lapHistoryDTO);

        //Classification details
        //TODO: Add this..

        //Put everything together
        playerDTO.setCarSetup(carSetupData);
        playerDTO.setPenalties(penalties);
        sessionHistoryDTO.setLapHistory(lapHistoryDataList);
        playerDTO.setSessionHistory(sessionHistoryDTO);
        playerDTOList.add(playerDTO);
        raceSession.setPlayers(playerDTOList);

        return raceSession;
    }

}
