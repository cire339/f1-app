package com.cire.formula1.packets.models;

import com.cire.formula1.packets.models.constants.*;
import com.cire.formula1.packets.utils.PacketConstants;
import com.cire.formula1.packets.models.data.MarshalZone;
import com.cire.formula1.packets.models.data.WeatherForecastSample;

import java.util.ArrayList;
import java.util.List;

/**
 * Session Packet
 * 
 * The session packet includes details about the current session in progress.
 * Frequency: 2 per second
 */
public class PacketSessionData extends Packet {

    private Weather weather;
    private short trackTemperature;
    private short airTemperature;
    private short totalLaps;
    private int trackLength;
    private SessionType sessionType;
    private Track trackId;
    private Formula formula;
    private int sessionTimeLeft;
    private int sessionDuration;
    private short pitSpeedLimit;
    private short gamePaused;
    private short isSpectating;
    private short spectatorCarIndex;
    private short sliProNativeSupport;
    private short numMarshalZones;
    private List<MarshalZone> marshalZones = new ArrayList<>(PacketConstants.MARSHAL_ZONES);
    private SafetyCarStatus safetyCarStatus;
    private short networkGame;
    private short numWeatherForecastSamples;
    private List<WeatherForecastSample> weatherForecastSamples = new ArrayList<>(PacketConstants.WEATHER_FORECAST_SAMPLES);
    private short forecastAccuracy;
    private short aiDifficulty;
    private long seasonLinkIdentifier;
    private long weekendLinkIdentifier;
    private long sessionLinkIdentifier;
    private short pitStopWindowIdealLap;
    private short pitStopWindowLatestLap;
    private short pitStopRejoinPosition;
    private short steeringAssist;
    private short brakingAssist;
    private short gearboxAssist;
    private short pitAssist;
    private short pitReleaseAssist;
    private short ersAssist;
    private short drsAssist;
    private short dynamicRacingLine;
    private short dynamicRacingLineType;


    /**
     * @return Weather
     * Weather - 0 = clear, 1 = light cloud, 2 = overcast
     * 3 = light rain, 4 = heavy rain, 5 = storm
     */
    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    /**
     * @return Track temp. in degrees celsius
     */
    public short getTrackTemperature() {
        return trackTemperature;
    }

    public void setTrackTemperature(short trackTemperature) {
        this.trackTemperature = trackTemperature;
    }

    /**
     * @return Air temp. in degrees celsius
     */
    public short getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(short airTemperature) {
        this.airTemperature = airTemperature;
    }

    /**
     * @return Total number of laps in this race
     */
    public short getTotalLaps() {
        return totalLaps;
    }

    public void setTotalLaps(short totalLaps) {
        this.totalLaps = totalLaps;
    }

    /**
     * @return Track length in metres
     */
    public int getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(int trackLength) {
        this.trackLength = trackLength;
    }

    /**
     * @return Session type
     * 0 = unknown, 1 = P1, 2 = P2, 3 = P3, 4 = Short P
     * 5 = Q1, 6 = Q2, 7 = Q3, 8 = Short Q, 9 = OSQ
     * 10 = R, 11 = R2, 12 = Time Trial
     */
    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    /**
     * @return Track ID
     * -1 for unknown, 0-21 for tracks, see appendix
     */
    public Track getTrackId() {
        return trackId;
    }

    public void setTrackId(Track trackId) {
        this.trackId = trackId;
    }

    /**
     * @return Formula
     * Formula, 0 = F1 Modern, 1 = F1 Classic, 2 = F2, 3 = F1 Generic
     */
    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    /**
     * @return Time left in session in seconds
     */
    public int getSessionTimeLeft() {
        return sessionTimeLeft;
    }

    public void setSessionTimeLeft(int sessionTimeLeft) {
        this.sessionTimeLeft = sessionTimeLeft;
    }

