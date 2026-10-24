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
package org.teavm.classlib.java.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TFutureTask<V> implements Future<V>, Runnable {
    private Callable<V> callable;
    private V result;
    private Throwable failure;
    private boolean done;
    private boolean cancelled;

    public TFutureTask(Callable<V> callable) {
        this.callable = callable;
    }

    public TFutureTask(Runnable runnable, V result) {
        this.callable = () -> {
            runnable.run();
            return result;
        };
    }

    @Override
    public void run() {
        if (done) {
            return;
        }
        try {
            result = callable.call();
        } catch (Throwable e) {
            failure = e;
        } finally {
            done = true;
            callable = null;
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (done) {
            return false;
        }
        cancelled = true;
        done = true;
        callable = null;
        return true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        if (!done) {
            run();
        }
        if (failure != null) {
            throw new ExecutionException(failure);
        }
        return result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException {
        return get();
    }
}
