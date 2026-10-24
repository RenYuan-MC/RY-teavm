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

/**
 * Thrown when a file region would overlap a lock already held by this JVM. Browser builds are
 * single-process and never take real file locks, so this type only has to exist for linking.
 */
public class TOverlappingFileLockException extends IllegalStateException {
    private static final long serialVersionUID = 1L;

    public TOverlappingFileLockException() {
    }

    public TOverlappingFileLockException(String message) {
        super(message);
    }
}
