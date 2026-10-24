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
import java.util.Locale;

public class TDefaultCollationKey extends CollationKey {
    private final String key;
    private final byte[] bytes;

    TDefaultCollationKey(String source, boolean ignoreCase) {
        super(source);
        key = ignoreCase ? source.toUpperCase(Locale.ROOT) : source;
        bytes = toBytes(key);
    }

    private static byte[] toBytes(String s) {
        byte[] result = new byte[s.length() * 2];
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            result[i * 2] = (byte) (c >>> 8);
            result[i * 2 + 1] = (byte) c;
        }
        return result;
    }

    @Override
    public int compareTo(CollationKey target) {
        if (target instanceof TDefaultCollationKey) {
            return key.compareTo(((TDefaultCollationKey) target).key);
        }
        return key.compareTo(target.getSourceString());
    }

    @Override
    public byte[] toByteArray() {
        return bytes.clone();
    }
}
