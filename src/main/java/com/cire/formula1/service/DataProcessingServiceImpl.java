package com.cire.formula1.service;

import com.cire.formula1.config.UdpMessageHandler;
import com.cire.formula1.packets.models.Packet;
import com.cire.formula1.packets.models.PacketEventData;
import com.cire.formula1.packets.models.constants.InfringementType;
import com.cire.formula1.packets.models.constants.PacketId;
import com.cire.formula1.packets.models.constants.PenaltyType;
import com.cire.formula1.packets.models.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.cire.formula1.packets.models.constants.EventCode.*;

public class DataProcessingServiceImpl implements DataProcessingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataProcessingServiceImpl.class);

    @Override
    public void processData(Packet packet) {

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
        LOGGER.info(eventDataPacket.getEventCode().toString());
        switch (eventDataPacket.getEventCode()) {
            //TODO: implement this
            case SESSION_STARTED:
                break;
            case SESSION_ENDED:
                break;
            case FASTEST_LAP:
                LOGGER.info("Fastest lap triggered for car index: " +
                        eventDataPacket.getEventDataDetails().getFastestLap().getVehicleIdx() +
                        " with a lap time of: " +
                        eventDataPacket.getEventDataDetails().getFastestLap().getLapTime());
                break;
            case RETIREMENT:
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
                LOGGER.info("Retirement triggered for car index: " +
                        eventDataPacket.getEventDataDetails().getSpeedTrap().getVehicleIdx() +
                        " with a speed of " +
                        eventDataPacket.getEventDataDetails().getSpeedTrap().getSpeed());
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
}
