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
package org.teavm.classlib.java.net;

import java.net.UnknownHostException;

public class TInetAddress {
    private final String host;
    private final String address;

    TInetAddress(String host, String address) {
        this.host = host;
        this.address = address;
    }

    public static TInetAddress getLoopbackAddress() {
        return new TInetAddress("localhost", "127.0.0.1");
    }

    public static TInetAddress getLocalHost() throws UnknownHostException {
        return new TInetAddress("localhost", "127.0.0.1");
    }

    public static TInetAddress getByName(String host) throws UnknownHostException {
        if (host == null || host.isEmpty()) {
            return getLoopbackAddress();
        }
        return new TInetAddress(host, host);
    }

    public static TInetAddress[] getAllByName(String host) throws UnknownHostException {
        return new TInetAddress[] { getByName(host) };
    }

    public static TInetAddress getByAddress(String host, byte[] addr) throws UnknownHostException {
        return new TInetAddress(host, host);
    }

    public String getHostAddress() {
        return address;
    }

    public String getHostName() {
        return host;
    }

    public String getCanonicalHostName() {
        return host;
    }

    public boolean isLoopbackAddress() {
        return "127.0.0.1".equals(address);
    }

    public boolean isAnyLocalAddress() {
        return "0.0.0.0".equals(address);
    }

    @Override
    public String toString() {
        return host + "/" + address;
    }
}
