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

public class TTimestamp extends org.teavm.classlib.java.util.TDate {
    private static final long serialVersionUID = 1L;

    private int nanos;

    public TTimestamp(int year, int month, int date, int hour, int minute, int second, int nano) {
        super(year - 1900, month - 1, date, hour, minute, second);
        setNanos(nano);
    }

    public TTimestamp(long time) {
        super(time);
    }

    public int getNanos() {
        return nanos;
    }

    public void setNanos(int n) {
        if (n > 999999999 || n < 0) {
            throw new IllegalArgumentException("nanos out of range: " + n);
        }
        nanos = n;
    }

    public static TTimestamp valueOf(String s) {
        String datePart;
        String timePart;
        int space = s.indexOf(' ');
        if (space < 0) {
            datePart = s;
            timePart = "00:00:00";
        } else {
            datePart = s.substring(0, space);
            timePart = s.substring(space + 1);
        }
        int firstDash = datePart.indexOf('-');
        int secondDash = datePart.indexOf('-', firstDash + 1);
        int year = Integer.parseInt(datePart.substring(0, firstDash));
        int month = Integer.parseInt(datePart.substring(firstDash + 1, secondDash));
        int day = Integer.parseInt(datePart.substring(secondDash + 1));
        int dot = timePart.indexOf('.');
        int nano = 0;
        String main = timePart;
        if (dot >= 0) {
            main = timePart.substring(0, dot);
            String frac = timePart.substring(dot + 1);
            StringBuilder ns = new StringBuilder(frac);
            while (ns.length() < 9) {
                ns.append('0');
            }
            nano = Integer.parseInt(ns.substring(0, 9));
        }
        int firstColon = main.indexOf(':');
        int secondColon = main.indexOf(':', firstColon + 1);
        int hour = Integer.parseInt(main.substring(0, firstColon));
        int minute = Integer.parseInt(main.substring(firstColon + 1, secondColon));
        int second = Integer.parseInt(main.substring(secondColon + 1));
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(year, month - 1, day, hour, minute, second);
        TTimestamp ts = new TTimestamp(c.getTimeInMillis());
        ts.setNanos(nano);
        return ts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getTime());
        TDate.pad(sb, c.get(Calendar.YEAR), 4).append('-');
        TDate.pad(sb, c.get(Calendar.MONTH) + 1, 2).append('-');
        TDate.pad(sb, c.get(Calendar.DAY_OF_MONTH), 2).append(' ');
        TDate.pad(sb, c.get(Calendar.HOUR_OF_DAY), 2).append(':');
        TDate.pad(sb, c.get(Calendar.MINUTE), 2).append(':');
        TDate.pad(sb, c.get(Calendar.SECOND), 2);
        if (nanos != 0) {
            String ns = Integer.toString(nanos);
            for (int i = ns.length(); i < 9; ++i) {
                sb.append('0');
            }
            sb.append(ns);
        }
        return sb.toString();
    }
}
