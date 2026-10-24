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
import org.teavm.classlib.java.util.TDeque;
import org.teavm.classlib.java.util.TIterator;

public class TConcurrentLinkedDeque<E> extends TAbstractCollection<E> implements TDeque<E> {
    private final ArrayList<E> list = new ArrayList<>();

    public TConcurrentLinkedDeque() {
    }

    @Override
    public boolean offer(E e) {
        return list.add(e);
    }

    @Override
    public boolean add(E e) {
        return list.add(e);
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
    public boolean offerFirst(E e) {
        list.add(0, e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        return list.add(e);
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        return list.isEmpty() ? null : list.remove(list.size() - 1);
    }

    @Override
    public E peekFirst() {
        return peek();
    }

    @Override
    public E peekLast() {
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        int index = list.lastIndexOf(o);
        if (index < 0) {
            return false;
        }
        list.remove(index);
        return true;
    }

    @Override
    public void push(E e) {
        offerFirst(e);
    }

    @Override
    public E pop() {
        E e = pollFirst();
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public TIterator<E> descendingIterator() {
        return new TIterator<>() {
            private int index = list.size();

            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public E next() {
                if (index <= 0) {
                    throw new NoSuchElementException();
                }
                return list.get(--index);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public void addFirst(E e) {
        list.add(0, e);
    }

    @Override
    public void addLast(E e) {
        list.add(e);
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        E e = peekLast();
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E removeFirst() {
        return remove();
    }

    @Override
    public E removeLast() {
        E e = pollLast();
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
