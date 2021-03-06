
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Formula
 */
public enum Formula {
    F1_MODERN(0),
    F1_CLASSIC(1),
    F2(2),
    F1_GENERIC(3);
    
    private static final Map<Integer, Formula> map = new HashMap<>();

    static {
        for (Formula formula : Formula.values()) {
            map.put(formula.value, formula);
        }
    }

    private final int value;
    
    Formula(int value) {
        this.value = value;
    }

    public static Formula valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}