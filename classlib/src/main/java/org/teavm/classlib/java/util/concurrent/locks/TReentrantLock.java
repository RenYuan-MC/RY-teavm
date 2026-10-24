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
package org.teavm.classlib.java.util.concurrent.locks;

import java.util.concurrent.TimeUnit;

/**
 * Reentrant lock for the single-threaded TeaVM runtime. Blocking is impossible, so
 * {@link #lock()} only records ownership; it never waits.
 */
public class TReentrantLock {
    private Thread owner;
    private int holdCount;

    public TReentrantLock() {
    }

    public TReentrantLock(boolean fair) {
    }

    public void lock() {
        Thread current = Thread.currentThread();
        if (owner == current) {
            ++holdCount;
        } else {
            owner = current;
            holdCount = 1;
        }
    }

    public void lockInterruptibly() throws InterruptedException {
        lock();
    }

    public boolean tryLock() {
        Thread current = Thread.currentThread();
        if (owner == null || owner == current) {
            lock();
            return true;
        }
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) {
        return tryLock();
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (owner != current) {
            throw new IllegalMonitorStateException();
        }
        if (--holdCount == 0) {
            owner = null;
        }
    }

    public int getHoldCount() {
        return holdCount;
    }

    public boolean isHeldByCurrentThread() {
        return owner == Thread.currentThread();
    }

    public boolean isLocked() {
        return owner != null;
    }

    public final boolean isFair() {
        return false;
    }

    @Override
    public String toString() {
        return "ReentrantLock[Locked by " + (owner != null ? owner.getName() : "no thread") + "]";
    }
}
