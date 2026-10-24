/*
 *  Copyright 2015 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.classlib.java.util;

import org.teavm.classlib.PlatformDetector;
import org.teavm.classlib.java.lang.TComparable;
import org.teavm.jso.crypto.Crypto;

public class TUUID implements TComparable<TUUID> {
    private String value;
    private long mostSigBits;
    private long leastSigBits;

    private TUUID(String value) {
        this.value = value;
        parseBits();
    }

    public TUUID(long mostSigBits, long leastSigBits) {
        this.mostSigBits = mostSigBits;
        this.leastSigBits = leastSigBits;
        this.value = format(mostSigBits, leastSigBits);
    }

    private void parseBits() {
        String hex = value.replace("-", "");
        if (hex.length() == 32) {
            mostSigBits = parseHex(hex.substring(0, 16));
            leastSigBits = parseHex(hex.substring(16));
        }
    }

    private static long parseHex(String s) {
        long result = 0;
        for (int i = 0; i < s.length(); ++i) {
            result = (result << 4) | Character.digit(s.charAt(i), 16);
        }
        return result;
    }

    private static String format(long msb, long lsb) {
        String hi = toHex(msb);
        String lo = toHex(lsb);
        return hi.substring(0, 8) + "-" + hi.substring(8, 12) + "-" + hi.substring(12, 16) + "-"
                + lo.substring(0, 4) + "-" + lo.substring(4, 16);
    }

    private static String toHex(long value) {
        String s = Long.toHexString(value);
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < 16; ++i) {
            sb.append('0');
        }
        return sb.append(s).toString();
    }

    public long getMostSignificantBits() {
        return mostSigBits;
    }

    public long getLeastSignificantBits() {
        return leastSigBits;
    }

    public int version() {
        return (int) (mostSigBits >> 12) & 0xf;
    }

    @Override
    public int compareTo(TUUID other) {
        if (mostSigBits != other.mostSigBits) {
            return mostSigBits < other.mostSigBits ? -1 : 1;
        }
        long a = leastSigBits + Long.MIN_VALUE;
        long b = other.leastSigBits + Long.MIN_VALUE;
        return a < b ? -1 : a == b ? 0 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TUUID) {
            TUUID other = (TUUID) o;
            return value.equals(other.value);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }

    public static TUUID randomUUID() {
        if (PlatformDetector.isJavaScript() && Crypto.isSupported()) {
            return new TUUID(Crypto.current().randomUUID());
        } else {
            String value = s4() + s4() + "-" + s4() + "-" + s4() + "-" + s4() + "-" + s4() + s4() + s4();
            return new TUUID(value);
        }
    }

    private static String s4() {
        return Integer.toString((int) Math.floor((1 + Math.random()) * 65536), 16).substring(1);
    }

    public static TUUID fromString(String value) {
        return new TUUID(value);
    }
}
