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

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class TDriverManager {
    private TDriverManager() {
    }

    public static TConnection getConnection(String url) throws TSQLException {
        return getConnection(url, new Properties());
    }

    public static TConnection getConnection(String url, String user, String password) throws TSQLException {
        Properties info = new Properties();
        if (user != null) {
            info.put("user", user);
        }
        if (password != null) {
            info.put("password", password);
        }
        return getConnection(url, info);
    }

    public static TConnection getConnection(String url, Properties info) throws TSQLException {
        throw new TSQLException("No suitable driver found for " + url);
    }

    public static TDriver getDriver(String url) {
        return null;
    }

    public static void registerDriver(TDriver driver) throws TSQLException {
    }

    public static void deregisterDriver(TDriver driver) throws TSQLException {
    }

    public static Enumeration<TDriver> getDrivers() {
        return null;
    }

    public static int getLoginTimeout() {
        return 0;
    }

    public static void setLoginTimeout(int seconds) {
    }

    public static PrintWriter getLogWriter() {
        return null;
    }

    public static void setLogWriter(PrintWriter out) {
    }
}
