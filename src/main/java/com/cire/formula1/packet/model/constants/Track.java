
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Track
 */
public enum Track {
    MELBOURNE(0),
    PAUL_RICARD(1),
    SHANGHAI(2),
    SAKHIR_BAHRAIN(3),
    CATALUNYA(4),
    MONACO(5),
    MONTREAL(6),
    SILVERSTONE(7),
    HOCKENHEIM(8),
    HUNGARORING(9),
    SPA(10),
    MONZA(11),
    SINGAPORE(12),
    SUZUKA(13),
    ABU_DHABI(14),
    TEXAS(15),
    BRAZIL(16),
    AUSTRIA(17),
    SOCHI(18),
    MEXICO(19),
    BAKU_AZERBAIJAN(20),
    SAKHIR_SHORT(21),
    SILVERSTONE_SHORT(22),
    TEXAS_SHORT(23),
    SUZUKA_SHORT(24),
    HANOI(25),
    ZANDVOORT(26),
    IMOLA(27),
    PORTIMAO(28),
    JEDDAH(29);

    private static Map<Integer, Track> map = new HashMap<>();

    static {
        for (Track track : Track.values()) {
            map.put(track.value, track);
        }
    }

    private int value;
    
    Track(int value) {
        this.value = value;
    }

    public static Track valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}