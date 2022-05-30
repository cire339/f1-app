package com.cire.formula1.packets.models.data;

import com.cire.formula1.packets.models.constants.SessionType;
import com.cire.formula1.packets.models.constants.Weather;

public class WeatherForecastSample {

    public static final int SIZE = 8;

    private SessionType sessionType;
    private short timeOffset;
    private Weather weather;
    private short trackTemperature;
    private short trackTemperatureChange;
    private short airTemperature;
    private short airTemperatureChange;
    private short rainPercentage;

    /**
     * @return Session type
     * 0 = unknown, 1 = P1, 2 = P2, 3 = P3, 4 = Short P, 5 = Q1
     * 6 = Q2, 7 = Q3, 8 = Short Q, 9 = OSQ, 10 = R, 11 = R2
     * 12 = Time Trial
     */
    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    /**
     * @return Time in minutes the forecast is for
     */
    public short getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(short timeOffset) {
        this.timeOffset = timeOffset;
    }

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
     * @return Track temp. change – 0 = up, 1 = down, 2 = no change
     */
    public short getTrackTemperatureChange() {
        return trackTemperatureChange;
    }

    public void setTrackTemperatureChange(short trackTemperatureChange) {
        this.trackTemperatureChange = trackTemperatureChange;
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
     * @return Air temp. change – 0 = up, 1 = down, 2 = no change
     */
    public short getAirTemperatureChange() {
        return airTemperatureChange;
    }

    public void setAirTemperatureChange(short airTemperatureChange) {
        this.airTemperatureChange = airTemperatureChange;
    }

    /**
     * @return Rain percentage (0-100)
     */
    public short getRainPercentage() {
        return rainPercentage;
    }

    public void setRainPercentage(short rainPercentage) {
        this.rainPercentage = rainPercentage;
    }

    @Override
    public String toString() {
        return "WeatherForecastSample[sessionType=" + this.sessionType +
                ",timeOffset=" + this.timeOffset +
                ",weather=" + this.weather +
                ",trackTemperature=" + this.trackTemperature +
                ",trackTemperateChange=" + this.trackTemperatureChange +
                ",airTemperature=" + this.airTemperature +
                ",airTemperatureChange=" + this.airTemperatureChange +
                ",rainPercentage=" + this.rainPercentage +
                "]";
    }
}
