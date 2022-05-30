package com.cire.formula1.packets.models;

import com.cire.formula1.packets.utils.PacketConstants;
import com.cire.formula1.packets.models.data.CarSetupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Car Setups Packet
 * 
 * This packet details the car setups for each vehicle in the session. Note that
 * in multiplayer games, other player cars will appear as blank, you will only
 * be able to see your car setup and AI cars.
 *
 * Frequency: 2 per second
 */
public class PacketCarSetupData extends Packet {

    // 1102
    public static final int SIZE = PacketHeader.SIZE +
                                    CarSetupData.SIZE * PacketConstants.CARS ;
    
    private List<CarSetupData> carSetupData = new ArrayList<>(PacketConstants.CARS);

    /**
     * @return Car setup data for all cars
     */
    public List<CarSetupData> getCarSetupData() {
        return carSetupData;
    }

    public void setCarSetupData(List<CarSetupData> carSetupData) {
        this.carSetupData = carSetupData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarSetup[");
        sb.append(super.toString());
        sb.append(",carSetupData=");
        for (CarSetupData c: carSetupData) {
            sb.append(c.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

}