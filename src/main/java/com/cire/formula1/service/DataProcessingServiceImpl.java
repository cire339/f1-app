package com.cire.formula1.service;

import com.cire.formula1.model.RaceSession;
import com.cire.formula1.packet.model.Packet;
import com.cire.formula1.packet.model.PacketEventData;
import com.cire.formula1.packet.model.PacketParticipantsData;
import com.cire.formula1.packet.model.constants.PacketId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataProcessingServiceImpl.class);

    private RaceSessionService raceSessionService;

    private RaceSession raceSession = null;

    @Autowired
    public DataProcessingServiceImpl(RaceSessionService raceSessionService) {
        this.raceSessionService = raceSessionService;
    }

    @Override
    public void processData(Packet packet) {

        BigInteger sessionUid = packet.getHeader().getSessionUid();
        if(sessionUid != BigInteger.ZERO){
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
        LOGGER.debug("This is a session history packet!");
    }

    private void processSession(Packet packet) {
        LOGGER.debug("This is a session packet!");
    }

    private void processParticipants(Packet packet) {
        PacketParticipantsData participantsDataPacket = (PacketParticipantsData) packet;
        raceSession.setDrivers(participantsDataPacket.getParticipants());
        LOGGER.debug("This is a participant packet!");
    }

    private void processMotion(Packet packet) {
        LOGGER.debug("This is a motion packet!");
    }

    private void processLobbyInfo(Packet packet) {
        LOGGER.debug("This is a lobby info packet!");
    }

    private void processLapData(Packet packet) {
        LOGGER.debug("This is a lap data packet!");
    }

    private void processFinalClassification(Packet packet) {
        LOGGER.debug("This is a final classification packet!");
    }

    private void processEvent(Packet packet) {
        LOGGER.debug("This is an event packet!");
        PacketEventData eventDataPacket = (PacketEventData) packet;

        //LOGGER.info(eventDataPacket.getEventCode().toString());
        switch (eventDataPacket.getEventCode()) {
            //TODO: implement this
            case SESSION_STARTED:
                //TODO: Is this where the session should be created?
                //Create new race session when race starts.
                LOGGER.info("Session Started.");
                raceSessionService.createRaceSession(packet.getHeader().getSessionUid());
                break;
            case SESSION_ENDED:
                LOGGER.info("Session Ended.");
                LOGGER.info("Fastest lap: " + raceSession.getFastestLapTime() + " by " + getDriverName(raceSession.getFastestLapCarIndex()));
                LOGGER.info("Highest speed: " + raceSession.getFastestSpeed() + " by " + raceSession.getDrivers().get(raceSession.getFastestSpeedCarIndex()));
                break;
            case FASTEST_LAP:
                LOGGER.info("Fastest lap triggered for player: " +
                        getDriverName(eventDataPacket.getEventDataDetails().getFastestLap().getVehicleIdx()) +
                        " with a lap time of: " +
                        eventDataPacket.getEventDataDetails().getFastestLap().getLapTime());
                if(raceSession != null){
                    raceSession.setFastestLapCarIndex(eventDataPacket.getEventDataDetails().getFastestLap().getVehicleIdx());
                    raceSession.setFastestLapTime(eventDataPacket.getEventDataDetails().getFastestLap().getLapTime());
                }
                break;
            case RETIREMENT:
                LOGGER.info("Retirement triggered for player: " +
                        getDriverName(eventDataPacket.getEventDataDetails().getRetirement().getVehicleIdx()));
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
                break;
            case PENALTY_ISSUED:
                break;
            case SPEED_TRAP_TRIGGERED:
                LOGGER.info("Speed trap triggered for player : " +
                        getDriverName(eventDataPacket.getEventDataDetails().getSpeedTrap().getVehicleIdx()) +
                        " with a speed of " +
                        eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed());
                if(eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed() > raceSession.getFastestSpeed()){
                    raceSession.setFastestSpeed(eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed());
                    raceSession.setFastestSpeedCarIndex(eventDataPacket.getEventDataDetails().getSpeedTrap().getVehicleIdx());
                }
                break;
            case START_LIGHTS:
                break;
            case LIGHTS_OUT:
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
        LOGGER.debug("This is a car telemetry packet!");
    }

    private void processCarStatus(Packet packet) {
        LOGGER.debug("This is a car status packet!");
    }

    private void processCarSetups(Packet packet) {
        LOGGER.debug("This is a car setup packet!");
    }

    private void processCarDamage(Packet packet) {
        LOGGER.debug("This is a car damage packet!");
    }

    private String getDriverName(short carIndex){
        if(raceSession != null &&
                raceSession.getDrivers() != null &&
                !raceSession.getDrivers().isEmpty() &&
                raceSession.getDrivers().size() >= carIndex) {
            return raceSession.getDrivers().get(carIndex).getName();
        }else{
            return null;
        }
    }
}
