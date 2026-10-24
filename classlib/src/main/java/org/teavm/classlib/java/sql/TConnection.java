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

public interface TConnection extends TWrapper, AutoCloseable {
    int TRANSACTION_NONE = 0;
    int TRANSACTION_READ_UNCOMMITTED = 1;
    int TRANSACTION_READ_COMMITTED = 2;
    int TRANSACTION_REPEATABLE_READ = 4;
    int TRANSACTION_SERIALIZABLE = 8;

    TStatement createStatement() throws TSQLException;

    TPreparedStatement prepareStatement(String sql) throws TSQLException;

    TDatabaseMetaData getMetaData() throws TSQLException;

    TArray createArrayOf(String typeName, Object[] elements) throws TSQLException;

    boolean getAutoCommit() throws TSQLException;

    void setAutoCommit(boolean autoCommit) throws TSQLException;

    void commit() throws TSQLException;

    void rollback() throws TSQLException;

    void close() throws TSQLException;

    boolean isClosed() throws TSQLException;

    boolean isReadOnly() throws TSQLException;

    void setReadOnly(boolean readOnly) throws TSQLException;

    String getCatalog() throws TSQLException;

    void setCatalog(String catalog) throws TSQLException;

    int getTransactionIsolation() throws TSQLException;

    void setTransactionIsolation(int level) throws TSQLException;

    TSQLWarning getWarnings() throws TSQLException;

    void clearWarnings() throws TSQLException;

    TStatement createStatement(int resultSetType, int resultSetConcurrency) throws TSQLException;

    boolean isValid(int timeout) throws TSQLException;

    int getHoldability() throws TSQLException;

    void setHoldability(int holdability) throws TSQLException;

    void setSchema(String schema) throws TSQLException;

    String getSchema() throws TSQLException;

    void abort(Runnable executor) throws TSQLException;
}
