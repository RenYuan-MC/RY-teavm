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

import java.sql.SQLSyntaxErrorException;

public class TSQLSyntaxErrorException extends TSQLNonTransientException {
    private static final long serialVersionUID = 1L;

    public TSQLSyntaxErrorException() {
    }

    public TSQLSyntaxErrorException(String reason) {
        super(reason);
    }

    public TSQLSyntaxErrorException(String reason, String sqlState) {
        super(reason, sqlState);
    }

    public TSQLSyntaxErrorException(String reason, String sqlState, int vendorCode) {
        super(reason, sqlState, vendorCode);
    }

    public TSQLSyntaxErrorException(Throwable cause) {
        super(cause);
    }

    public TSQLSyntaxErrorException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public TSQLSyntaxErrorException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, 0, cause);
    }

    public TSQLSyntaxErrorException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}
