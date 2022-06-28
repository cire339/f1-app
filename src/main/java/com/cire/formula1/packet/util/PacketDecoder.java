package com.cire.formula1.packet.util;

import com.cire.formula1.packet.model.*;
import com.cire.formula1.packet.model.constants.*;
import com.cire.formula1.packet.model.data.*;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Packet decoder
 */
public class PacketDecoder {

    private final static Logger LOGGER = LoggerFactory.getLogger(PacketDecoder.class);

    private ByteBuf packetBuffer;

    /**
     * Decode the buffer into a new Packet.
     * 
     * @param buffer buffer with the raw bytes
     * @return decoded Packet
     */
    public Packet decode(ByteBuf buffer) {
        packetBuffer = buffer;
        PacketHeader header = generatePacketHeader();
        PacketId packetId = header.getPacketId();
        return switch (packetId) {
            case CAR_DAMAGE -> generateCarDamagePacket(header);
            case CAR_SETUPS -> generateCarSetupPacket(header);
            case CAR_STATUS -> generateCarStatusPacket(header);
            case CAR_TELEMETRY -> generateCarTelemetryPacket(header);
            case EVENT -> generateEventPacket(header);
            case FINAL_CLASSIFICATION -> generateFinalClassificationPacket(header);
            case LAP_DATA -> generateLapDataPacket(header);
            case LOBBY_INFO -> generateLobbyInfoPacket(header);
            case MOTION -> generateMotionPacket(header);
            case PARTICIPANTS -> generateParticipantsPacket(header);
            case SESSION -> generateSessionPacket(header);
            case SESSION_HISTORY -> generateSessionHistoryPacket(header);
            default -> throw new IllegalArgumentException("PacketId=" + packetId + " unrecognized");
        };
    }

    private Packet generateSessionHistoryPacket(PacketHeader header) {
        PacketSessionHistoryData packet = new PacketSessionHistoryData();
        List<LapHistoryData> lapHistoryDataList = new ArrayList<>(PacketConstants.LAPS);
        List<TyreStintHistoryData> tyreStintHistoryDataList = new ArrayList<>(PacketConstants.TYRE_STINTS);
        packet.setHeader(header);
        packet.setCarIdx(packetBuffer.readUnsignedByte());
        packet.setNumLaps(packetBuffer.readUnsignedByte());
        packet.setNumTyreStints(packetBuffer.readUnsignedByte());
        packet.setBestLapTimeLapNum(packetBuffer.readUnsignedByte());
        packet.setBestSector1LapNum(packetBuffer.readUnsignedByte());
        packet.setBestSector2LapNum(packetBuffer.readUnsignedByte());
        packet.setBestSector3LapNum(packetBuffer.readUnsignedByte());
        for (int i = 0; i < PacketConstants.LAPS; i++) {
            LapHistoryData lhd = new LapHistoryData();
            lhd.setLapTimeInMS(packetBuffer.readUnsignedIntLE());
            lhd.setSector1TimeInMS(packetBuffer.readUnsignedShortLE());
            lhd.setSector2TimeInMS(packetBuffer.readUnsignedShortLE());
            lhd.setSector3TimeInMS(packetBuffer.readUnsignedShortLE());
            lhd.setLapValidBitFlags(packetBuffer.readUnsignedByte());
            lapHistoryDataList.add(lhd);
        }
        packet.setLapHistoryData(lapHistoryDataList);
        for (int i = 0; i < PacketConstants.TYRE_STINTS; i++) {
            TyreStintHistoryData tshd = new TyreStintHistoryData();
            tshd.setEndLap(packetBuffer.readUnsignedByte());
            tshd.setTyreActualCompound(packetBuffer.readUnsignedByte());
            tshd.setTyreVisualCompound(packetBuffer.readUnsignedByte());
            tyreStintHistoryDataList.add(tshd);
        }
        packet.setTyreStintHistoryData(tyreStintHistoryDataList);
        return packet;
    }

