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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Single-threaded, run-inline thread pool for the TeaVM runtime. Tasks are executed
 * immediately on the calling thread instead of being handed to a worker thread.
 */
public class TThreadPoolExecutor {
    private boolean shutdown;

    public TThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
    }

    public TThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
    }

    public void execute(Runnable command) {
        if (shutdown) {
            throw new RejectedExecutionException("Executor has been shut down");
        }
        command.run();
    }

    public Future<?> submit(Runnable task) {
        TFutureTask<Void> future = new TFutureTask<>(task, null);
        execute(future);
        return future;
    }

    public <T> Future<T> submit(Callable<T> task) {
        TFutureTask<T> future = new TFutureTask<>(task);
        execute(future);
        return future;
    }

    public void shutdown() {
        shutdown = true;
    }

    public List<Runnable> shutdownNow() {
        shutdown = true;
        return new ArrayList<>();
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public boolean isTerminated() {
        return shutdown;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) {
        return true;
    }

    public int getPoolSize() {
        return 1;
    }

    public int getActiveCount() {
        return 0;
    }

    public int getCorePoolSize() {
        return 1;
    }

    public BlockingQueue<Runnable> getQueue() {
        return null;
    }

    public int getMaximumPoolSize() {
        return 1;
    }
}
