
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * MFD panel
 */
public enum MfdPanel {
    CAR_SETUP(0),
    PITS(1),
    DAMAGE(2),
    ENGINE(3),
    TEMPERATURES(4),
    MFD_CLOSED(255);

    private static Map<Integer, MfdPanel> map = new HashMap<>();

    static {
        for (MfdPanel mfdPanel : MfdPanel.values()) {
            map.put(mfdPanel.value, mfdPanel);
        }
    }

    private int value;
    
    MfdPanel(int value) {
        this.value = value;
    }

    public static MfdPanel valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}