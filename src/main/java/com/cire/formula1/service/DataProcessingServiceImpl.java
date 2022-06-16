package com.cire.formula1.service;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.model.dto.FinalClassificationDTO;
import com.cire.formula1.model.dto.PenaltyDTO;
import com.cire.formula1.model.dto.RaceSessionDTO;
import com.cire.formula1.model.dto.SessionHistoryDTO;
import com.cire.formula1.packet.model.*;
import com.cire.formula1.packet.model.constants.PacketId;
import com.cire.formula1.packet.model.constants.SessionType;
import com.cire.formula1.packet.model.data.*;
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
    public void processPrimaryData(Packet packet, String playerName) {

        processHeader(packet, playerName);
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

        //Update session in memory.
        if(raceSession != null) {
            raceSession = raceSessionService.updateRaceSession(raceSession);
        }
    }

    /**
        Secondary player data. This could include player name, car damage, etc..
        Anything the main player can't see.
     */
    @Override
    public void processSecondaryData(Packet packet, String playerName) {
        processHeader(packet, playerName);

        //Update session in memory.
        if(raceSession != null) {
            raceSessionService.updateRaceSession(raceSession);
        }
    }

    private void processHeader(Packet packet, String playerName) {
        BigInteger sessionUid = packet.getHeader().getSessionUid();
        short playerCarIndex = packet.getHeader().getPlayerCarIndex();

        if(!sessionUid.equals(BigInteger.ZERO)){
            raceSession = raceSessionService.getRaceSessionByUid(sessionUid);
            raceSession.getPlayers().get(playerCarIndex).setPlayerName(playerName);
        }else{
            LOGGER.debug("Session UID is empty.. um..");
        }
    }

    private void processSessionHistory(Packet packet) {
        //One final Session History packet is sent at the very end after the Final Classification packet is sent.
        //But it does not seem to be the case.. why? It's a bug - confirmed on CodeMasters forums. We may need to use LapData to calculate this stuff instead.
        PacketSessionHistoryData data = (PacketSessionHistoryData)packet;
        SessionHistoryDTO sessionHistory = raceSession.getPlayers().get(data.getCarIdx()).getSessionHistory();
        if(sessionHistory != null){
            sessionHistory.updateSessionHistory(new SessionHistoryDTO(data));
        }else {
            raceSession.getPlayers().get(data.getCarIdx()).setSessionHistory(new SessionHistoryDTO(data));
        }
        //Only update the DB if the race has ended.
        if(raceSession.isRaceEnded()){
            updateSessionInDatabase();
        }
    }

    private void processSession(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        //For now, I will only save the data that does not change.
        PacketSessionData sessionDataPacket = (PacketSessionData) packet;

        //Only save to DB the races.
        if(sessionDataPacket.getSessionType().equals(SessionType.R)){
            raceSession.setSaveToDatabase(true);
        }
        raceSession.setSessionType(sessionDataPacket.getSessionType().name());
        raceSession.setTrack(sessionDataPacket.getTrackId().name());
        raceSession.setTotalLaps(sessionDataPacket.getTotalLaps());
    }

    private void processParticipants(Packet packet) {
        //TODO: Number changes if a player quits (retires). How should I handle this data?
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a participant data packet!");
        //PacketParticipantsData participantsDataPacket = (PacketParticipantsData) packet;
    }

    private void processMotion(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a motion packet!");
    }

    private void processLobbyInfo(Packet packet) {
        //This is only sent while in the lobby - not session ID yet..
        LOGGER.debug("Players are in the lobby.. Waiting for session to start.");
    }

    private void processLapData(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a lap data packet!");
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
        updateSessionInDatabase();
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
                //Set player fastest speed
                if(speedCaptured > raceSession.getPlayers().get(carIndex).getFastestSpeed()){
                    raceSession.getPlayers().get(carIndex).setFastestSpeed(speedCaptured);
                }
                break;
            case LIGHTS_OUT:
                LOGGER.info("Race " + raceSession.getSessionUid() + " has started!");
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
            case CHEQUERED_FLAG:
            case START_LIGHTS:
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
        PacketCarSetupData carSetupDataPacket = (PacketCarSetupData) packet;
        //Set car setup for each player.
        for(int i = 0; i<raceSession.getPlayers().size(); i++){
            CarSetupData carSetupData = carSetupDataPacket.getCarSetupData().get(i);
            if(!carSetupData.isBlank()) {
                raceSession.getPlayers().get(i).setCarSetup(carSetupData);
            }
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
            return "Player " + carIndex;
        }
        return name;
    }

    private synchronized void saveSessionInDatabase(){
        if(raceSession.isSaveToDatabase()) {
            if (formulaOneDao.getRaceSessionByUid(raceSession.getSessionUid()).isEmpty()) {
                LOGGER.info("Creating session " + raceSession.getSessionUid() + " in database..");
                raceSession = new RaceSessionDTO(formulaOneDao.createRaceSession(raceSession));
                raceSessionService.updateRaceSession(raceSession);
                LOGGER.info("Session created successfully!");
            }
        }
    }

    private synchronized void updateSessionInDatabase(){
        if(raceSession.isSaveToDatabase()) {
            if (formulaOneDao.getRaceSessionByUid(raceSession.getSessionUid()).isPresent()) {
                LOGGER.info("Updating session " + raceSession.getSessionUid() + " in database..");
                raceSession = new RaceSessionDTO(formulaOneDao.updateRaceSession(raceSession));
                raceSessionService.updateRaceSession(raceSession);
                LOGGER.info("Session updated successfully!");
            }
        }
    }
}
