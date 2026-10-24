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
package org.teavm.classlib.java.sql;

import java.util.Calendar;

public class TDate extends org.teavm.classlib.java.util.TDate {
    private static final long serialVersionUID = 1L;

    public TDate(int year, int month, int day) {
        super(year - 1900, month - 1, day);
    }

    public TDate(long date) {
        super(date);
    }

    public static TDate valueOf(String s) {
        int firstDash = s.indexOf('-');
        int secondDash = s.indexOf('-', firstDash + 1);
        if (firstDash < 0 || secondDash < 0) {
            throw new IllegalArgumentException(s);
        }
        int year = Integer.parseInt(s.substring(0, firstDash));
        int month = Integer.parseInt(s.substring(firstDash + 1, secondDash));
        int day = Integer.parseInt(s.substring(secondDash + 1));
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(year, month - 1, day);
        return new TDate(c.getTimeInMillis());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getTime());
        pad(sb, c.get(Calendar.YEAR), 4).append('-');
        pad(sb, c.get(Calendar.MONTH) + 1, 2).append('-');
        pad(sb, c.get(Calendar.DAY_OF_MONTH), 2);
        return sb.toString();
    }

    static StringBuilder pad(StringBuilder sb, int value, int width) {
        String s = Integer.toString(value);
        for (int i = s.length(); i < width; ++i) {
            sb.append('0');
        }
        return sb.append(s);
    }
}
