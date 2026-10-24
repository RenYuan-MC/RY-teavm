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

public interface TStatement extends TWrapper, AutoCloseable {
    int CLOSE_CURRENT_RESULT = 1;
    int KEEP_CURRENT_RESULT = 2;
    int CLOSE_ALL_RESULTS = 3;
    int SUCCESS_NO_INFO = -2;
    int EXECUTE_FAILED = -3;
    int RETURN_GENERATED_KEYS = 1;
    int NO_GENERATED_KEYS = 2;

    TResultSet executeQuery(String sql) throws TSQLException;

    boolean execute(String sql) throws TSQLException;

    int executeUpdate(String sql) throws TSQLException;

    void close() throws TSQLException;

    boolean isClosed() throws TSQLException;

    int getMaxFieldSize() throws TSQLException;

    void setMaxFieldSize(int max) throws TSQLException;

    int getMaxRows() throws TSQLException;

    void setMaxRows(int max) throws TSQLException;

    boolean getMoreResults() throws TSQLException;

    int getUpdateCount() throws TSQLException;

    void cancel() throws TSQLException;

    TSQLWarning getWarnings() throws TSQLException;

    void clearWarnings() throws TSQLException;

    TConnection getConnection() throws TSQLException;

    TResultSet getResultSet() throws TSQLException;

    int getQueryTimeout() throws TSQLException;

    void setQueryTimeout(int seconds) throws TSQLException;
}
