
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Traction control
 */
public enum TractionControl {
    OFF(0),
    MEDIUM(1),
    HIGH(2);

    private static final Map<Integer, TractionControl> map = new HashMap<>();

    static {
        for (TractionControl tractionControl : TractionControl.values()) {
            map.put(tractionControl.value, tractionControl);
        }
    }

    private final int value;
    
    TractionControl(int value) {
        this.value = value;
    }

    public static TractionControl valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}