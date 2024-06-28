package com.li.CodingTools.BaseUtil;

/**
 * Base32编码与解码
 *
 * @author Lrn
 */
public class Base32 {

    private static final String BASE32_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private static final int[] BASE32_DECODE_MAP = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
    };

    public static String encode(byte[] data) {
        StringBuilder sb = new StringBuilder();
        int bits = 0;
        int value = 0;

        for (byte b : data) {
            value = (value << 8) | (b & 0xFF);
            bits += 8;

            while (bits >= 5) {
                bits -= 5;
                sb.append(BASE32_ALPHABET.charAt((value >>> bits) & 31));
            }
        }

        if (bits > 0) {
            sb.append(BASE32_ALPHABET.charAt((value << (5 - bits)) & 31));
        }

        while (sb.length() % 8 != 0) {
            sb.append('='); // Padding
        }

        return sb.toString();
    }

    public static byte[] decode(String encoded) {
        byte[] bytes = new byte[(encoded.length() * 5) / 8];
        int bytePos = 0;
        int bitBuffer = 0;
        int bitCount = 0;

        for (int i = 0; i < encoded.length(); i++) {
            char c = encoded.charAt(i);
            if (c == '=') { // 遇到填充字符，停止解码
                break;
            }

            int index = BASE32_ALPHABET.indexOf(c);
            if (index == -1) {
                throw new IllegalArgumentException("Invalid character in Base32 input: " + c);
            }

            bitBuffer = (bitBuffer << 5) | index;
            bitCount += 5;

            if (bitCount >= 8) {
                bytes[bytePos++] = (byte) ((bitBuffer >>> (bitCount - 8)) & 0xFF);
                bitCount -= 8;
            }
        }

        // 处理最后一个字节，如果有剩余的位
        if (bitCount > 0) {
            bytes[bytePos] = (byte) (bitBuffer << (8 - bitCount) & 0xFF);
        }

        return bytes;
    }

}
