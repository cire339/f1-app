package com.cire.formula1.service;

import com.cire.formula1.model.RaceSession;
import com.cire.formula1.packet.model.*;
import com.cire.formula1.packet.model.constants.PacketId;
import com.cire.formula1.packet.model.data.Penalty;
import com.cire.formula1.packet.model.data.RaceWinner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataProcessingServiceImpl.class);

    private final RaceSessionService raceSessionService;

    private RaceSession raceSession = null;

    @Autowired
    public DataProcessingServiceImpl(RaceSessionService raceSessionService) {
        this.raceSessionService = raceSessionService;
    }

    @Override
    public void processData(Packet packet) {

        BigInteger sessionUid = packet.getHeader().getSessionUid();
        if(!sessionUid.equals(BigInteger.ZERO)){
            raceSession = raceSessionService.getRaceSessionByUid(sessionUid);
        }

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

    private void processSessionHistory(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a session history packet!");
    }

    private void processSession(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a session packet!");
    }

    private void processParticipants(Packet packet) {
        PacketParticipantsData participantsDataPacket = (PacketParticipantsData) packet;
        raceSession.setDrivers(participantsDataPacket.getParticipants());
    }

    private void processMotion(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a motion packet!");
    }

    private void processLobbyInfo(Packet packet) {
        PacketLobbyInfoData lobbyInfoDataPacket = (PacketLobbyInfoData) packet;
        raceSession.setLobby(lobbyInfoDataPacket.getLobbyInfoData());
    }

    private void processLapData(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a lap data packet!");
    }

    private void processFinalClassification(Packet packet) {
        PacketFinalClassificationData finalClassificationDataPacket = (PacketFinalClassificationData) packet;
        raceSession.setFinalClassification(finalClassificationDataPacket.getFinalClassificationData());

        LOGGER.info("Fastest lap: " + raceSession.getFastestLapTime() + " by " + getDriverName(raceSession.getFastestLapCarIndex()));
        LOGGER.info("Highest speed: " + raceSession.getFastestSpeed() + " by " + getDriverName(raceSession.getFastestSpeedCarIndex()));
        LOGGER.info("Race Session Info: " + raceSession.toString());
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
                    raceSession.setFastestLapCarIndex(eventDataPacket.getEventDataDetails().getFastestLap().getCarIndex());
                    raceSession.setFastestLapTime(eventDataPacket.getEventDataDetails().getFastestLap().getLapTime());
                }
                break;
            case RETIREMENT:
                LOGGER.info("Retirement triggered for player: " +
                        getDriverName(eventDataPacket.getEventDataDetails().getRetirement().getCarIndex()));
                break;
            case DRS_ENABLED:
                break;
            case DRS_DISABLED:
                break;
            case TEAM_MATE_IN_PITS:
                break;
            case CHEQUERED_FLAG:
                break;
            case RACE_WINNER:
                RaceWinner raceWinner = eventDataPacket.getEventDataDetails().getRaceWinner();
                LOGGER.info("Race winner is " + getDriverName(raceWinner.getCarIndex()));
                raceSession.setRaceWinner(raceWinner);
                break;
            case PENALTY_ISSUED:
                Penalty penalty = eventDataPacket.getEventDataDetails().getPenalty();
                LOGGER.info("Penalty issued for " + getDriverName(penalty.getCarIndex()) + ": " + penalty.getInfringementType().name() + " " + penalty.getPenaltyType().name());
                raceSession.getPenalties().add(eventDataPacket.getEventDataDetails().getPenalty());
                break;
            case SPEED_TRAP_TRIGGERED:
                //Set highest speed
                if(eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed() > raceSession.getFastestSpeed()){
                    LOGGER.info("New speed trap highest speed " +
                            getDriverName(eventDataPacket.getEventDataDetails().getSpeedTrap().getCarIndex()) +
                            " with a speed of " +
                            eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed());
                    raceSession.setFastestSpeed(eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed());
                    raceSession.setFastestSpeedCarIndex(eventDataPacket.getEventDataDetails().getSpeedTrap().getCarIndex());
                }
                break;
            case START_LIGHTS:
                break;
            case LIGHTS_OUT:
                LOGGER.info("Race " + raceSession.getSessionUid() + " has started!");
                raceSession.setRaceStarted(true);
                break;
            case DRIVE_THROUGH_SERVED:
                break;
            case STOP_GO_SERVED:
                break;
            case FLASHBACK:
                break;
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
        raceSession.setCarSetups(carSetupDataPacket.getCarSetupData());
    }

    private void processCarDamage(Packet packet) {
        //TODO: Data that evolves over time. How to handle this?
        LOGGER.debug("This is a car damage packet!");
    }

    private String getDriverName(short carIndex){
        if(raceSession != null &&
                raceSession.getDrivers() != null &&
                !raceSession.getDrivers().isEmpty() &&
                raceSession.getDrivers().size() >= carIndex) {
            String driverName = raceSession.getDrivers().get(carIndex).getName();
            if(driverName == null || driverName.equalsIgnoreCase("Player")){
                //In multiplayer, the player names are hidden... TODO: Figure out a workaround.
                return driverName + " " + carIndex;
            }
            return raceSession.getDrivers().get(carIndex).getName();
        }else{
            return null;
        }
    }
}
