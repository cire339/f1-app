
package com.cire.formula1.packets.models.constants;

import java.util.HashMap;
import java.util.Map;

public enum SafetyCarStatus {
    NO_SAFETY_CAR(0),
    FULL_SAFETY_CAR(1),
    VIRTUAL_SAFETY_CAR(2);
    
    private static Map<Integer, SafetyCarStatus> map = new HashMap<>();

    static {
        for (SafetyCarStatus safetyCarStatus : SafetyCarStatus.values()) {
            map.put(safetyCarStatus.value, safetyCarStatus);
        }
    }

    private int value;
    
    SafetyCarStatus(int value) {
        this.value = value;
    }

    public static SafetyCarStatus valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}