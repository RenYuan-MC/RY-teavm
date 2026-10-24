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

import java.io.IOException;

/**
 * Server sockets are not available in the browser runtime.
 */
public class TServerSocket {
    public TServerSocket() {
    }

    public TServerSocket(int port) throws IOException {
        throw new TBindException("Network is not available in this runtime");
    }

    public TServerSocket(int port, int backlog) throws IOException {
        throw new TBindException("Network is not available in this runtime");
    }

    public TServerSocket(int port, int backlog, java.net.InetAddress bindAddr) throws IOException {
        throw new TBindException("Network is not available in this runtime");
    }

    public TSocket accept() throws IOException {
        throw new TSocketException("Network is not available in this runtime");
    }

    public void close() throws IOException {
    }

    public boolean isClosed() {
        return true;
    }

    public int getLocalPort() {
        return 0;
    }

    public void setReuseAddress(boolean on) throws IOException {
    }

    public TInetAddress getInetAddress() {
        return TInetAddress.getLoopbackAddress();
    }
}
