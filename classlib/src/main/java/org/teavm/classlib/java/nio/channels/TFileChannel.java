/*
 *  Copyright 2026 RenYuan MC.
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
package org.teavm.classlib.java.nio.channels;

import java.io.IOException;
import org.teavm.classlib.java.nio.TByteBuffer;
import org.teavm.classlib.java.nio.TMappedByteBuffer;

/**
 * Minimal {@code java.nio.channels.FileChannel} emulation.
 *
 * <p>The real file providers are expected to subclass this class and implement the abstract methods,
 * exactly like H2's {@code org.h2.store.fs.FileBase} does.</p>
 */
public abstract class TFileChannel implements TSeekableByteChannel {
    private boolean open = true;

    protected TFileChannel() {
    }

    public abstract int read(TByteBuffer dst) throws IOException;

    public abstract int read(TByteBuffer dst, long position) throws IOException;

    public abstract int write(TByteBuffer src, long position) throws IOException;

    public abstract void force(boolean metaData) throws IOException;

    public abstract long read(TByteBuffer[] dsts, int offset, int length) throws IOException;

    public final long read(TByteBuffer[] dsts) throws IOException {
        return read(dsts, 0, dsts.length);
    }

    public abstract int write(TByteBuffer src) throws IOException;

    public abstract long write(TByteBuffer[] srcs, int offset, int length) throws IOException;

    public final long write(TByteBuffer[] srcs) throws IOException {
        return write(srcs, 0, srcs.length);
    }

    @Override
    public TFileChannel position(long newPosition) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public TFileChannel truncate(long size) throws IOException {
        throw new UnsupportedOperationException();
    }

    public abstract long transferTo(long position, long count, TWritableByteChannel target)
            throws IOException;

    public abstract long transferFrom(TReadableByteChannel src, long position, long count)
            throws IOException;

    public abstract TFileLock lock(long position, long size, boolean shared) throws IOException;

    public final TFileLock lock() throws IOException {
        return lock(0L, Long.MAX_VALUE, false);
    }

    public abstract TFileLock tryLock(long position, long size, boolean shared) throws IOException;

    public final TFileLock tryLock() throws IOException {
        return tryLock(0L, Long.MAX_VALUE, false);
    }

    public abstract TMappedByteBuffer map(MapMode mode, long position, long size) throws IOException;

    @Override
    public final boolean isOpen() {
        return open;
    }

    @Override
    public final void close() throws IOException {
        if (open) {
            open = false;
            implCloseChannel();
        }
    }

    protected void implCloseChannel() throws IOException {
    }

    public static class MapMode {
        public static final MapMode READ_ONLY = new MapMode("READ_ONLY");
        public static final MapMode READ_WRITE = new MapMode("READ_WRITE");
        public static final MapMode PRIVATE = new MapMode("PRIVATE");

        private final String name;

        private MapMode(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
