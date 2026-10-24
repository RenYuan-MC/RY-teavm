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

public interface TResultSetMetaData extends TWrapper {
    int columnNoNulls = 0;
    int columnNullable = 1;
    int columnNullableUnknown = 2;

    int getColumnCount() throws TSQLException;

    String getColumnLabel(int column) throws TSQLException;

    String getColumnName(int column) throws TSQLException;

    int getColumnType(int column) throws TSQLException;

    String getColumnTypeName(int column) throws TSQLException;

    int getColumnDisplaySize(int column) throws TSQLException;

    int getPrecision(int column) throws TSQLException;

    int getScale(int column) throws TSQLException;

    int isNullable(int column) throws TSQLException;

    String getTableName(int column) throws TSQLException;

    String getSchemaName(int column) throws TSQLException;

    String getCatalogName(int column) throws TSQLException;
}
