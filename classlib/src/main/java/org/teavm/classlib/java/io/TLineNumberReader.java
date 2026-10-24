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
package org.teavm.classlib.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TLineNumberReader extends BufferedReader {
    private int lineNumber;

    public TLineNumberReader(Reader in) {
        super(in);
    }

    public TLineNumberReader(Reader in, int sz) {
        super(in, sz);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String readLine() throws IOException {
        String result = super.readLine();
        if (result != null) {
            ++lineNumber;
        }
        return result;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == '\n') {
            ++lineNumber;
        }
        return c;
    }
}
