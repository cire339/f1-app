
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Car FIA flag
 */
public enum CarFiaFlag {
    INVALID_UNKNOWN(-1),
    NONE(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    RED(4);
    
    private static Map<Integer, CarFiaFlag> map = new HashMap<>();

    static {
        for (CarFiaFlag carFiaFlag : CarFiaFlag.values()) {
            map.put(carFiaFlag.value, carFiaFlag);
        }
    }

    private int value;
    
    CarFiaFlag(int value) {
        this.value = value;
    }

    public static CarFiaFlag valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}