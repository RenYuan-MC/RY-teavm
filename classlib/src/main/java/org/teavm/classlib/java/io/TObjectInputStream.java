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
package org.teavm.classlib.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamClass;

/**
 * Minimal stand-in for {@code java.io.ObjectInputStream}. Java serialization is not available
 * in the TeaVM runtime, so {@link #readObject()} always throws.
 */
public class TObjectInputStream extends InputStream {
    protected InputStream in;

    public TObjectInputStream(InputStream in) throws IOException {
        this.in = in;
    }

    public final Object readObject() throws IOException, ClassNotFoundException {
        throw new IOException("Java serialization is not supported in this runtime");
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    public int readInt() throws IOException {
        int a = in.read();
        int b = in.read();
        int c = in.read();
        int d = in.read();
        if ((a | b | c | d) < 0) {
            throw new IOException("Unexpected end of stream");
        }
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    public boolean readBoolean() throws IOException {
        int v = in.read();
        if (v < 0) {
            throw new IOException("Unexpected end of stream");
        }
        return v != 0;
    }

    public byte readByte() throws IOException {
        int v = in.read();
        if (v < 0) {
            throw new IOException("Unexpected end of stream");
        }
        return (byte) v;
    }

    public long readLong() throws IOException {
        return ((long) readInt() << 32) | (readInt() & 0xffffffffL);
    }

    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        return Object.class;
    }

    protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
        return Object.class;
    }

    public void defaultReadObject() throws IOException {
    }

    public ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        return null;
    }
}
