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
package org.teavm.classlib.java.sql;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TSQLException extends Exception implements Iterable<Throwable> {
    private static final long serialVersionUID = 1L;

    private final String sqlState;
    private final int vendorCode;
    private TSQLException next;

    public TSQLException() {
        this(null, null, 0);
    }

    public TSQLException(String reason) {
        this(reason, null, 0);
    }

    public TSQLException(String reason, String sqlState) {
        this(reason, sqlState, 0);
    }

    public TSQLException(String reason, String sqlState, int vendorCode) {
        super(reason);
        this.sqlState = sqlState;
        this.vendorCode = vendorCode;
    }

    public TSQLException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, cause);
        this.sqlState = sqlState;
        this.vendorCode = vendorCode;
    }

    public TSQLException(Throwable cause) {
        super(cause);
        this.sqlState = null;
        this.vendorCode = 0;
    }

    public TSQLException(String reason, Throwable cause) {
        super(reason, cause);
        this.sqlState = null;
        this.vendorCode = 0;
    }

    public String getSQLState() {
        return sqlState;
    }

    public int getErrorCode() {
        return vendorCode;
    }

    public TSQLException getNextException() {
        return next;
    }

    public void setNextException(TSQLException ex) {
        if (next != null) {
            next.setNextException(ex);
        } else {
            next = ex;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append(": ").append(getMessage());
        if (sqlState != null) {
            sb.append("; SQLState: ").append(sqlState);
        }
        sb.append("; error code: ").append(vendorCode);
        return sb.toString();
    }

    @Override
    public Iterator<Throwable> iterator() {
        return new Iterator<>() {
            private TSQLException current = TSQLException.this;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Throwable next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                TSQLException result = current;
                current = current.getNextException();
                return result;
            }
        };
    }
}
