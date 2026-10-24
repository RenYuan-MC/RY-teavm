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
import java.util.NoSuchElementException;
import org.teavm.classlib.java.util.TAbstractCollection;
import org.teavm.classlib.java.util.TCollection;
import org.teavm.classlib.java.util.TIterator;

/**
 * Simple non-blocking queue used by the single-threaded browser runtime. All "blocking"
 * methods return immediately instead of waiting.
 */
abstract class TSimpleBlockingQueue<E> extends TAbstractCollection<E> implements TBlockingQueue<E> {
    private final ArrayList<E> list = new ArrayList<>();
    private final int capacity;

    TSimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean add(E e) {
        if (list.size() >= capacity) {
            throw new IllegalStateException("Queue is full");
        }
        return list.add(e);
    }

    @Override
    public boolean offer(E e) {
        if (list.size() >= capacity) {
            return false;
        }
        return list.add(e);
    }

    @Override
    public void put(E e) {
        if (!offer(e)) {
            throw new IllegalStateException("Queue is full");
        }
    }

    @Override
    public boolean offer(E e, long timeout, TTimeUnit unit) {
        return offer(e);
    }

    @Override
    public E take() {
        return poll();
    }

    @Override
    public E poll(long timeout, TTimeUnit unit) {
        return poll();
    }

    @Override
    public int remainingCapacity() {
        return capacity - list.size();
    }

    @Override
    public int drainTo(TCollection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    @Override
    public int drainTo(TCollection<? super E> c, int maxElements) {
        int count = 0;
        while (count < maxElements && !list.isEmpty()) {
            c.add(list.remove(0));
            ++count;
        }
        return count;
    }

    @Override
    public E poll() {
        return list.isEmpty() ? null : list.remove(0);
    }

    @Override
    public E peek() {
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public E element() {
        E e = peek();
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E remove() {
        E e = poll();
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public TIterator<E> iterator() {
        return new TIterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < list.size();
            }

            @Override
            public E next() {
                if (index >= list.size()) {
                    throw new NoSuchElementException();
                }
                return list.get(index++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
