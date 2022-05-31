package com.cire.formula1.packet.model;

import com.cire.formula1.packet.util.PacketConstants;
import com.cire.formula1.packet.model.data.CarDamageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Car Damage Packet
 *
 * This packet details car damage parameters for all the cars in the race.
 * Frequency: 2 per second
 */
public class PacketCarDamageData extends Packet {

    private List<CarDamageData> carDamageData = new ArrayList<>(PacketConstants.CARS);

    /**
     * @return Car damage data for all cars
     */
    public List<CarDamageData> getCarDamageData() {
        return carDamageData;
    }

    public void setCarDamageData(List<CarDamageData> carDamageData) {
        this.carDamageData = carDamageData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarDamage[");
        sb.append(super.toString());
        sb.append(",carDamageData=");
        for (CarDamageData c: carDamageData) {
            sb.append(c.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

}
