
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Result status
 */
public enum ResultStatus {
    INVALID(0),
    INACTIVE(1),
    ACTIVE(2),
    FINISHED(3),
    DISQUALIFIED(4),
    NOT_CLASSIFIED(5),
    RETIRED(6);

    private static final Map<Integer, ResultStatus> map = new HashMap<>();

    static {
        for (ResultStatus resultStatus : ResultStatus.values()) {
            map.put(resultStatus.value, resultStatus);
        }
    }

    private final int value;
    
    ResultStatus(int value) {
        this.value = value;
    }

    public static ResultStatus valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}