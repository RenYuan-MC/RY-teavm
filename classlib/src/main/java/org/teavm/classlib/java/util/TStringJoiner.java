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
package org.teavm.classlib.java.util;

public class TStringJoiner {
    private final String prefix;
    private final String delimiter;
    private final String suffix;
    private final StringBuilder builder = new StringBuilder();
    private boolean empty = true;

    public TStringJoiner(CharSequence delimiter) {
        this(delimiter, "", "");
    }

    public TStringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        this.delimiter = delimiter.toString();
        this.prefix = prefix.toString();
        this.suffix = suffix.toString();
    }

    public TStringJoiner add(CharSequence newElement) {
        if (empty) {
            builder.append(prefix);
            empty = false;
        } else {
            builder.append(delimiter);
        }
        builder.append(newElement);
        return this;
    }

    public TStringJoiner merge(TStringJoiner other) {
        if (!other.empty) {
            add(other.toString());
        }
        return this;
    }

    public int length() {
        return builder.length() + (empty ? prefix.length() + suffix.length() : suffix.length());
    }

    public TStringJoiner setEmptyValue(CharSequence emptyValue) {
        return this;
    }

    @Override
    public String toString() {
        if (empty) {
            return prefix + suffix;
        }
        return builder.toString() + suffix;
    }
}
