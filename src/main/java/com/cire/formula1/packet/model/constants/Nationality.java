
package com.cire.formula1.packet.model.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Nationality
 */
public enum Nationality {
    USA(1),
    ARGENTINA(2),
    AUSTRALIA(3),
    AUSTRIA(4),
    AZERBAIJAN(5),
    BAHRAIN(6),
    BELGIUM(7),
    BOLIVIA(8),
    BRAZIL(9),
    UK(10),
    BULGARIA(11),
    CAMEROON(12),
    CANADA(13),
    CHILE(14),
    CHINA(15),
    COLOMBIA(16),
    COSTA_RICA(17),
    CROATIA(18),
    CYPRUS(19),
    CZECHIA(20),
    DANMARK(21),
    NETHERLANDS(22),
    ECUADOR(23),
    ENGLAND(24),
    EMIRATES(25),
    ESTONIA(26),
    FINLAND(27),
    FRANCE(28),
    GERMANY(29),
    GHANA(30),
    GREECE(31),
    GUATEMALA(32),
    HONDURAS(33),
    HONK_KONG(34),
    HUNGARY(35),
    ICELAND(36),
    INDIA(37),
    INDONESIA(38),
    IRELAND(39),
    ISRAEL(40),
    ITALY(41),
    JAMAICA(42),
    JAPAN(43),
    JORDAN(44),
    KUWAIT(45),
    LATVIA(46),
    LEBANON(47),
    LITHUANIA(48),
    LUXEMBOURG(49),
    MALAYSIA(50),
    MALTA(51),
    MEXICO(52),
    MONACO(53),
    NEW_ZEALAND(54),
    NICARAGUA(55),
    NORTHERN_IRELAND(56),
    NORWAY(57),
    OMAN(58),
    PAKISTAN(59),
    PANAMA(60),
    PARAGUAY(61),
    PERU(62),
    POLAND(63),
    PORTUGAL(64),
    QATAR(65),
    ROMANIA(66),
    RUSSIA(67),
    EL_SALVADOR(68),
    SAUDI_ARABIA(69),
    SCOTLAND(70),
    SERBIA(71),
    SINGAPORE(72),
    SLOVAKIA(73),
    SLOVENIA(74),
    SOUTH_KOREA(75),
    SOUTH_AFRICA(76),
    SPAIN(77),
    SWEDEN(78),
    SWITZERLAND(79),
    THAILAND(80),
    TURKEY(81),
    URUGUAY(82),
    UKRAINE(83),
    VENEZUELA(84),
    BARBADOS(85),
    WALES(86),
    VIETNAM(87);

    private static Map<Integer, Nationality> map = new HashMap<>();

    static {
        for (Nationality nationality : Nationality.values()) {
            map.put(nationality.value, nationality);
        }
    }

    private int value;
    
    Nationality(int value) {
        this.value = value;
    }

    public static Nationality valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}