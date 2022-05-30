package com.cire.formula1.packets.models;

import com.cire.formula1.packets.utils.PacketConstants;
import com.cire.formula1.packets.models.data.FinalClassificationData;

import java.util.ArrayList;
import java.util.List;

/**
 * Final Classification Packet
 * 
 * This packet details the final classification at the end of the race, and the
 * data will match with the post race results screen. This is especially useful
 * for multiplayer games where it is not always possible to send lap times on
 * the final frame because of network delay.
 * Frequency: Once at the end of a race
 */
public class PacketFinalClassificationData extends Packet {
    
    private short numCars;
    private List<FinalClassificationData> finalClassificationData = new ArrayList<>(PacketConstants.CARS);

    /**
     * @return Number of cars in the final classification
     */
    public short getNumCars() {
        return numCars;
    }

    public void setNumCars(short numCars) {
        this.numCars = numCars;
    }

    /**
     * @return Final classification data for all cars
     */
    public List<FinalClassificationData> getFinalClassificationData() {
        return finalClassificationData;
    }

    public void setFinalClassificationData(List<FinalClassificationData> finalClassificationData) {
        this.finalClassificationData = finalClassificationData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FinalClassification[");
        sb.append(super.toString());
        sb.append("numCars=" + this.numCars);
        sb.append(",finalClassificationData=");
        for (FinalClassificationData f : finalClassificationData) {
            sb.append(f.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return  sb.toString();
    }

}