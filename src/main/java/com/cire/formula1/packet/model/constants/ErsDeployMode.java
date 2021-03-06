
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * ERS deploy mode
 */
public enum ErsDeployMode {
    NONE(0),
    MEDIUM(1),
    OVERTAKE(2),
    HOTLAP(3);
    
    private static final Map<Integer, ErsDeployMode> map = new HashMap<>();

    static {
        for (ErsDeployMode ersDeployMode : ErsDeployMode.values()) {
            map.put(ersDeployMode.value, ersDeployMode);
        }
    }

    private final int value;
    
    ErsDeployMode(int value) {
        this.value = value;
    }

    public static ErsDeployMode valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}