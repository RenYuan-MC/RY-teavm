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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public interface TResultSet extends TWrapper, AutoCloseable {
    int FETCH_FORWARD = 1000;
    int FETCH_REVERSE = 1001;
    int FETCH_UNKNOWN = 1002;
    int TYPE_FORWARD_ONLY = 1003;
    int TYPE_SCROLL_INSENSITIVE = 1004;
    int TYPE_SCROLL_SENSITIVE = 1005;
    int CONCUR_READ_ONLY = 1007;
    int CONCUR_UPDATABLE = 1008;
    int HOLD_CURSORS_OVER_COMMIT = 1;
    int CLOSE_CURSORS_AT_COMMIT = 2;

    boolean next() throws TSQLException;

    void close() throws TSQLException;

    boolean wasNull() throws TSQLException;

    String getString(int columnIndex) throws TSQLException;

    String getString(String columnLabel) throws TSQLException;

    boolean getBoolean(int columnIndex) throws TSQLException;

    byte getByte(int columnIndex) throws TSQLException;

    short getShort(int columnIndex) throws TSQLException;

    int getInt(int columnIndex) throws TSQLException;

    int getInt(String columnLabel) throws TSQLException;

    long getLong(int columnIndex) throws TSQLException;

    float getFloat(int columnIndex) throws TSQLException;

    double getDouble(int columnIndex) throws TSQLException;

    BigDecimal getBigDecimal(int columnIndex) throws TSQLException;

    byte[] getBytes(int columnIndex) throws TSQLException;

    Date getDate(int columnIndex) throws TSQLException;

    Time getTime(int columnIndex) throws TSQLException;

    Timestamp getTimestamp(int columnIndex) throws TSQLException;

    TArray getArray(int columnIndex) throws TSQLException;

    InputStream getBinaryStream(int columnIndex) throws TSQLException;

    Reader getCharacterStream(int columnIndex) throws TSQLException;

    Object getObject(int columnIndex) throws TSQLException;

    <T> T getObject(int columnIndex, Class<T> type) throws TSQLException;

    TResultSetMetaData getMetaData() throws TSQLException;

    int findColumn(String columnLabel) throws TSQLException;

    TStatement getStatement() throws TSQLException;

    TSQLWarning getWarnings() throws TSQLException;

    void clearWarnings() throws TSQLException;

    boolean isClosed() throws TSQLException;

    int getRow() throws TSQLException;

    int getType() throws TSQLException;

    boolean getBoolean(String columnLabel) throws TSQLException;

    byte getByte(String columnLabel) throws TSQLException;

    short getShort(String columnLabel) throws TSQLException;

    long getLong(String columnLabel) throws TSQLException;

    float getFloat(String columnLabel) throws TSQLException;

    double getDouble(String columnLabel) throws TSQLException;

    BigDecimal getBigDecimal(String columnLabel) throws TSQLException;

    byte[] getBytes(String columnLabel) throws TSQLException;

    Date getDate(String columnLabel) throws TSQLException;

    Time getTime(String columnLabel) throws TSQLException;

    Timestamp getTimestamp(String columnLabel) throws TSQLException;

    Object getObject(String columnLabel) throws TSQLException;
}
