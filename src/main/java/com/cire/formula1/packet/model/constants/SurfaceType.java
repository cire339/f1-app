
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Surface type
 * These types are from physics data and show what type of contact each wheel is experiencing.
 */
public enum SurfaceType {
    TARMAC(0),
    RUMBLE_STRIP(1),
    CONCRETE(2),
    ROCK(3),
    GRAVEL(4),
    MUD(5),
    SAND(6),
    GRASS(7),
    WATER(8),
    COBBLESTONE(9),
    METAL(10),
    RIDGED(11);
    
    private static final Map<Integer, SurfaceType> map = new HashMap<>();

    static {
        for (SurfaceType surfaceType : SurfaceType.values()) {
            map.put(surfaceType.value, surfaceType);
        }
    }

    private final int value;
    
    SurfaceType(int value) {
        this.value = value;
    }

    public static SurfaceType valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}