package com.cire.formula1.packet.util;

import io.netty.buffer.ByteBuf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * Utility methods used while decoding packets
 */
public class PacketUtils {
    
    /**
     * Get the String instance from the related bytes in the input buffer
     * The string can be null terminated or filling the buffer until the end
     * 
     * @param buffer buffer containing the string bytes
     * @param maxLength max length of the string
     * @return String instance
     */
    public static String readString(ByteBuf buffer, int maxLength) {
        int result = buffer.bytesBefore(maxLength, (byte) 0);
        // if the string is not null terminated, just read all the characters
        result = result == -1 ? maxLength : result;
        ByteBuf b = buffer.readBytes(result);
        String s = b.toString(StandardCharsets.UTF_8);
        b.release();
        buffer.skipBytes(maxLength - result);
        return s;
    }

    /**
     * Convert a normalized vector represented as 16-bit signed value to float
     * 
     * @param value normalized vector represented as 16-bit signed value
     * @return normalized vector as float
     */
    public static float normalizedVectorToFloat(short value) {
        return value / 32767.0f;
    }

    /**
     * Return a BigInteger equal to the unsigned value of the
     * argument.
     */
    public static BigInteger toUnsignedBigInteger(long i) {
        if (i >= 0L)
            return BigInteger.valueOf(i);
        else {
            int upper = (int) (i >>> 32);
            int lower = (int) i;

            // return (upper << 32) + lower
            return (BigInteger.valueOf(Integer.toUnsignedLong(upper))).shiftLeft(32).
                    add(BigInteger.valueOf(Integer.toUnsignedLong(lower)));
        }
    }
}