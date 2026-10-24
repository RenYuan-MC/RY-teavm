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

public interface TDatabaseMetaData extends TWrapper {
    int procedureResultUnknown = 0;
    int procedureNoResult = 1;
    int procedureReturnsResult = 2;
    int procedureColumnUnknown = 0;
    int procedureColumnIn = 1;
    int procedureColumnInOut = 2;
    int procedureColumnResult = 3;
    int procedureColumnOut = 4;
    int procedureColumnReturn = 5;
    int procedureNoNulls = 0;
    int procedureNullable = 1;
    int procedureNullableUnknown = 2;
    int columnNoNulls = 0;
    int columnNullable = 1;
    int columnNullableUnknown = 2;
    int bestRowTemporary = 0;
    int bestRowTransaction = 1;
    int bestRowSession = 2;
    int bestRowUnknown = 0;
    int bestRowNotPseudo = 1;
    int bestRowPseudo = 2;
    int versionColumnUnknown = 0;
    int versionColumnNotPseudo = 1;
    int versionColumnPseudo = 2;
    int importedKeyCascade = 0;
    int importedKeyRestrict = 1;
    int importedKeySetNull = 2;
    int importedKeyNoAction = 3;
    int importedKeySetDefault = 4;
    int importedKeyInitiallyDeferred = 5;
    int importedKeyInitiallyImmediate = 6;
    int importedKeyNotDeferrable = 7;
    int typeNoNulls = 0;
    int typeNullable = 1;
    int typeNullableUnknown = 2;
    int typePredNone = 0;
    int typePredChar = 1;
    int typePredBasic = 2;
    int typeSearchable = 3;
    short tableIndexStatistic = 0;
    short tableIndexClustered = 1;
    short tableIndexHashed = 2;
    short tableIndexOther = 3;
    short attributeNoNulls = 0;
    short attributeNullable = 1;
    short attributeNullableUnknown = 2;
    int sqlStateXOpen = 1;
    int sqlStateSQL = 2;
    int sqlStateSQL99 = 2;
    int functionColumnUnknown = 0;
    int functionColumnIn = 1;
    int functionColumnInOut = 2;
    int functionColumnOut = 3;
    int functionReturn = 4;
    int functionColumnResult = 5;
    int functionNoNulls = 0;
    int functionNullable = 1;
    int functionNullableUnknown = 2;
    int functionResultUnknown = 0;
    int functionNoTable = 1;
    int functionReturnsTable = 2;

    TResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)
            throws TSQLException;

    TResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
            throws TSQLException;

    TResultSet getPrimaryKeys(String catalog, String schema, String table) throws TSQLException;

    TResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate)
            throws TSQLException;

    String getDatabaseProductName() throws TSQLException;

    String getIdentifierQuoteString() throws TSQLException;

    String getDatabaseProductVersion() throws TSQLException;

    String getDriverName() throws TSQLException;

    String getDriverVersion() throws TSQLException;

    int getDatabaseMajorVersion() throws TSQLException;

    int getDatabaseMinorVersion() throws TSQLException;
}
