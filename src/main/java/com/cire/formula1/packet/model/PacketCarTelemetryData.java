package com.cire.formula1.packet.model;

import com.cire.formula1.packet.model.data.CarTelemetryData;
import com.cire.formula1.packet.util.PacketConstants;
import com.cire.formula1.packet.model.constants.MfdPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Car Telemetry Packet
 * 
 * This packet details telemetry for all the cars in the race. It details
 * various values that would be recorded on the car such as speed, throttle
 * application, DRS etc.
 * Frequency: Rate as specified in menus
 */
public class PacketCarTelemetryData extends Packet {
    
    private List<CarTelemetryData> carTelemetryData = new ArrayList<>(PacketConstants.CARS);
    private MfdPanel mfdPanelIndex;
    private MfdPanel mfdPanelIndexSecondaryPlayer;
    private short suggestedGear;

    /**
     * @return Car telemetry data for all cars
     */
    public List<CarTelemetryData> getCarTelemetryData() {
        return carTelemetryData;
    }

    public void setCarTelemetryData(List<CarTelemetryData> carTelemetryData) {
        this.carTelemetryData = carTelemetryData;
    }

    /**
     * @return Index of MFD panel open
     * 255 = MFD closed
     * Single player, race – 0 = Car setup, 1 = Pits
     * 2 = Damage, 3 =  Engine, 4 = Temperatures
     * May vary depending on game mode
     */
    public MfdPanel getMfdPanelIndex() {
        return mfdPanelIndex;
    }

    public void setMfdPanelIndex(MfdPanel mfdPanelIndex) {
        this.mfdPanelIndex = mfdPanelIndex;
    }

    /**
     * @return Index of MFD panel open for secondary player
     */
    public MfdPanel getMfdPanelIndexSecondaryPlayer() {
        return mfdPanelIndexSecondaryPlayer;
    }

    public void setMfdPanelIndexSecondaryPlayer(MfdPanel mfdPanelIndexSecondaryPlayer) {
        this.mfdPanelIndexSecondaryPlayer = mfdPanelIndexSecondaryPlayer;
    }

    /**
     * @return Suggested gear for the player (1-8)
     * 0 if no gear suggested
     */
    public short getSuggestedGear() {
        return suggestedGear;
    }

    public void setSuggestedGear(short suggestedGear) {
        this.suggestedGear = suggestedGear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarTelemetry[");
        sb.append(super.toString());
        sb.append(",carTelemetryData=");
        for (CarTelemetryData c : carTelemetryData) {
            sb.append(c.toString() + ",");
        }
        sb.append(",mfdPanelIndex=" + this.mfdPanelIndex);
        sb.append(",mfdPanelIndexSecondaryPlayer=" + this.mfdPanelIndexSecondaryPlayer);
        sb.append(",suggestedGear=" + this.suggestedGear);
        sb.append("]");
        return sb.toString();
    }

}