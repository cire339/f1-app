
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Sector
 */
public enum Sector {
    SECTOR1(0),
    SECTOR2(1),
    SECTOR3(2);

    private static final Map<Integer, Sector> map = new HashMap<>();

    static {
        for (Sector sector : Sector.values()) {
            map.put(sector.value, sector);
        }
    }

    private final int value;
    
    Sector(int value) {
        this.value = value;
    }

    public static Sector valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}