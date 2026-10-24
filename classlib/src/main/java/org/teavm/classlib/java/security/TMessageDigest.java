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
package org.teavm.classlib.java.security;

import java.security.NoSuchAlgorithmException;

/**
 * Minimal message digest implementation for the browser runtime. Supports MD5, SHA-1 and
 * SHA-256, which is everything H2 needs.
 */
public abstract class TMessageDigest {
    private final String algorithm;

    protected TMessageDigest(String algorithm) {
        this.algorithm = algorithm;
    }

    public static TMessageDigest getInstance(String algorithm) throws NoSuchAlgorithmException {
        String name = algorithm.toUpperCase();
        switch (name) {
        case "MD5":
            return new Md5();
        case "SHA-1":
        case "SHA1":
            return new Sha1();
        case "SHA-256":
        case "SHA256":
            return new Sha256();
        default:
            throw new NoSuchAlgorithmException(algorithm);
        }
    }

    public final String getAlgorithm() {
        return algorithm;
    }

    public final int getDigestLength() {
        return engineGetDigestLength();
    }

    protected abstract int engineGetDigestLength();

    public void update(byte input) {
        update(new byte[] { input }, 0, 1);
    }

    public abstract void update(byte[] input, int offset, int len);

    public void update(byte[] input) {
        update(input, 0, input.length);
    }

    public byte[] digest() {
        return engineDigest();
    }

    public int digest(byte[] buf, int offset, int len) {
        byte[] result = engineDigest();
        int n = Math.min(len, result.length);
        System.arraycopy(result, 0, buf, offset, n);
        return n;
    }

    public byte[] digest(byte[] input) {
        update(input);
        return engineDigest();
    }

    protected abstract byte[] engineDigest();

    public void reset() {
        engineReset();
    }

    protected abstract void engineReset();

    private static byte[] padMessage(byte[] data, int length, int blockSize, long bitLength) {
        int total = (length + 9 + blockSize - 1) / blockSize * blockSize;
        byte[] padded = new byte[total];
        System.arraycopy(data, 0, padded, 0, length);
        padded[length] = (byte) 0x80;
        for (int i = 0; i < 8; ++i) {
            padded[total - 1 - i] = (byte) (bitLength >>> (8 * i));
        }
        return padded;
    }

    static final class Sha256 extends TMessageDigest {
        private final int[] state = { 0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
                0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19 };
        private final byte[] buffer = new byte[64];
        private int buffered;
        private long length;

        Sha256() {
            super("SHA-256");
        }

        @Override
        protected int engineGetDigestLength() {
            return 32;
        }

        @Override
        public void update(byte[] input, int offset, int len) {
            for (int i = 0; i < len; ++i) {
                buffer[buffered++] = input[offset + i];
                if (buffered == 64) {
                    compress(state, buffer);
                    buffered = 0;
                }
            }
            length += len;
        }

        @Override
        protected byte[] engineDigest() {
            long bitLength = length * 8;
            int savedBuffered = buffered;
            byte[] padded = padMessage(buffer, buffered, 64, bitLength);
            byte[] result = new byte[32];
            int[] st = state.clone();
            for (int i = 0; i < padded.length; i += 64) {
                byte[] block = new byte[64];
                System.arraycopy(padded, i, block, 0, 64);
                compress(st, block);
            }
            for (int i = 0; i < 8; ++i) {
                result[i * 4] = (byte) (st[i] >>> 24);
                result[i * 4 + 1] = (byte) (st[i] >>> 16);
                result[i * 4 + 2] = (byte) (st[i] >>> 8);
                result[i * 4 + 3] = (byte) st[i];
            }
            buffered = savedBuffered;
            return result;
        }

        @Override
        protected void engineReset() {
            int[] initial = { 0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
                    0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19 };
            System.arraycopy(initial, 0, state, 0, 8);
            buffered = 0;
            length = 0;
        }

        private static final int[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
        };