    private Packet generateSessionPacket(PacketHeader header) {
        PacketSessionData packet = new PacketSessionData();
        List<MarshalZone> marshalZoneList = new ArrayList<>(PacketConstants.MARSHAL_ZONES);
        List<WeatherForecastSample> weatherForecastSampleList = new ArrayList<>(PacketConstants.WEATHER_FORECAST_SAMPLES);
        packet.setHeader(header);
        packet.setWeather(Weather.valueOf(packetBuffer.readUnsignedByte()));
        packet.setTrackTemperature(packetBuffer.readByte());
        packet.setAirTemperature(packetBuffer.readByte());
        packet.setTotalLaps(packetBuffer.readUnsignedByte());
        packet.setTrackLength(packetBuffer.readUnsignedShortLE());
        packet.setSessionType(SessionType.valueOf(packetBuffer.readUnsignedByte()));
        packet.setTrackId(Track.valueOf(packetBuffer.readByte()));
        packet.setFormula(Formula.valueOf(packetBuffer.readUnsignedByte()));
        packet.setSessionTimeLeft(packetBuffer.readUnsignedShortLE());
        packet.setSessionDuration(packetBuffer.readUnsignedShortLE());
        packet.setPitSpeedLimit(packetBuffer.readUnsignedByte());
        packet.setGamePaused(packetBuffer.readUnsignedByte());
        packet.setIsSpectating(packetBuffer.readUnsignedByte());
        packet.setSpectatorCarIndex(packetBuffer.readUnsignedByte());
        packet.setSliProNativeSupport(packetBuffer.readUnsignedByte());
        packet.setNumMarshalZones(packetBuffer.readUnsignedByte());
        for (int i = 0; i < PacketConstants.MARSHAL_ZONES; i++) {
            MarshalZone mz = new MarshalZone();
            mz.setZoneStart(packetBuffer.readFloatLE());
            mz.setZoneFlag(ZoneFlag.valueOf(packetBuffer.readByte()));
            marshalZoneList.add(mz);
        }
        packet.setMarshalZones(marshalZoneList);
        packet.setSafetyCarStatus(SafetyCarStatus.valueOf(packetBuffer.readUnsignedByte()));
        packet.setNetworkGame(packetBuffer.readUnsignedByte());
        packet.setNumWeatherForecastSamples(packetBuffer.readUnsignedByte());
        for (int i = 0; i < PacketConstants.WEATHER_FORECAST_SAMPLES; i++) {
            WeatherForecastSample wfs = new WeatherForecastSample();
            wfs.setSessionType(SessionType.valueOf(packetBuffer.readUnsignedByte()));
            wfs.setTimeOffset(packetBuffer.readUnsignedByte());
            wfs.setWeather(Weather.valueOf(packetBuffer.readUnsignedByte()));
            wfs.setTrackTemperature(packetBuffer.readByte());
            wfs.setTrackTemperatureChange(packetBuffer.readByte());
            wfs.setAirTemperature(packetBuffer.readByte());
            wfs.setAirTemperatureChange(packetBuffer.readByte());
            wfs.setRainPercentage(packetBuffer.readUnsignedByte());
            weatherForecastSampleList.add(wfs);
        }
        packet.setWeatherForecastSamples(weatherForecastSampleList);
        packet.setForecastAccuracy(packetBuffer.readByte());
        packet.setAiDifficulty(packetBuffer.readByte());
        packet.setSeasonLinkIdentifier(packetBuffer.readUnsignedIntLE());
        packet.setWeekendLinkIdentifier(packetBuffer.readUnsignedIntLE());
        packet.setSessionLinkIdentifier(packetBuffer.readUnsignedIntLE());
        packet.setPitStopWindowIdealLap(packetBuffer.readByte());
        packet.setPitStopWindowLatestLap(packetBuffer.readByte());
        packet.setPitStopRejoinPosition(packetBuffer.readByte());
        packet.setSteeringAssist(packetBuffer.readByte());
        packet.setBrakingAssist(packetBuffer.readByte());
        packet.setGearboxAssist(packetBuffer.readByte());
        packet.setPitAssist(packetBuffer.readByte());
        packet.setPitReleaseAssist(packetBuffer.readByte());
        packet.setErsAssist(packetBuffer.readByte());
        packet.setDrsAssist(packetBuffer.readByte());
        packet.setDynamicRacingLine(packetBuffer.readByte());
        packet.setDynamicRacingLineType(packetBuffer.readByte());

        if(is2022Format(header)){
            packet.setGameMode(packetBuffer.readUnsignedByte());
            packet.setRuleSet(packetBuffer.readUnsignedByte());
            packet.setTimeOfDay(packetBuffer.readUnsignedByte());
            packet.setSessionLength(packetBuffer.readUnsignedByte());
        }

        return packet;
    }

