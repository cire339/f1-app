
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Fuel mix
 */
public enum FuelMix {
    LEAN(0),
    STANDARD(1),
    RICH(2),
    MAX(3);
    
    private static Map<Integer, FuelMix> map = new HashMap<>();

    static {
        for (FuelMix fuelMix : FuelMix.values()) {
            map.put(fuelMix.value, fuelMix);
        }
    }

    private int value;
    
    FuelMix(int value) {
        this.value = value;
    }

    public static FuelMix valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}