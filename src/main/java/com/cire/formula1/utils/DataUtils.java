package com.cire.formula1.utils;

import com.cire.formula1.model.dto.*;
import com.cire.formula1.packet.model.constants.InfringementType;
import com.cire.formula1.packet.model.constants.PenaltyType;
import com.cire.formula1.packet.model.constants.ResultStatus;
import com.cire.formula1.packet.model.data.CarSetupData;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        PlayerDTO player0 = createPlayerDTO(0);
        PlayerDTO player1 = createPlayerDTO(1);
        playerDTOList.add(player0);
        playerDTOList.add(player1);
        raceSession.setPlayers(playerDTOList);

        return raceSession;
    }

    private static PlayerDTO createPlayerDTO(int playerId){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(playerId);
        playerDTO.setCarIndex(playerId);
        playerDTO.setPlayerName("Test Player " + playerId);


        //Session History Data
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO();
        sessionHistoryDTO.setNumLaps((short) 16);
        sessionHistoryDTO.setNumTyreStints((short)2);
        sessionHistoryDTO.setBestLapTimeLapNum((short)3);
        sessionHistoryDTO.setBestSector1LapNum((short)4);
        sessionHistoryDTO.setBestSector2LapNum((short)2);
        sessionHistoryDTO.setBestSector3LapNum((short)5);

        //Lap Data
        Set<LapHistoryDTO> lapHistoryDataList = new HashSet<>();
        LapHistoryDTO lapHistoryDTO = new LapHistoryDTO();
        lapHistoryDTO.setLapTimeInMS(73546);
        lapHistoryDTO.setSector1TimeInMS(22515);
        lapHistoryDTO.setSector2TimeInMS(26515);
        lapHistoryDTO.setSector3TimeInMS(24516);
        lapHistoryDataList.add(lapHistoryDTO);

        //Classification details
        FinalClassificationDTO finalClassificationDTO = new FinalClassificationDTO();
        finalClassificationDTO.setFinalPosition((short) 1);
        finalClassificationDTO.setGridPosition((short) 5);
        finalClassificationDTO.setBestLapTime(73546);
        finalClassificationDTO.setNumberOfLaps((short) 3);
        finalClassificationDTO.setNumberOfPenalties((short) 2);
        finalClassificationDTO.setPoints((short) 25);
        finalClassificationDTO.setNumberOfPitStops((short) 2);
        finalClassificationDTO.setPenaltiesTime((short) 5);
        finalClassificationDTO.setNumberOfTyreStints((short) 2);
        finalClassificationDTO.setResultStatus(ResultStatus.FINISHED.name());

        //Put everything together
        playerDTO.setCarSetup(createCarSetupData());
        playerDTO.setPenalties(createPenalties(playerId));
        playerDTO.setInvolvedPenalties(createInvolvedPenalties(playerId));
        sessionHistoryDTO.setLapHistory(lapHistoryDataList);
        playerDTO.setSessionHistory(sessionHistoryDTO);
        playerDTO.setFinalClassification(finalClassificationDTO);

        return playerDTO;
    }

    private static CarSetupData createCarSetupData(){
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

        return carSetupData;
    }

    private static Set<PenaltyDTO> createPenalties(int playerId){
        Set<PenaltyDTO> penalties = new HashSet<>();

        PenaltyDTO penalty1 = new PenaltyDTO();
        penalty1.setPenaltyType(PenaltyType.WARNING);
        penalty1.setInfringementType(InfringementType.CORNER_CUTTING_GAINED_TIME);
        penalty1.setPlayerId(playerId);
        penalty1.setCarIndex(playerId);
        penalty1.setOtherCarIndex(playerId+1);
        penalty1.setPenaltyTime((short)28466);
        penalty1.setLapNumber((short)3);
        penalty1.setPlacesGained((short)2);
        penalties.add(penalty1);

        PenaltyDTO penalty2 = new PenaltyDTO();
        penalty2.setPenaltyType(PenaltyType.TIME_PENALTY);
        penalty2.setInfringementType(InfringementType.IGNORING_DRIVE_THROUGH);
        penalty2.setPlayerId(playerId);
        penalty2.setCarIndex(playerId);
        penalty2.setOtherCarIndex((short)255);
        penalty2.setPenaltyTime((short)10947);
        penalty2.setLapNumber((short)2);
        penalty2.setPlacesGained((short)0);
        penalties.add(penalty2);

        return penalties;
    }

    private static Set<PenaltyDTO> createInvolvedPenalties(int playerId){
        Set<PenaltyDTO> penalties = new HashSet<>();

        PenaltyDTO penalty1 = new PenaltyDTO();
        penalty1.setPenaltyType(PenaltyType.WARNING);
        penalty1.setInfringementType(InfringementType.CORNER_CUTTING_GAINED_TIME);
        penalty1.setPlayerId(-1);
        penalty1.setCarIndex(-1);
        penalty1.setOtherCarIndex(playerId);
        penalty1.setPenaltyTime((short)28466);
        penalty1.setLapNumber((short)3);
        penalty1.setPlacesGained((short)2);
        penalties.add(penalty1);

        PenaltyDTO penalty2 = new PenaltyDTO();
        penalty2.setPenaltyType(PenaltyType.TIME_PENALTY);
        penalty2.setInfringementType(InfringementType.IGNORING_DRIVE_THROUGH);
        penalty2.setPlayerId(-1);
        penalty2.setCarIndex(-1);
        penalty2.setOtherCarIndex(playerId);
        penalty2.setPenaltyTime((short)10947);
        penalty2.setLapNumber((short)2);
        penalty2.setPlacesGained((short)0);
        penalties.add(penalty2);

        return penalties;
    }

}
