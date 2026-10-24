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
package org.teavm.classlib.java.nio.channels;

import java.io.IOException;
import java.nio.channels.FileChannel;
import org.teavm.classlib.java.io.TCloseable;

/**
 * Minimal {@code java.nio.channels.FileLock} emulation. Browser builds are single-process,
 * so real file locking is never required, but the type must exist for linking.
 *
 * <p>Like the JDK, the channel/position/size/shared state is stored here; subclasses only have to
 * implement {@link #isValid()} and {@link #release()}.</p>
 */
public abstract class TFileLock implements TCloseable {
    private final FileChannel channel;
    private final long position;
    private final long size;
    private final boolean shared;

    protected TFileLock(FileChannel channel, long position, long size, boolean shared) {
        this.channel = channel;
        this.position = position;
        this.size = size;
        this.shared = shared;
    }

    public final FileChannel channel() {
        return channel;
    }

    public final boolean isShared() {
        return shared;
    }

    public final long position() {
        return position;
    }

    public final long size() {
        return size;
    }

    public final boolean overlaps(long position, long size) {
        long thisPosition = position();
        long thisEnd = thisPosition + size();
        long otherEnd = position + size;
        return thisPosition < otherEnd && position < thisEnd;
    }

    public abstract boolean isValid();

    public abstract void release() throws IOException;

    @Override
    public void close() throws IOException {
        release();
    }
}
