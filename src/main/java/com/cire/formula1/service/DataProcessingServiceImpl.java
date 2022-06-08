package com.cire.formula1.service;

import com.cire.formula1.database.FormulaOneDao;
import com.cire.formula1.model.RaceSession;
import com.cire.formula1.model.SessionHistoryData;
import com.cire.formula1.packet.model.*;
import com.cire.formula1.packet.model.constants.PacketId;
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

    private RaceSession raceSession = null;

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

    }

    @Override
    public void processSecondaryData(Packet packet, String playerName) {
        processHeader(packet, playerName);
    }

    private void processHeader(Packet packet, String playerName) {
        BigInteger sessionUid = packet.getHeader().getSessionUid();
        short playerCarIndex = packet.getHeader().getPlayerCarIndex();

        if(!sessionUid.equals(BigInteger.ZERO)){
            raceSession = raceSessionService.getRaceSessionByUid(sessionUid);
            raceSession.getPlayers().get(playerCarIndex).setPlayerName(playerName);
        }
    }

    private void processSessionHistory(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        //One final Session History packet is sent at the very end after the Final Classification packet is sent.
        if(raceSession.isRaceEnded()){
            PacketSessionHistoryData data = (PacketSessionHistoryData)packet;
            raceSession.getPlayers().get(data.getCarIdx()).setSessionHistoryData(new SessionHistoryData(data));

            //Save session to DB.
            saveSessionInDatabase();
        }
    }

    private void processSession(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a session packet!");
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
            raceSession.getPlayers().get(i).setClassificationDetails(classificationData);
        }

        if(raceSession.getFastestLap() != null) {
            LOGGER.info("Fastest lap: " + raceSession.getFastestLap().getLapTime() + " by " + getDriverName(raceSession.getFastestLap().getCarIndex()));
        }
        LOGGER.info("Highest speed: " + raceSession.getFastestSpeed() + " by " + getDriverName(raceSession.getFastestSpeedCarIndex()));
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
                //Add penalty to the player.
                raceSession.getPlayers().get(penalty.getCarIndex()).getPenalties().add(penalty);
                break;
            case SPEED_TRAP_TRIGGERED:
                //Set highest speed
                if(eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed() > raceSession.getFastestSpeed()){
                    LOGGER.info("New speed trap highest speed by " +
                            getDriverName(eventDataPacket.getEventDataDetails().getSpeedTrap().getCarIndex()) +
                            " with a speed of " +
                            eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed());
                    raceSession.setFastestSpeed(eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed());
                    raceSession.setFastestSpeedCarIndex(eventDataPacket.getEventDataDetails().getSpeedTrap().getCarIndex());
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
        //Check DB first?
        if(formulaOneDao.getRaceSessionByUid(raceSession.getSessionUid()).isEmpty()) {
            LOGGER.info("Creating session " + raceSession.getSessionUid() + " in database..");
            formulaOneDao.createRaceSession(raceSession);
            LOGGER.info("Session created successfully!");
        }
    }
}
