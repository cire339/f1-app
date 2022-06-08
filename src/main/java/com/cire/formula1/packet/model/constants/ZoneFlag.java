
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Zone flag
 */
public enum ZoneFlag {
    INVALID_UNKNOWN(-1),
    NONE(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    RED(4);

    private static final Map<Integer, ZoneFlag> map = new HashMap<>();

    static {
        for (ZoneFlag zoneFlag : ZoneFlag.values()) {
            map.put(zoneFlag.value, zoneFlag);
        }
    }

    private final int value;
    
    ZoneFlag(int value) {
        this.value = value;
    }

    public static ZoneFlag valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}