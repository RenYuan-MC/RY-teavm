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
package org.teavm.classlib.java.lang;

public class TExceptionInInitializerError extends LinkageError {
    private static final long serialVersionUID = 1L;

    private final Throwable exception;

    public TExceptionInInitializerError() {
        super();
        exception = null;
    }

    public TExceptionInInitializerError(Throwable thrown) {
        super("Exception in initializer", thrown);
        exception = thrown;
    }

    public TExceptionInInitializerError(String s) {
        super(s);
        exception = null;
    }

    public Throwable getException() {
        return exception;
    }
}
