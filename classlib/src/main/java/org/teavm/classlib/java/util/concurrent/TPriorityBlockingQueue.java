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

import java.util.Comparator;

/**
 * Insertion-ordered stand-in for {@link java.util.concurrent.PriorityBlockingQueue}.
 * Priority ordering is not preserved; the browser runtime executes tasks inline.
 */
public class TPriorityBlockingQueue<E> extends TSimpleBlockingQueue<E> {
    private static final int DEFAULT_CAPACITY = 8192;

    public TPriorityBlockingQueue() {
        super(DEFAULT_CAPACITY);
    }

    public TPriorityBlockingQueue(int initialCapacity) {
        super(Math.max(1, initialCapacity));
    }

    public TPriorityBlockingQueue(int initialCapacity, Comparator<? super E> comparator) {
        super(Math.max(1, initialCapacity));
    }

    public Comparator<? super E> comparator() {
        return null;
    }
}
