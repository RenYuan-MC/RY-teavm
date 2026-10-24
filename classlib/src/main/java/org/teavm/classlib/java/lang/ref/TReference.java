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
package org.teavm.classlib.java.lang.ref;

import java.lang.ref.ReferenceQueue;
import org.teavm.classlib.java.lang.TObject;

/**
 * Base class for soft/weak/phantom references.
 *
 * <p>Keep this class free of instance fields: the wasmGC backend stores the native reference in a
 * fixed field slot of the concrete reference structure (see WeakReferenceGenerator), so adding
 * fields here shifts that slot and produces invalid reference casts.</p>
 */
public abstract class TReference<T> extends TObject {
    protected TReference() {
    }

    protected TReference(T referent) {
    }

    protected TReference(T referent, ReferenceQueue<? super T> queue) {
    }

    public T get() {
        return null;
    }

    public void clear() {
    }

    public boolean isEnqueued() {
        return false;
    }

    public boolean enqueue() {
        return false;
    }
}
