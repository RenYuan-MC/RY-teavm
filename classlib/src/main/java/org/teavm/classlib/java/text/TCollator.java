/*
 *  Copyright 2024 RenYuan.
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
package org.teavm.classlib.java.text;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;

public abstract class TCollator {
    public static final int PRIMARY = 0;
    public static final int SECONDARY = 1;
    public static final int TERTIARY = 2;
    public static final int IDENTICAL = 3;

    public static final int NO_DECOMPOSITION = 0;
    public static final int CANONICAL_DECOMPOSITION = 1;

    private int strength = TERTIARY;
    private int decomposition = NO_DECOMPOSITION;

    public abstract int compare(String source, String target);

    public abstract CollationKey getCollationKey(String source);

    public int getStrength() {
        return strength;
    }

    public void setStrength(int newStrength) {
        if (newStrength < PRIMARY || newStrength > IDENTICAL) {
            throw new IllegalArgumentException("Strength out of range: " + newStrength);
        }
        strength = newStrength;
    }

    public int getDecomposition() {
        return decomposition;
    }

    public void setDecomposition(int decompositionMode) {
        decomposition = decompositionMode;
    }

    public static Collator getInstance() {
        return getInstance(Locale.getDefault());
    }

    public static Collator getInstance(Locale desiredLocale) {
        return new TDefaultCollator();
    }

    public static Locale[] getAvailableLocales() {
        return new Locale[] { Locale.ROOT, Locale.ENGLISH };
    }

    private static final class TDefaultCollator extends Collator {
        @Override
        public int compare(String source, String target) {
            if (getStrength() <= SECONDARY) {
                return source.toUpperCase(Locale.ROOT).compareTo(target.toUpperCase(Locale.ROOT));
            }
            return source.compareTo(target);
        }

        @Override
        public CollationKey getCollationKey(String source) {
            return new TDefaultCollationKey(source, getStrength() <= SECONDARY);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
