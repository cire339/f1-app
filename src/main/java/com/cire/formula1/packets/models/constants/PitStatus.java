
package com.cire.formula1.packets.models.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Pit status
 */
public enum PitStatus {
    NONE(0),
    PITTING(1),
    IN_PIT_AREA(2);

    private static Map<Integer, PitStatus> map = new HashMap<>();

    static {
        for (PitStatus pitStatus : PitStatus.values()) {
            map.put(pitStatus.value, pitStatus);
        }
    }

    private int value;
    
    PitStatus(int value) {
        this.value = value;
    }

    public static PitStatus valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}