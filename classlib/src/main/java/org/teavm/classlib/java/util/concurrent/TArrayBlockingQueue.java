/*
 *  Copyright 2014 Alexey Andreev.
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

public class TArrayBlockingQueue<E> extends TSimpleBlockingQueue<E> {
    public TArrayBlockingQueue(int capacity) {
        super(Math.max(1, capacity));
    }

    public TArrayBlockingQueue(int capacity, boolean fair) {
        super(Math.max(1, capacity));
    }

    public TArrayBlockingQueue(int capacity, boolean fair, java.util.Collection<? extends E> c) {
        super(Math.max(1, capacity));
        if (capacity < 1 || capacity < c.size()) {
            throw new IllegalArgumentException("Capacity must be at least 1 or collection's size");
        }
        for (E e : c) {
            add(e);
        }
    }
}
