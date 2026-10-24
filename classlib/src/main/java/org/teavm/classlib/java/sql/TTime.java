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

public class TTime extends org.teavm.classlib.java.util.TDate {
    private static final long serialVersionUID = 1L;

    public TTime(int hour, int minute, int second) {
        super(70, 0, 1, hour, minute, second);
    }

    public TTime(long time) {
        super(time);
    }

    public static TTime valueOf(String s) {
        int firstColon = s.indexOf(':');
        int secondColon = s.indexOf(':', firstColon + 1);
        if (firstColon < 0 || secondColon < 0) {
            throw new IllegalArgumentException(s);
        }
        int hour = Integer.parseInt(s.substring(0, firstColon));
        int minute = Integer.parseInt(s.substring(firstColon + 1, secondColon));
        int second = Integer.parseInt(s.substring(secondColon + 1));
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(1970, 0, 1, hour, minute, second);
        return new TTime(c.getTimeInMillis());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getTime());
        TDate.pad(sb, c.get(Calendar.HOUR_OF_DAY), 2).append(':');
        TDate.pad(sb, c.get(Calendar.MINUTE), 2).append(':');
        TDate.pad(sb, c.get(Calendar.SECOND), 2);
        return sb.toString();
    }
}
