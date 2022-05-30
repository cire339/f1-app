
package com.cire.formula1.packets.models;

import com.cire.formula1.packets.utils.PacketConstants;
import com.cire.formula1.packets.models.data.CarStatusData;

import java.util.ArrayList;
import java.util.List;

/**
 * Car Status Packet
 * 
 * This packet details car statuses for all the cars in the race. It includes
 * values such as the damage readings on the car.
 * Frequency: Rate as specified in menus
 */
public class PacketCarStatusData extends Packet {

    // 1058
    public static final int SIZE = PacketHeader.SIZE
                                    + CarStatusData.SIZE * PacketConstants.CARS;
    
    private List<CarStatusData> carStatusData = new ArrayList<>(PacketConstants.CARS);

    /**
     * @return Car status data for all cars
     */
    public List<CarStatusData> getCarStatusData() {
        return carStatusData;
    }

    public void setCarStatusData(List<CarStatusData> carStatusData) {
        this.carStatusData = carStatusData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarStatus[");
        sb.append(super.toString());
        sb.append(",carStatusData=");
        for (CarStatusData c : carStatusData) {
            sb.append(c.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

}