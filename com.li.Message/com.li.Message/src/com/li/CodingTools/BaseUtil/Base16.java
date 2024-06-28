package com.li.CodingTools.BaseUtil;

/**
 * Base16编码与解码
 *
 * @author Lrn
 */
public class Base16 {

    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    /**
     * 这个解码
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        char[] out = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            out[i * 2] = HEX_CHARS[(data[i] >> 4) & 0x0F];
            out[i * 2 + 1] = HEX_CHARS[data[i] & 0x0F];
        }
        return new String(out);
    }

    public static byte[] decode(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string length must be even.");
        }
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < data.length; i++) {
            int j = i * 2;
            int f = Character.digit(hex.charAt(j), 16) << 4;
            int s = Character.digit(hex.charAt(j + 1), 16);
            data[i] = (byte) (f + s);
        }
        return data;
    }

}