    private Packet generateParticipantsPacket(PacketHeader header) {
        PacketParticipantsData packet = new PacketParticipantsData();
        List<ParticipantData> participantDataList = new ArrayList<>(PacketConstants.CARS);
        packet.setHeader(header);
        packet.setNumActiveCars(packetBuffer.readUnsignedByte());
        for (int i = 0; i < packet.getNumActiveCars(); i++) {
            ParticipantData pd = new ParticipantData();
            pd.setAiControlled(packetBuffer.readUnsignedByte());
            pd.setDriverId(Driver.valueOf(packetBuffer.readUnsignedByte()));
            pd.setNetworkId(packetBuffer.readUnsignedByte());
            pd.setTeamId(Team.valueOf(packetBuffer.readUnsignedByte()));
            pd.setMyTeam(packetBuffer.readUnsignedByte());
            pd.setRaceNumber(packetBuffer.readUnsignedByte());
            pd.setNationality(Nationality.valueOf(packetBuffer.readUnsignedByte()));
            pd.setName(PacketUtils.readString(packetBuffer, ParticipantData.NAME_LENGTH));
            pd.setYourTelemetry(packetBuffer.readUnsignedByte());
            participantDataList.add(pd);
        }
        packet.setParticipants(participantDataList);
        return packet;
    }

