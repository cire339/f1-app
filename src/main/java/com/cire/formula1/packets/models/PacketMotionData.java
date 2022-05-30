package com.cire.formula1.packets.models;

import com.cire.formula1.packets.utils.PacketConstants;
import com.cire.formula1.packets.models.data.CarMotionData;
import com.cire.formula1.packets.models.data.ExtraCarMotionData;

import java.util.ArrayList;
import java.util.List;

/**
 * Motion Packet
 * 
 * The motion packet gives physics data for all the cars being driven. 
 * There is additional data for the car being driven with the goal of being able to drive a motion platform setup.
 * Frequency: Rate as specified in menus
 */
public class PacketMotionData extends Packet {
    
    private List<CarMotionData> carMotionData = new ArrayList<>(PacketConstants.CARS);
    private ExtraCarMotionData extraCarMotionData = new ExtraCarMotionData();

    /**
     * @return Cars motion data
     */
    public List<CarMotionData> getCarMotionData() {
        return carMotionData;
    }

    public void setCarMotionData(List<CarMotionData> carMotionData) {
        this.carMotionData = carMotionData;
    }

    /**
     * @return Extra player car only data
     */
    public ExtraCarMotionData getExtraCarMotionData() {
        return extraCarMotionData;
    }

    public void setExtraCarMotionData(ExtraCarMotionData extraCarMotionData) {
        this.extraCarMotionData = extraCarMotionData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Motion[");
        sb.append(super.toString());
        sb.append(",carMotionData=");
        for (CarMotionData cmd : this.carMotionData) {
            sb.append(cmd.toString() + ",");
        }
        sb.append("extraCarMotionData=" + this.extraCarMotionData.toString());
        sb.append("]");
        return sb.toString();
    }
}