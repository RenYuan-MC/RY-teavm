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

public interface TPreparedStatement extends TStatement {
    TResultSet executeQuery() throws TSQLException;

    int executeUpdate() throws TSQLException;

    boolean execute() throws TSQLException;

    void clearParameters() throws TSQLException;

    void setFetchSize(int rows) throws TSQLException;

    void setNull(int parameterIndex, int sqlType) throws TSQLException;

    void setBoolean(int parameterIndex, boolean x) throws TSQLException;

    void setByte(int parameterIndex, byte x) throws TSQLException;

    void setShort(int parameterIndex, short x) throws TSQLException;

    void setInt(int parameterIndex, int x) throws TSQLException;

    void setLong(int parameterIndex, long x) throws TSQLException;

    void setFloat(int parameterIndex, float x) throws TSQLException;

    void setDouble(int parameterIndex, double x) throws TSQLException;

    void setBigDecimal(int parameterIndex, BigDecimal x) throws TSQLException;

    void setString(int parameterIndex, String x) throws TSQLException;

    void setBytes(int parameterIndex, byte[] x) throws TSQLException;

    void setArray(int parameterIndex, TArray x) throws TSQLException;

    void setDate(int parameterIndex, Date x) throws TSQLException;

    void setTime(int parameterIndex, Time x) throws TSQLException;

    void setTimestamp(int parameterIndex, Timestamp x) throws TSQLException;

    void setBinaryStream(int parameterIndex, InputStream x, int length) throws TSQLException;

    void setCharacterStream(int parameterIndex, Reader reader, int length) throws TSQLException;

    void setObject(int parameterIndex, Object x) throws TSQLException;

    void setObject(int parameterIndex, Object x, int targetSqlType) throws TSQLException;
}