        static void compress(int[] state, byte[] block) {
            int[] w = new int[64];
            for (int i = 0; i < 16; ++i) {
                w[i] = ((block[i * 4] & 0xff) << 24) | ((block[i * 4 + 1] & 0xff) << 16)
                        | ((block[i * 4 + 2] & 0xff) << 8) | (block[i * 4 + 3] & 0xff);
            }
            for (int i = 16; i < 64; ++i) {
                int s0 = Integer.rotateRight(w[i - 15], 7) ^ Integer.rotateRight(w[i - 15], 18) ^ (w[i - 15] >>> 3);
                int s1 = Integer.rotateRight(w[i - 2], 17) ^ Integer.rotateRight(w[i - 2], 19) ^ (w[i - 2] >>> 10);
                w[i] = w[i - 16] + s0 + w[i - 7] + s1;
            }
            int a = state[0], b = state[1], c = state[2], d = state[3];
            int e = state[4], f = state[5], g = state[6], h = state[7];
            for (int i = 0; i < 64; ++i) {
                int s1 = Integer.rotateRight(e, 6) ^ Integer.rotateRight(e, 11) ^ Integer.rotateRight(e, 25);
                int ch = (e & f) ^ (~e & g);
                int temp1 = h + s1 + ch + K[i] + w[i];
                int s0 = Integer.rotateRight(a, 2) ^ Integer.rotateRight(a, 13) ^ Integer.rotateRight(a, 22);
                int maj = (a & b) ^ (a & c) ^ (b & c);
                int temp2 = s0 + maj;
                h = g;
                g = f;
                f = e;
                e = d + temp1;
                d = c;
                c = b;
                b = a;
                a = temp1 + temp2;
            }
            state[0] += a;
            state[1] += b;
            state[2] += c;
            state[3] += d;
            state[4] += e;
            state[5] += f;
            state[6] += g;
            state[7] += h;
        }
    }

    static final class Sha1 extends TMessageDigest {
        private final int[] state = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476, 0xc3d2e1f0 };
        private final byte[] buffer = new byte[64];
        private int buffered;
        private long length;

        Sha1() {
            super("SHA-1");
        }

        @Override
        protected int engineGetDigestLength() {
            return 20;
        }

        @Override
        public void update(byte[] input, int offset, int len) {
            for (int i = 0; i < len; ++i) {
                buffer[buffered++] = input[offset + i];
                if (buffered == 64) {
                    compress(state, buffer);
                    buffered = 0;
                }
            }
            length += len;
        }

        @Override
        protected byte[] engineDigest() {
            long bitLength = length * 8;
            byte[] padded = padMessage(buffer, buffered, 64, bitLength);
            int[] st = state.clone();
            for (int i = 0; i < padded.length; i += 64) {
                byte[] block = new byte[64];
                System.arraycopy(padded, i, block, 0, 64);
                compress(st, block);
            }
            byte[] result = new byte[20];
            for (int i = 0; i < 5; ++i) {
                result[i * 4] = (byte) (st[i] >>> 24);
                result[i * 4 + 1] = (byte) (st[i] >>> 16);
                result[i * 4 + 2] = (byte) (st[i] >>> 8);
                result[i * 4 + 3] = (byte) st[i];
            }
            return result;
        }

