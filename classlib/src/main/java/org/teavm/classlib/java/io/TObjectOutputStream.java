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
import java.io.ObjectStreamClass;
import java.io.OutputStream;

/**
 * Minimal stand-in for {@code java.io.ObjectOutputStream}. Java serialization is not available
 * in the TeaVM runtime, so {@link #writeObject(Object)} always throws.
 */
public class TObjectOutputStream extends OutputStream {
    protected OutputStream out;

    public TObjectOutputStream(OutputStream out) throws IOException {
        this.out = out;
    }

    public void writeObject(Object obj) throws IOException {
        throw new IOException("Java serialization is not supported in this runtime");
    }

    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    public void writeBoolean(boolean val) throws IOException {
        out.write(val ? 1 : 0);
    }

    public void writeByte(int val) throws IOException {
        out.write(val);
    }

    public void writeShort(int val) throws IOException {
        out.write(val >>> 8);
        out.write(val);
    }

    public void writeInt(int val) throws IOException {
        out.write(val >>> 24);
        out.write(val >>> 16);
        out.write(val >>> 8);
        out.write(val);
    }

    public void writeLong(long val) throws IOException {
        writeInt((int) (val >>> 32));
        writeInt((int) val);
    }

    public void writeFloat(float val) throws IOException {
        writeInt(Float.floatToIntBits(val));
    }

    public void writeDouble(double val) throws IOException {
        writeLong(Double.doubleToLongBits(val));
    }

    public void writeChars(String s) throws IOException {
        throw new IOException("Java serialization is not supported in this runtime");
    }

    public void writeUTF(String s) throws IOException {
        throw new IOException("Java serialization is not supported in this runtime");
    }

    public void defaultWriteObject() throws IOException {
    }

    public void reset() throws IOException {
    }
}
