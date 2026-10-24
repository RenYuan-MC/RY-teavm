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
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Network sockets are not available in the browser runtime. Every operation fails.
 */
public class TSocket {
    public TSocket() {
    }

    public TSocket(String host, int port) throws IOException {
        throw new TSocketException("Network is not available in this runtime");
    }

    public TSocket(TInetAddress address, int port) throws IOException {
        throw new TSocketException("Network is not available in this runtime");
    }

    public InputStream getInputStream() throws IOException {
        throw new TSocketException("Network is not available in this runtime");
    }

    public OutputStream getOutputStream() throws IOException {
        throw new TSocketException("Network is not available in this runtime");
    }

    public void connect(java.net.SocketAddress endpoint) throws IOException {
        throw new TSocketException("Network is not available in this runtime");
    }

    public void close() throws IOException {
    }

    public boolean isClosed() {
        return true;
    }

    public int getPort() {
        return 0;
    }

    public int getLocalPort() {
        return 0;
    }

    public TInetAddress getInetAddress() {
        return TInetAddress.getLoopbackAddress();
    }

    public TInetAddress getLocalAddress() {
        return TInetAddress.getLoopbackAddress();
    }

    public void setSoTimeout(int timeout) throws IOException {
    }

    public int getSoTimeout() throws IOException {
        return 0;
    }

    public void setTcpNoDelay(boolean on) throws IOException {
    }

    public boolean isConnected() {
        return false;
    }
}