    /**
     * @return Session duration in seconds
     */
    public int getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(int sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    /**
     * @return Pit speed limit in kilometres per hour
     */
    public short getPitSpeedLimit() {
        return pitSpeedLimit;
    }

    public void setPitSpeedLimit(short pitSpeedLimit) {
        this.pitSpeedLimit = pitSpeedLimit;
    }

    /**
     * @return Whether the game is paused
     */
    public short getGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(short gamePaused) {
        this.gamePaused = gamePaused;
    }

    /**
     * @return Whether the player is spectating
     */
    public short getIsSpectating() {
        return isSpectating;
    }

    public void setIsSpectating(short isSpectating) {
        this.isSpectating = isSpectating;
    }

    /**
     * @return Index of the car being spectated
     */
    public short getSpectatorCarIndex() {
        return spectatorCarIndex;
    }

    public void setSpectatorCarIndex(short spectatorCarIndex) {
        this.spectatorCarIndex = spectatorCarIndex;
    }

    /**
     * @return SLI Pro support, 0 = inactive, 1 = active
     */
    public short getSliProNativeSupport() {
        return sliProNativeSupport;
    }

    public void setSliProNativeSupport(short sliProNativeSupport) {
        this.sliProNativeSupport = sliProNativeSupport;
    }

    /**
     * @return Number of marshal zones to follow
     */
    public short getNumMarshalZones() {
        return numMarshalZones;
    }

    public void setNumMarshalZones(short numMarshalZones) {
        this.numMarshalZones = numMarshalZones;
    }

    /**
     * @return List of marshal zones – max 21
     */
    public List<MarshalZone> getMarshalZones() {
        return marshalZones;
    }

    public void setMarshalZones(List<MarshalZone> marshalZones) {
        this.marshalZones = marshalZones;
    }

    /**
     * @return Safety car status
     * 0 = no safety car, 1 = full safety car
     * 2 = virtual safety car
     * 0 = offline, 1 = online
     */
    public SafetyCarStatus getSafetyCarStatus() {
        return safetyCarStatus;
    }

    public void setSafetyCarStatus(SafetyCarStatus safetyCarStatus) {
        this.safetyCarStatus = safetyCarStatus;
    }

    /**
     * @return Network game
     * 0 = offline, 1 = online
     */
    public short getNetworkGame() {
        return networkGame;
    }

    public void setNetworkGame(short networkGame) {
        this.networkGame = networkGame;
    }

    /**
     * @return Number of weather samples to follow
     */
    public short getNumWeatherForecastSamples() {
        return numWeatherForecastSamples;
    }

    public void setNumWeatherForecastSamples(short numWeatherForecastSamples) {
        this.numWeatherForecastSamples = numWeatherForecastSamples;
    }

    /**
     * @return List of weather forecast samples
     */
    public List<WeatherForecastSample> getWeatherForecastSamples() {
        return weatherForecastSamples;
    }

    public void setWeatherForecastSamples(List<WeatherForecastSample> weatherForecastSamples) {
        this.weatherForecastSamples = weatherForecastSamples;
    }

    /**
     * @return Forecast Accuracy.
     * 0 = Perfect, 1 = Approximate
     */
    public short getForecastAccuracy() {
        return forecastAccuracy;
    }

    public void setForecastAccuracy(short forecastAccuracy) {
        this.forecastAccuracy = forecastAccuracy;
    }

    /**
     * @return AI Difficulty rating
     * 0-100
     */
    public short getAiDifficulty() {
        return aiDifficulty;
    }

    public void setAiDifficulty(short aiDifficulty) {
        this.aiDifficulty = aiDifficulty;
    }

    /**
     * @return Identifier for season - persists across saves
     */
    public float getSeasonLinkIdentifier() {
        return seasonLinkIdentifier;
    }

    public void setSeasonLinkIdentifier(long seasonLinkIdentifier) {
        this.seasonLinkIdentifier = seasonLinkIdentifier;
    }

    /**
     * @return Identifier for weekend - persists across saves
     */
    public float getWeekendLinkIdentifier() {
        return weekendLinkIdentifier;
    }

    public void setWeekendLinkIdentifier(long weekendLinkIdentifier) {
        this.weekendLinkIdentifier = weekendLinkIdentifier;
    }

    /**
     * @return Identifier for session - persists across saves
     */
    public float getSessionLinkIdentifier() {
        return sessionLinkIdentifier;
    }

    public void setSessionLinkIdentifier(long sessionLinkIdentifier) {
        this.sessionLinkIdentifier = sessionLinkIdentifier;
    }

    /**
     * @return Ideal lap to pit on for current strategy (player)
     */
    public short getPitStopWindowIdealLap() {
        return pitStopWindowIdealLap;
    }

    public void setPitStopWindowIdealLap(short pitStopWindowIdealLap) {
        this.pitStopWindowIdealLap = pitStopWindowIdealLap;
    }

    /**
     * @return Latest lap to pit on for current strategy (player)
     */
    public short getPitStopWindowLatestLap() {
        return pitStopWindowLatestLap;
    }

    public void setPitStopWindowLatestLap(short pitStopWindowLatestLap) {
        this.pitStopWindowLatestLap = pitStopWindowLatestLap;
    }

    /**
     * @return Predicted position to rejoin at (player)
     */
    public short getPitStopRejoinPosition() {
        return pitStopRejoinPosition;
    }

    public void setPitStopRejoinPosition(short pitStopRejoinPosition) {
        this.pitStopRejoinPosition = pitStopRejoinPosition;
    }

    /**
     * @return Steering Assist
     * 0 = off, 1 = on
     */
    public short getSteeringAssist() {
        return steeringAssist;
    }

    public void setSteeringAssist(short steeringAssist) {
        this.steeringAssist = steeringAssist;
    }

    /**
     * @return Braking Assist
     * 0 = off, 1 = low, 2 = medium, 3 = high
     */
    public short getBrakingAssist() {
        return brakingAssist;
    }

    public void setBrakingAssist(short brakingAssist) {
        this.brakingAssist = brakingAssist;
    }

    /**
     * @return Gearbox Assist
     * 1 = manual, 2 = manual & suggested gear, 3 = auto
     */
    public short getGearboxAssist() {
        return gearboxAssist;
    }

    public void setGearboxAssist(short gearboxAssist) {
        this.gearboxAssist = gearboxAssist;
    }

    /**
     * @return Pit Assist
     * 0 = off, 1 = on
     */
    public short getPitAssist() {
        return pitAssist;
    }

    public void setPitAssist(short pitAssist) {
        this.pitAssist = pitAssist;
    }

    /**
     * @return Pit release assist
     * 0 = off, 1 = on
     */
    public short getPitReleaseAssist() {
        return pitReleaseAssist;
    }

    public void setPitReleaseAssist(short pitReleaseAssist) {
        this.pitReleaseAssist = pitReleaseAssist;
    }

    /**
     * @return ERS Assist
     * 0 = off, 1 = on
     */
    public short getErsAssist() {
        return ersAssist;
    }

    public void setErsAssist(short ersAssist) {
        this.ersAssist = ersAssist;
    }

    /**
     * @return DRS Assist
     * 0 = off, 1 = on
     */
    public short getDrsAssist() {
        return drsAssist;
    }

    public void setDrsAssist(short drsAssist) {
        this.drsAssist = drsAssist;
    }

    /**
     * @return Dynamic Racing Line
     * 0 = off, 1 = corners only, 2 = full
     */
    public short getDynamicRacingLine() {
        return dynamicRacingLine;
    }

    public void setDynamicRacingLine(short dynamicRacingLine) {
        this.dynamicRacingLine = dynamicRacingLine;
    }

    /**
     * @return Dynamics Racing Line Type
     * 0 = 2D, 1 = 3D
     */
    public short getDynamicRacingLineType() {
        return dynamicRacingLineType;
    }

    public void setDynamicRacingLineType(short dynamicRacingLineType) {
        this.dynamicRacingLineType = dynamicRacingLineType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Session[");
        sb.append(super.toString());
        sb.append(",weather=" + this.weather);
        sb.append(",trackTemperature=" + this.trackTemperature);
        sb.append(",airTemperature=" + this.airTemperature);
        sb.append(",totalLaps=" + this.totalLaps);
        sb.append(",trackLength=" + this.trackLength);
        sb.append(",sessionType=" + this.sessionType);
        sb.append(",trackId=" + this.trackId);
        sb.append(",formula=" + this.formula);
        sb.append(",sessionTimeLeft=" + this.sessionTimeLeft);
        sb.append(",sessionDuration=" + this.sessionDuration);
        sb.append(",pitSpeedLimit=" + this.pitSpeedLimit);
        sb.append(",gamePaused=" + this.gamePaused);
        sb.append(",isSpectating=" + this.isSpectating);
        sb.append(",spectatorCarIndex" + this.spectatorCarIndex);
        sb.append(",sliProNativeSupport=" + this.sliProNativeSupport);
        sb.append(",numMarshalZones=" + this.numMarshalZones);
        sb.append(",marshalZones=");
        for (MarshalZone mz: marshalZones) {
            sb.append(mz.toString() + ",");
        }
        sb.append("safetyCarStatus=" + this.safetyCarStatus);
        sb.append(",networkGame=" + this.networkGame);
        sb.append(",numWeatherForecastSamples=" + this.numWeatherForecastSamples);
        sb.append(",weatherForecastSamples=");
        for (WeatherForecastSample wfs : weatherForecastSamples) {
            sb.append(wfs.toString() + ",");
        }
        sb.append("forecastAccuracy=" + this.forecastAccuracy);
        sb.append(",aiDifficulty" + this.aiDifficulty);
        sb.append(",seasonLinkIdentifier" + this.seasonLinkIdentifier);
        sb.append(",weekendLinkIdentifier" + this.weekendLinkIdentifier);
        sb.append(",sessionLinkIdentifier" + this.sessionLinkIdentifier);
        sb.append(",pitStopWindowIdealLap" + this.pitStopWindowIdealLap);
        sb.append(",pitStopWindowLatestLap" + this.pitStopWindowLatestLap);
        sb.append(",pitStopRejoinPosition" + this.pitStopRejoinPosition);
        sb.append(",steeringAssist" + this.steeringAssist);
        sb.append(",brakingAssist" + this.brakingAssist);
        sb.append(",gearboxAssist" + this.gearboxAssist);
        sb.append(",pitAssist" + this.pitAssist);
        sb.append(",pitReleaseAssist" + this.pitReleaseAssist);
        sb.append(",ersAssist" + this.ersAssist);
        sb.append(",drsAssist" + this.drsAssist);
        sb.append(",dynamicRacingLine" + this.dynamicRacingLine);
        sb.append(",dynamicRacingLineType" + this.dynamicRacingLineType);
        sb.append("]");
        return sb.toString();
    }
}