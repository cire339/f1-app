package com.cire.formula1.service;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.model.dto.*;
import com.cire.formula1.packet.model.*;
import com.cire.formula1.packet.model.constants.PacketId;
import com.cire.formula1.packet.model.constants.SessionType;
import com.cire.formula1.packet.model.data.*;
import com.cire.formula1.packet.util.PacketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataProcessingServiceImpl.class);

    private final RaceSessionService raceSessionService;
    private final FormulaOneDao formulaOneDao;

    private RaceSessionDTO raceSession = null;

    @Autowired
    public DataProcessingServiceImpl(RaceSessionService raceSessionService, FormulaOneDao formulaOneDao) {
        this.raceSessionService = raceSessionService;
        this.formulaOneDao = formulaOneDao;
    }

    @Override
    public void processData(Packet packet) {
        //Process header data
        processHeader(packet);
        PacketId packetId = packet.getHeader().getPacketId();

        switch (packetId) {
            case CAR_DAMAGE -> processCarDamage(packet);
            case CAR_SETUPS -> processCarSetups(packet);
            case CAR_STATUS -> processCarStatus(packet);
            case CAR_TELEMETRY -> processCarTelemetry(packet);
            case EVENT -> processEvent(packet);
            case FINAL_CLASSIFICATION -> processFinalClassification(packet);
            case LAP_DATA -> processLapData(packet);
            case LOBBY_INFO -> processLobbyInfo(packet);
            case MOTION -> processMotion(packet);
            case PARTICIPANTS -> processParticipants(packet);
            case SESSION -> processSession(packet);
            case SESSION_HISTORY -> processSessionHistory(packet);
            default -> throw new IllegalArgumentException("PacketId=" + packetId + " unrecognized");
        }

        if(raceSession != null) {
            //Update session in memory.
            raceSession = raceSessionService.updateRaceSession(raceSession);
        }
    }

    private void processHeader(Packet packet) {
        BigInteger sessionUid = packet.getHeader().getSessionUid();
        short playerCarIndex = packet.getHeader().getPlayerCarIndex();

        if(!sessionUid.equals(BigInteger.ZERO)){
            raceSession = raceSessionService.getRaceSessionByUid(sessionUid);
            //TODO: Name handling in F1 2022
            raceSession.getPlayers().get(playerCarIndex).setPlayerName("cirelol");
        }else{
            LOGGER.debug("Session UID is empty.. um..");
        }
    }

    private void processSessionHistory(Packet packet) {
        //One final Session History packet is sent at the very end after the Final Classification packet is sent.
        //But it does not seem to be the case, why? It's a bug - confirmed on CodeMasters forums. We may need to use LapData to calculate this stuff instead.
        PacketSessionHistoryData data = (PacketSessionHistoryData)packet;
        SessionHistoryDTO sessionHistory = raceSession.getPlayers().get(data.getCarIdx()).getSessionHistory();
        if (sessionHistory != null) {
            sessionHistory.updateSessionHistory(new SessionHistoryDTO(data));
        } else {
            raceSession.getPlayers().get(data.getCarIdx()).setSessionHistory(new SessionHistoryDTO(data));
        }
        //Only update the DB if the race has ended (does not seem to work)
        if(raceSession.isRaceEnded()){
            LOGGER.info("Final session history packet. Saving to DB");
            saveSessionInDatabase();
        }
    }

    private void processSession(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        //For now, I will only save the data that does not change.
        PacketSessionData sessionDataPacket = (PacketSessionData) packet;

        raceSession.setSaveToDatabase(true);
        //Only save to DB the races.
        //if(sessionDataPacket.getSessionType().equals(SessionType.R)){
        //    raceSession.setSaveToDatabase(true);
        //}
        raceSession.setSessionType(sessionDataPacket.getSessionType().name());
        raceSession.setTrack(sessionDataPacket.getTrackId().name());
        raceSession.setTotalLaps(sessionDataPacket.getTotalLaps());
    }

    private void processParticipants(Packet packet) {
        //TODO: Number changes if a player quits (retires). How should I handle this data?
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a participant data packet!");
        PacketParticipantsData data = (PacketParticipantsData) packet;
        raceSession.setNumberActiveCars(data.getNumActiveCars());
        for(short i=0; i<data.getNumActiveCars(); i++) {
            //Only set name of non primary player.
            //TODO: This will change in 2022
            if(packet.getHeader().getPlayerCarIndex() != i) {
                String playerName = data.getParticipants().get(i).getName();
                if(playerName.equalsIgnoreCase("Player")){
                    raceSession.getPlayers().get(i).setPlayerName(playerName + packet.getHeader().getPlayerCarIndex());
                }else {
                    raceSession.getPlayers().get(i).setPlayerName(playerName);
                }
            }
        }
        LOGGER.debug("Participant packet. Saving to DB");
        saveSessionInDatabase();
    }

    private void processMotion(Packet packet) {
        PacketMotionData data = (PacketMotionData) packet;
        for(short i = 0; i< PacketConstants.CARS; i++){
            if(raceSession.getPlayers().get(i).getCurrentLapNumber() == 1){
                raceSession.getPlayers().get(i).updateMotionDataSet(data.getCarMotionData(), packet.getHeader().getPlayerCarIndex(), raceSession.getPlayers().get(i).getCurrentLapNumber());
            }
        }
    }

    private void processLobbyInfo(Packet packet) {
        //This is only sent while in the lobby - not session ID yet..
        LOGGER.debug("Players are in the lobby.. Waiting for session to start.");
    }

    private void processLapData(Packet packet) {
        short carIndex = packet.getHeader().getPlayerCarIndex();
        PacketLapData data = (PacketLapData) packet;

        //Set current lap number.
        short currentLapNumber = data.getLapData().get(carIndex).getCurrentLapNum();
        raceSession.getPlayers().get(carIndex).setCurrentLapNumber(currentLapNumber);

        raceSession.updatePlayerPositionDataSet(data.getLapData());
    }

    private void processFinalClassification(Packet packet) {
        PacketFinalClassificationData finalClassificationDataPacket = (PacketFinalClassificationData) packet;

        //Set final classification for each player.
        for(int i = 0; i<finalClassificationDataPacket.getNumCars(); i++){
            FinalClassificationData classificationData = finalClassificationDataPacket.getFinalClassificationData().get(i);
            raceSession.getPlayers().get(i).setFinalClassification(new FinalClassificationDTO(classificationData));
        }

        if(raceSession.getFastestLap() != null) {
            LOGGER.info("Fastest lap: " + raceSession.getFastestLap().getLapTime() + " by " + getDriverName(raceSession.getFastestLap().getCarIndex()));
        }
        LOGGER.info("Highest speed: " + raceSession.getFastestSpeed() + " by " + getDriverName(raceSession.getFastestSpeedCarIndex()));

        //Update session in DB.
        LOGGER.info("Final Classification packet. Saving to DB.");
        saveSessionInDatabase();
    }

    private void processEvent(Packet packet) {
        LOGGER.debug("This is an event packet!");
        PacketEventData eventDataPacket = (PacketEventData) packet;

        switch (eventDataPacket.getEventCode()) {
            case SESSION_STARTED:
                LOGGER.info("Session Started.");
                break;
            case SESSION_ENDED:
                LOGGER.info("Session Ended.");
                raceSession.setRaceEnded(true);
                saveSessionInDatabase();
                break;
            case FASTEST_LAP:
                LOGGER.info("New Fastest lap by " +
                        getDriverName(eventDataPacket.getEventDataDetails().getFastestLap().getCarIndex()) +
                        " with a lap time of: " +
                        eventDataPacket.getEventDataDetails().getFastestLap().getLapTime());
                if(raceSession != null){
                    raceSession.setFastestLap(eventDataPacket.getEventDataDetails().getFastestLap());
                }
                break;
            case RETIREMENT:
                LOGGER.info("Retirement triggered for " +
                        getDriverName(eventDataPacket.getEventDataDetails().getRetirement().getCarIndex()));
                break;
            case DRS_ENABLED:
                LOGGER.info("DRS is now enabled.");
                break;
            case DRS_DISABLED:
                LOGGER.info("DRS is now disabled.");
                break;
            case RACE_WINNER:
                RaceWinner raceWinner = eventDataPacket.getEventDataDetails().getRaceWinner();
                LOGGER.info("Race winner is " + getDriverName(raceWinner.getCarIndex()));
                raceSession.setRaceWinnerCarIndex(raceWinner.getCarIndex());
                break;
            case PENALTY_ISSUED:
                Penalty penalty = eventDataPacket.getEventDataDetails().getPenalty();
                LOGGER.info("Penalty issued for " + getDriverName(penalty.getCarIndex()) + ": " + penalty.getInfringementType().name() + " " + penalty.getPenaltyType().name());
                //Add penalty to the player(s).
                raceSession.getPlayers().get(penalty.getCarIndex()).getPenalties().add(new PenaltyDTO(penalty));
                //TODO: Check this to make sure it's right..
                if(penalty.getOtherCarIndex() != 255){
                    raceSession.getPlayers().get(penalty.getOtherCarIndex()).getInvolvedPenalties().add(new PenaltyDTO(penalty));
                }
                break;
            case SPEED_TRAP_TRIGGERED:
                //Set overall highest speed
                Float speedCaptured = eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed();
                short carIndex = eventDataPacket.getEventDataDetails().getSpeedTrap().getCarIndex();
                if(speedCaptured > raceSession.getFastestSpeed()){
                    LOGGER.info("New speed trap highest speed by " +
                            getDriverName(carIndex) +
                            " with a speed of " +
                            speedCaptured);
                    raceSession.setFastestSpeed(speedCaptured);
                    raceSession.setFastestSpeedCarIndex(carIndex);
                }
                //Set each player fastest speed
                if(speedCaptured > raceSession.getPlayers().get(carIndex).getFastestSpeed()){
                    raceSession.getPlayers().get(carIndex).setFastestSpeed(speedCaptured);
                }
                break;
            case LIGHTS_OUT:
                LOGGER.info("Start lights are out and the race " + raceSession.getSessionUid() + " on " + raceSession.getTrack() + " has started!");
                raceSession.setRaceStarted(true);
                break;
            case DRIVE_THROUGH_SERVED:
                DriveThroughPenaltyServed dtPenServed = eventDataPacket.getEventDataDetails().getDriveThroughPenaltyServed();
                LOGGER.info(getDriverName(dtPenServed.getCarIndex()) + " has served his drive through penalty.");
                break;
            case STOP_GO_SERVED:
                StopGoPenaltyServed sgPenServed = eventDataPacket.getEventDataDetails().getStopGoPenaltyServed();
                LOGGER.info(getDriverName(sgPenServed.getCarIndex()) + " has served his stop go penalty.");
                break;
            case TEAM_MATE_IN_PITS:
                TeamMateInPits tmip = eventDataPacket.getEventDataDetails().getTeamMateInPits();
                LOGGER.info(getDriverName(packet.getHeader().getPlayerCarIndex()) + "'s teammate, " + getDriverName(tmip.getCarIndex()) + ", is in the pits!");
            case CHEQUERED_FLAG:
                LOGGER.info("Checkered flag has been waived!");
            case START_LIGHTS:
                LOGGER.info("Start lights are on!");
            case FLASHBACK:
            case BUTTON_STATUS:
                break;
            default:
                throw new IllegalArgumentException("EventCode=" + eventDataPacket.getEventCode() + " not supported");
        }
    }

    private void processCarTelemetry(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a car telemetry packet!");
    }

    private void processCarStatus(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a car status packet!");
    }

    private void processCarSetups(Packet packet) {
        //Only AI and current player car setup is visible.
        PacketCarSetupData carSetupDataPacket = (PacketCarSetupData) packet;
        short playerIndex = packet.getHeader().getPlayerCarIndex();
        CarSetupData carSetupData = carSetupDataPacket.getCarSetupData().get(playerIndex);
        if(raceSession.getPlayers().get(playerIndex).getCarSetup() != null) {
            raceSession.getPlayers().get(playerIndex).getCarSetup().updateCarSetup(new CarSetupDTO(carSetupData));
        }else{
            raceSession.getPlayers().get(playerIndex).setCarSetup(new CarSetupDTO(carSetupData));
        }
    }

    private void processCarDamage(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a car damage packet!");
    }

    private String getDriverName(short carIndex){
        String name = raceSession.getPlayers().get(carIndex).getPlayerName();
        //Default if the player has no name.
        if(name == null || name.isEmpty()){
            return "Player (" + carIndex + ")";
        }
        return name + " (" + carIndex + ")";
    }

    private synchronized void saveSessionInDatabase(){
        if(raceSession.isSaveToDatabase()) {
            if (formulaOneDao.getRaceSessionByUid(raceSession.getSessionUid()).isPresent()) {
                LOGGER.debug("Updating session " + raceSession.getSessionUid() + " in database..");
                raceSession = new RaceSessionDTO(formulaOneDao.updateRaceSession(raceSession));
                raceSessionService.updateRaceSession(raceSession);
                LOGGER.debug("Session updated successfully!");
            }else{
                LOGGER.debug("Creating session " + raceSession.getSessionUid() + " in database..");
                raceSession = new RaceSessionDTO(formulaOneDao.createRaceSession(raceSession));
                raceSessionService.updateRaceSession(raceSession);
                LOGGER.debug("Session created successfully!");
            }
            LOGGER.debug("Session saved successfully!");
        }
    }
}