        @Override
        protected void engineReset() {
            int[] initial = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476, 0xc3d2e1f0 };
            System.arraycopy(initial, 0, state, 0, 5);
            buffered = 0;
            length = 0;
        }

        static void compress(int[] state, byte[] block) {
            int[] w = new int[80];
            for (int i = 0; i < 16; ++i) {
                w[i] = ((block[i * 4] & 0xff) << 24) | ((block[i * 4 + 1] & 0xff) << 16)
                        | ((block[i * 4 + 2] & 0xff) << 8) | (block[i * 4 + 3] & 0xff);
            }
            for (int i = 16; i < 80; ++i) {
                w[i] = Integer.rotateLeft(w[i - 3] ^ w[i - 8] ^ w[i - 14] ^ w[i - 16], 1);
            }
            int a = state[0], b = state[1], c = state[2], d = state[3], e = state[4];
            for (int i = 0; i < 80; ++i) {
                int f;
                int k;
                if (i < 20) {
                    f = (b & c) | (~b & d);
                    k = 0x5a827999;
                } else if (i < 40) {
                    f = b ^ c ^ d;
                    k = 0x6ed9eba1;
                } else if (i < 60) {
                    f = (b & c) | (b & d) | (c & d);
                    k = 0x8f1bbcdc;
                } else {
                    f = b ^ c ^ d;
                    k = 0xca62c1d6;
                }
                int temp = Integer.rotateLeft(a, 5) + f + e + k + w[i];
                e = d;
                d = c;
                c = Integer.rotateLeft(b, 30);
                b = a;
                a = temp;
            }
            state[0] += a;
            state[1] += b;
            state[2] += c;
            state[3] += d;
            state[4] += e;
        }
    }

    static final class Md5 extends TMessageDigest {
        private final int[] state = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476 };
        private final byte[] buffer = new byte[64];
        private int buffered;
        private long length;

        private static final int[] S = { 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
                5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
                4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
                6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21 };

        private static final int[] K = new int[64];

        static {
            for (int i = 0; i < 64; ++i) {
                K[i] = (int) (long) (Math.floor(Math.abs(Math.sin(i + 1)) * 4294967296.0));
            }
        }

        Md5() {
            super("MD5");
        }

        @Override
        protected int engineGetDigestLength() {
            return 16;
        }

        @Override
        public void update(byte[] input, int offset, int len) {
            for (int i = 0; i < len; ++i) {
                buffer[buffered++] = input[offset + i];
                if (buffered == 64) {
                    compress(state, buffer);
                    buffered = 0;
                }
            }
            length += len;
        }

        @Override
        protected byte[] engineDigest() {
            long bitLength = length * 8;
            byte[] padded = padMessage(buffer, buffered, 64, bitLength);
            int[] st = state.clone();
            for (int i = 0; i < padded.length; i += 64) {
                byte[] block = new byte[64];
                System.arraycopy(padded, i, block, 0, 64);
                compress(st, block);
            }
            byte[] result = new byte[16];
            for (int i = 0; i < 4; ++i) {
                result[i * 4] = (byte) st[i];
                result[i * 4 + 1] = (byte) (st[i] >>> 8);
                result[i * 4 + 2] = (byte) (st[i] >>> 16);
                result[i * 4 + 3] = (byte) (st[i] >>> 24);
            }
            return result;
        }

        @Override
        protected void engineReset() {
            int[] initial = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476 };
            System.arraycopy(initial, 0, state, 0, 4);
            buffered = 0;
            length = 0;
        }

        static void compress(int[] state, byte[] block) {
            int[] m = new int[16];
            for (int i = 0; i < 16; ++i) {
                m[i] = (block[i * 4] & 0xff) | ((block[i * 4 + 1] & 0xff) << 8)
                        | ((block[i * 4 + 2] & 0xff) << 16) | ((block[i * 4 + 3] & 0xff) << 24);
            }
            int a = state[0], b = state[1], c = state[2], d = state[3];
            for (int i = 0; i < 64; ++i) {
                int f;
                int g;
                if (i < 16) {
                    f = (b & c) | (~b & d);
                    g = i;
                } else if (i < 32) {
                    f = (d & b) | (~d & c);
                    g = (5 * i + 1) & 15;
                } else if (i < 48) {
                    f = b ^ c ^ d;
                    g = (3 * i + 5) & 15;
                } else {
                    f = c ^ (b | ~d);
                    g = (7 * i) & 15;
                }
                int temp = d;
                d = c;
                c = b;
                b = b + Integer.rotateLeft(a + f + K[i] + m[g], S[i]);
                a = temp;
            }
            state[0] += a;
            state[1] += b;
            state[2] += c;
            state[3] += d;
        }
    }
}