    private Packet generateMotionPacket(PacketHeader header) {
        PacketMotionData packet = new PacketMotionData();
        List<CarMotionData> carMotionDataList = new ArrayList<>(PacketConstants.CARS);
        ExtraCarMotionData extraCarMotionData = new ExtraCarMotionData();
        packet.setHeader(header);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarMotionData cmd = new CarMotionData();
            cmd.setWorldPositionX(packetBuffer.readFloatLE());
            cmd.setWorldPositionY(packetBuffer.readFloatLE());
            cmd.setWorldPositionZ(packetBuffer.readFloatLE());
            cmd.setWorldVelocityX(packetBuffer.readFloatLE());
            cmd.setWorldVelocityY(packetBuffer.readFloatLE());
            cmd.setWorldVelocityZ(packetBuffer.readFloatLE());
            cmd.setWorldForwardDirX(PacketUtils.normalizedVectorToFloat(packetBuffer.readShortLE()));
            cmd.setWorldForwardDirY(PacketUtils.normalizedVectorToFloat(packetBuffer.readShortLE()));
            cmd.setWorldForwardDirZ(PacketUtils.normalizedVectorToFloat(packetBuffer.readShortLE()));
            cmd.setWorldRightDirX(PacketUtils.normalizedVectorToFloat(packetBuffer.readShortLE()));
            cmd.setWorldRightDirY(PacketUtils.normalizedVectorToFloat(packetBuffer.readShortLE()));
            cmd.setWorldRightDirZ(PacketUtils.normalizedVectorToFloat(packetBuffer.readShortLE()));
            cmd.setgForceLateral(packetBuffer.readFloatLE());
            cmd.setgForceLongitudinal(packetBuffer.readFloatLE());
            cmd.setgForceVertical(packetBuffer.readFloatLE());
            cmd.setYaw(packetBuffer.readFloatLE());
            cmd.setPitch(packetBuffer.readFloatLE());
            cmd.setRoll(packetBuffer.readFloatLE());
            carMotionDataList.add(cmd);
        }
        packet.setCarMotionData(carMotionDataList);

        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            extraCarMotionData.getSuspensionPosition()[i] = packetBuffer.readFloatLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            extraCarMotionData.getSuspensionVelocity()[i] = packetBuffer.readFloatLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            extraCarMotionData.getSuspensionAcceleration()[i] = packetBuffer.readFloatLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            extraCarMotionData.getWheelSpeed()[i] = packetBuffer.readFloatLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            extraCarMotionData.getWheelSlip()[i] = packetBuffer.readFloatLE();
        }
        extraCarMotionData.setLocalVelocityX(packetBuffer.readFloatLE());
        extraCarMotionData.setLocalVelocityY(packetBuffer.readFloatLE());
        extraCarMotionData.setLocalVelocityZ(packetBuffer.readFloatLE());
        extraCarMotionData.setAngularVelocityX(packetBuffer.readFloatLE());
        extraCarMotionData.setAngularVelocityY(packetBuffer.readFloatLE());
        extraCarMotionData.setAngularVelocityZ(packetBuffer.readFloatLE());
        extraCarMotionData.setAngularAccelerationX(packetBuffer.readFloatLE());
        extraCarMotionData.setAngularAccelerationY(packetBuffer.readFloatLE());
        extraCarMotionData.setAngularAccelerationZ(packetBuffer.readFloatLE());
        extraCarMotionData.setFrontWheelsAngle(packetBuffer.readFloatLE());

        return packet;
    }

    private Packet generateLobbyInfoPacket(PacketHeader header) {
        PacketLobbyInfoData packet = new PacketLobbyInfoData();
        List<LobbyInfoData> lobbyInfoDataList = new ArrayList<>(PacketConstants.LOBBY_PLAYERS);
        packet.setHeader(header);
        packet.setNumPlayers(packetBuffer.readUnsignedByte());
        for (int i = 0; i < packet.getNumPlayers(); i++) {
            LobbyInfoData lid = new LobbyInfoData();
            lid.setAiControlled(packetBuffer.readUnsignedByte());
            lid.setTeamId(Team.valueOf(packetBuffer.readUnsignedByte()));
            lid.setNationality(Nationality.valueOf(packetBuffer.readUnsignedByte()));
            lid.setName(PacketUtils.readString(packetBuffer, LobbyInfoData.NAME_LENGTH));
            lid.setCarNumber(packetBuffer.readUnsignedByte());
            lid.setReadyStatus(ReadyStatus.valueOf(packetBuffer.readUnsignedByte()));
            lobbyInfoDataList.add(lid);
        }
        packet.setLobbyInfoData(lobbyInfoDataList);
        return packet;
    }

    private Packet generateLapDataPacket(PacketHeader header) {
        PacketLapData packet = new PacketLapData();
        List<LapData> lapDataList = new ArrayList<>(PacketConstants.CARS);
        packet.setHeader(header);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            LapData ld = new LapData();
            ld.setLastLapTimeInMS(packetBuffer.readUnsignedIntLE());
            ld.setCurrentLapTimeInMS(packetBuffer.readUnsignedIntLE());
            ld.setSector1TimeInMS(packetBuffer.readUnsignedShortLE());
            ld.setSector2TimeInMS(packetBuffer.readUnsignedShortLE());
            ld.setLapDistance(packetBuffer.readFloatLE());
            ld.setTotalDistance(packetBuffer.readFloatLE());
            ld.setSafetyCarDelta(packetBuffer.readFloatLE());
            ld.setCarPosition(packetBuffer.readUnsignedByte());
            ld.setCurrentLapNum(packetBuffer.readUnsignedByte());
            ld.setPitStatus(PitStatus.valueOf(packetBuffer.readUnsignedByte()));
            ld.setNumPitStops(packetBuffer.readUnsignedByte());
            ld.setSector(Sector.valueOf(packetBuffer.readUnsignedByte()));
            ld.setCurrentLapInvalid(packetBuffer.readUnsignedByte());
            ld.setPenalties(packetBuffer.readUnsignedByte());
            ld.setWarnings(packetBuffer.readUnsignedByte());
            ld.setNumUnservedDriveThroughPens(packetBuffer.readUnsignedByte());
            ld.setNumUnservedStopGoPens(packetBuffer.readUnsignedByte());
            ld.setGridPosition(packetBuffer.readUnsignedByte());
            ld.setDriverStatus(DriverStatus.valueOf(packetBuffer.readUnsignedByte()));
            ld.setResultStatus(ResultStatus.valueOf(packetBuffer.readUnsignedByte()));
            ld.setPitLaneTimerActive(packetBuffer.readUnsignedByte());
            ld.setPitLaneTimeInLaneInMS(packetBuffer.readUnsignedShortLE());
            ld.setPitStopTimerInMS(packetBuffer.readUnsignedShortLE());
            ld.setPitStopShouldServePen(packetBuffer.readUnsignedByte());
            lapDataList.add(ld);
        }
        if(is2022Format(header)){
            packet.setTimeTrialPBCarIdx(packetBuffer.readUnsignedByte());
            packet.setTimeTrialRivalCarIdx(packetBuffer.readUnsignedByte());
        }
        packet.setLapData(lapDataList);
        return packet;
    }

    private Packet generateFinalClassificationPacket(PacketHeader header) {
        PacketFinalClassificationData packet = new PacketFinalClassificationData();
        List<FinalClassificationData> finalClassificationDataList = new ArrayList<>(PacketConstants.CARS);
        packet.setHeader(header);
        packet.setNumCars(packetBuffer.readUnsignedByte());
        for (int i = 0; i < packet.getNumCars(); i++) {
            FinalClassificationData fcd = new FinalClassificationData();
            fcd.setPosition(packetBuffer.readUnsignedByte());
            fcd.setNumLaps(packetBuffer.readUnsignedByte());
            fcd.setGridPosition(packetBuffer.readUnsignedByte());
            fcd.setPoints(packetBuffer.readUnsignedByte());
            fcd.setNumPitStops(packetBuffer.readUnsignedByte());
            fcd.setResultStatus(ResultStatus.valueOf(packetBuffer.readUnsignedByte()));
            fcd.setBestLapTimeInMS(packetBuffer.readUnsignedIntLE());
            fcd.setTotalRaceTime(packetBuffer.readDoubleLE());
            fcd.setPenaltiesTime(packetBuffer.readUnsignedByte());
            fcd.setNumPenalties(packetBuffer.readUnsignedByte());
            fcd.setNumTyreStints(packetBuffer.readUnsignedByte());

            for (int j = 0; j < PacketConstants.TYRE_STINTS; j++) {
                fcd.getTyreStintsActual()[j] = packetBuffer.readUnsignedByte();
            }
            for (int j = 0; j < PacketConstants.TYRE_STINTS; j++) {
                fcd.getTyreStintsVisual()[j] = packetBuffer.readUnsignedByte();
            }
            if(is2022Format(header)){
                for (int j = 0; j < PacketConstants.TYRE_STINTS; j++) {
                    fcd.getTyreStintsEndLaps()[j] = packetBuffer.readUnsignedByte();
                }
            }
            finalClassificationDataList.add(fcd);
        }
        packet.setFinalClassificationData(finalClassificationDataList);
        return packet;
    }

    private PacketEventData generateEventPacket(PacketHeader header) {
        PacketEventData packet = new PacketEventData();
        EventDataDetails edd = new EventDataDetails();
        packet.setHeader(header);
        packet.setEventCode(EventCode.valueFrom(PacketUtils.readString(packetBuffer, 4)));
        switch (packet.getEventCode()) {
            case FASTEST_LAP:
                FastestLap fl = new FastestLap();
                fl.setCarIndex(packetBuffer.readUnsignedByte());
                fl.setLapTime(packetBuffer.readFloatLE());
                edd.setFastestLap(fl);
                break;
            case RETIREMENT:
                Retirement r = new Retirement();
                r.setCarIndex(packetBuffer.readUnsignedByte());
                edd.setRetirement(r);
                break;
            case TEAM_MATE_IN_PITS:
                TeamMateInPits tmip = new TeamMateInPits();
                tmip.setCarIndex(packetBuffer.readUnsignedByte());
                edd.setTeamMateInPits(tmip);
                break;
            case RACE_WINNER:
                RaceWinner rw = new RaceWinner();
                rw.setCarIndex(packetBuffer.readUnsignedByte());
                edd.setRaceWinner(rw);
                break;
            case PENALTY_ISSUED:
                Penalty p = new Penalty();
                p.setPenaltyType(PenaltyType.valueOf(packetBuffer.readUnsignedByte()));
                p.setInfringementType(InfringementType.valueOf(packetBuffer.readUnsignedByte()));
                p.setCarIndex(packetBuffer.readUnsignedByte());
                p.setOtherCarIndex(packetBuffer.readUnsignedByte());
                p.setTime(packetBuffer.readUnsignedByte());
                p.setLapNum(packetBuffer.readUnsignedByte());
                p.setPlacesGained(packetBuffer.readUnsignedByte());
                edd.setPenalty(p);
                break;
            case SPEED_TRAP_TRIGGERED:
                SpeedTrap st = new SpeedTrap();
                st.setCarIndex(packetBuffer.readUnsignedByte());
                st.setSpeed(packetBuffer.readFloatLE());
                st.setIsOverallFastestInSession(packetBuffer.readUnsignedByte());
                st.setIsDriverFastestInSession(packetBuffer.readUnsignedByte());
                if(is2022Format(header)){
                    st.setFastestVehicleIdxInSession(packetBuffer.readUnsignedByte());
                    st.setFastestSpeedInSession(packetBuffer.readFloatLE());
                }
                edd.setSpeedTrap(st);
                break;
            case START_LIGHTS:
                StartLights sl = new StartLights();
                sl.setNumLights(packetBuffer.readUnsignedByte());
                edd.setStartLights(sl);
                break;
            case DRIVE_THROUGH_SERVED:
                DriveThroughPenaltyServed dtps = new DriveThroughPenaltyServed();
                dtps.setCarIndex(packetBuffer.readUnsignedByte());
                edd.setDriveThroughPenaltyServed(dtps);
                break;
            case STOP_GO_SERVED:
                StopGoPenaltyServed sgps = new StopGoPenaltyServed();
                sgps.setCarIndex(packetBuffer.readUnsignedByte());
                edd.setStopGoPenaltyServed(sgps);
                break;
            case FLASHBACK:
                Flashback fb = new Flashback();
                fb.setFlashbackFrameIdentifier(packetBuffer.readUnsignedIntLE());
                fb.setFlashbackSessionTime(packetBuffer.readFloatLE());
                edd.setFlashback(fb);
                break;
            case BUTTON_STATUS:
                Buttons bt = new Buttons();
                bt.setButtonStatus(packetBuffer.readUnsignedIntLE());
                edd.setButtons(bt);
                break;
            case SESSION_STARTED:
            case SESSION_ENDED:
            case DRS_ENABLED:
            case DRS_DISABLED:
            case CHEQUERED_FLAG:
            case LIGHTS_OUT:
                break;
            default:
                throw new IllegalArgumentException("EventCode=" + packet.getEventCode() + " not supported");
        }
        packet.setEventDataDetails(edd);
        return packet;
    }

    private PacketCarTelemetryData generateCarTelemetryPacket(PacketHeader header) {
        PacketCarTelemetryData packet = new PacketCarTelemetryData();
        List<CarTelemetryData> carTelemetryDataList = new ArrayList<>(PacketConstants.CARS);
        packet.setHeader(header);

        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarTelemetryData ctd = new CarTelemetryData();
            ctd.setSpeed(packetBuffer.readUnsignedShortLE());
            ctd.setThrottle(packetBuffer.readFloatLE());
            ctd.setSteer(packetBuffer.readFloatLE());
            ctd.setBrake(packetBuffer.readFloatLE());
            ctd.setClutch(packetBuffer.readUnsignedByte());
            ctd.setGear(packetBuffer.readByte());
            ctd.setEngineRPM(packetBuffer.readUnsignedShortLE());
            ctd.setDrs(packetBuffer.readUnsignedByte());
            ctd.setRevLightsPercent(packetBuffer.readUnsignedByte());
            ctd.setRevLightsBitValue(packetBuffer.readUnsignedShortLE());
            for (int j = 0; j < PacketConstants.TYRES; j++) {
                ctd.getBrakesTemperature()[j] = packetBuffer.readUnsignedShortLE();
            }
            for (int j = 0; j < PacketConstants.TYRES; j++) {
                ctd.getTyresSurfaceTemperature()[j] = packetBuffer.readUnsignedByte();
            }
            for (int j = 0; j < PacketConstants.TYRES; j++) {
                ctd.getTyresInnerTemperature()[j] = packetBuffer.readUnsignedByte();
            }
            ctd.setEngineTemperature(packetBuffer.readUnsignedShortLE());
            for (int j = 0; j < PacketConstants.TYRES; j++) {
                ctd.getTyresPressure()[j] = packetBuffer.readFloatLE();
            }
            for (int j = 0; j < PacketConstants.TYRES; j++) {
                ctd.getSurfaceType()[j] = SurfaceType.valueOf(packetBuffer.readUnsignedByte());
            }
            carTelemetryDataList.add(ctd);
        }
        packet.setCarTelemetryData(carTelemetryDataList);
        packet.setMfdPanelIndex(MfdPanel.valueOf(packetBuffer.readUnsignedByte()));
        packet.setMfdPanelIndexSecondaryPlayer(MfdPanel.valueOf(packetBuffer.readUnsignedByte()));
        packet.setSuggestedGear(packetBuffer.readByte());
        return packet;
    }

    private PacketCarStatusData generateCarStatusPacket(PacketHeader header) {
        PacketCarStatusData packet = new PacketCarStatusData();
        List<CarStatusData> carStatusDataList = new ArrayList<>(PacketConstants.CARS);
        packet.setHeader(header);

        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarStatusData csd = new CarStatusData();
            csd.setTractionControl(TractionControl.valueOf(packetBuffer.readUnsignedByte()));
            csd.setAntiLockBrakes(packetBuffer.readUnsignedByte());
            csd.setFuelMix(FuelMix.valueOf(packetBuffer.readUnsignedByte()));
            csd.setFrontBrakeBias(packetBuffer.readUnsignedByte());
            csd.setPitLimiterStatus(packetBuffer.readUnsignedByte());
            csd.setFuelInTank(packetBuffer.readFloatLE());
            csd.setFuelCapacity(packetBuffer.readFloatLE());
            csd.setFuelRemainingLaps(packetBuffer.readFloatLE());
            csd.setMaxRPM(packetBuffer.readUnsignedShortLE());
            csd.setIdleRPM(packetBuffer.readUnsignedShortLE());
            csd.setMaxGears(packetBuffer.readUnsignedByte());
            csd.setDrsAllowed(DrsAllowed.valueOf(packetBuffer.readUnsignedByte()));
            csd.setDrsActivationDistance(packetBuffer.readUnsignedShortLE());
            csd.setActualTyreCompound(ActualTyreCompound.valueOf(packetBuffer.readUnsignedByte()));
            csd.setVisualTyreCompound(VisualTyreCompound.valueOf(packetBuffer.readUnsignedByte()));
            csd.setTyresAgeLaps(packetBuffer.readUnsignedByte());
            csd.setCarFiaFlags(CarFiaFlag.valueOf(packetBuffer.readByte()));
            csd.setErsStoreEnergy(packetBuffer.readFloatLE());
            csd.setErsDeployMode(ErsDeployMode.valueOf(packetBuffer.readUnsignedByte()));
            csd.setErsHarvestedThisLapMGUK(packetBuffer.readFloatLE());
            csd.setErsHarvestedThisLapMGUH(packetBuffer.readFloatLE());
            csd.setErsDeployedThisLap(packetBuffer.readFloatLE());
            csd.setNetworkPaused(packetBuffer.readUnsignedByte());

            carStatusDataList.add(csd);
        }
        packet.setCarStatusData(carStatusDataList);
        return packet;
    }

    public PacketCarSetupData generateCarSetupPacket(PacketHeader header){
        PacketCarSetupData packet = new PacketCarSetupData();
        List<CarSetupData> carSetupDataList = new ArrayList<>(PacketConstants.CARS);
        packet.setHeader(header);

        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarSetupData csd = new CarSetupData();
            csd.setFrontWing(packetBuffer.readUnsignedByte());
            csd.setRearWing(packetBuffer.readUnsignedByte());
            csd.setOnThrottle(packetBuffer.readUnsignedByte());
            csd.setOffThrottle(packetBuffer.readUnsignedByte());
            csd.setFrontCamber(packetBuffer.readFloatLE());
            csd.setRearCamber(packetBuffer.readFloatLE());
            csd.setFrontToe(packetBuffer.readFloatLE());
            csd.setRearToe(packetBuffer.readFloatLE());
            csd.setFrontSuspension(packetBuffer.readUnsignedByte());
            csd.setRearSuspension(packetBuffer.readUnsignedByte());
            csd.setFrontAntiRollBar(packetBuffer.readUnsignedByte());
            csd.setRearAntiRollBar(packetBuffer.readUnsignedByte());
            csd.setFrontSuspensionHeight(packetBuffer.readUnsignedByte());
            csd.setRearSuspensionHeight(packetBuffer.readUnsignedByte());
            csd.setBrakePressure(packetBuffer.readUnsignedByte());
            csd.setBrakeBias(packetBuffer.readUnsignedByte());
            csd.setRearLeftTyrePressure(packetBuffer.readFloatLE());
            csd.setRearRightTyrePressure(packetBuffer.readFloatLE());
            csd.setFrontLeftTyrePressure(packetBuffer.readFloatLE());
            csd.setFrontRightTyrePressure(packetBuffer.readFloatLE());
            csd.setBallast(packetBuffer.readUnsignedByte());
            csd.setFuelLoad(packetBuffer.readFloatLE());

            carSetupDataList.add(csd);
        }
        packet.setCarSetupData(carSetupDataList);
        return packet;
    }

    public PacketCarDamageData generateCarDamagePacket(PacketHeader header){
        PacketCarDamageData packet = new PacketCarDamageData();
        List<CarDamageData> carDamageDataList = new ArrayList<>(PacketConstants.CARS);
        packet.setHeader(header);

        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarDamageData cdd = new CarDamageData();
            for (int j = 0; j < PacketConstants.TYRES; j++) {
                cdd.getTyresWear()[j] = packetBuffer.readUnsignedByte();
            }
            for (int j = 0; j < PacketConstants.TYRES; j++) {
                cdd.getTyresDamage()[j] = packetBuffer.readUnsignedByte();
            }
            for (int j = 0; j < PacketConstants.BRAKES; j++) {
                cdd.getBrakeDamage()[j] = packetBuffer.readUnsignedByte();
            }
            cdd.setFrontLeftWingDamage(packetBuffer.readUnsignedByte());
            cdd.setFrontRightWingDamage(packetBuffer.readUnsignedByte());
            cdd.setRearWingDamage(packetBuffer.readUnsignedByte());
            cdd.setFloorDamage(packetBuffer.readUnsignedByte());
            cdd.setDiffuserDamage(packetBuffer.readUnsignedByte());
            cdd.setSidePodDamage(packetBuffer.readUnsignedByte());
            cdd.setDrsFault(packetBuffer.readUnsignedByte());
            if(is2022Format(header)){
                cdd.setErsFault(packetBuffer.readUnsignedByte());
            }
            cdd.setGearBoxDamage(packetBuffer.readUnsignedByte());
            cdd.setEngineDamage(packetBuffer.readUnsignedByte());
            cdd.setEngineMGUHWear(packetBuffer.readUnsignedByte());
            cdd.setEngineESWear(packetBuffer.readUnsignedByte());
            cdd.setEngineCEWear(packetBuffer.readUnsignedByte());
            cdd.setEngineICEWear(packetBuffer.readUnsignedByte());
            cdd.setEngineTCWear(packetBuffer.readUnsignedByte());
            if(is2022Format(header)){
                cdd.setEngineBlown(packetBuffer.readUnsignedByte());
                cdd.setEngineSeized(packetBuffer.readUnsignedByte());
            }

            carDamageDataList.add(cdd);
        }
        packet.setCarDamageData(carDamageDataList);
        return packet;
    }

    public PacketHeader generatePacketHeader() {
        PacketHeader packetHeader = new PacketHeader();
        packetHeader.setPacketFormat(packetBuffer.readUnsignedShortLE());
        packetHeader.setGameMajorVersion(packetBuffer.readUnsignedByte());
        packetHeader.setGameMinorVersion(packetBuffer.readUnsignedByte());
        packetHeader.setPacketVersion(packetBuffer.readUnsignedByte());
        packetHeader.setPacketId(PacketId.valueOf(packetBuffer.readUnsignedByte()));
        packetHeader.setSessionUid(PacketUtils.toUnsignedBigInteger(packetBuffer.readLongLE()));
        packetHeader.setSessionTime(packetBuffer.readFloatLE());
        packetHeader.setFrameIdentifier(packetBuffer.readUnsignedIntLE());
        packetHeader.setPlayerCarIndex(packetBuffer.readUnsignedByte());
        packetHeader.setSecondaryPlayerCarIndex(packetBuffer.readUnsignedByte());
        return packetHeader;
    }

    private boolean is2022Format(PacketHeader header){
        if(header.getPacketFormat() == PacketConstants.FORMAT_2021){
            return true;
        }
        return false;
    }
}