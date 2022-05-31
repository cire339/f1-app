
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Vehicle FIA flag
 */
public enum VehicleFiaFlag {
    INVALID_UNKNOWN(-1),
    NONE(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    RED(4);
    
    private static Map<Integer, VehicleFiaFlag> map = new HashMap<>();

    static {
        for (VehicleFiaFlag vehicleFiaFlag : VehicleFiaFlag.values()) {
            map.put(vehicleFiaFlag.value, vehicleFiaFlag);
        }
    }

    private int value;
    
    VehicleFiaFlag(int value) {
        this.value = value;
    }

    public static VehicleFiaFlag valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}