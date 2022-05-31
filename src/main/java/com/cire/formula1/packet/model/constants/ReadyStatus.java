
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Ready status
 */
public enum ReadyStatus {
    NOT_READY(0),
    READY(1),
    SPECTATING(2);
    
    private static Map<Integer, ReadyStatus> map = new HashMap<>();

    static {
        for (ReadyStatus readyStatus : ReadyStatus.values()) {
            map.put(readyStatus.value, readyStatus);
        }
    }

    private int value;
    
    ReadyStatus(int value) {
        this.value = value;
    }

    public static ReadyStatus valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}