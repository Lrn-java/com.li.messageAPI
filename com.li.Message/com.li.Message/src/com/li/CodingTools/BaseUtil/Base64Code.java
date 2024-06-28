package com.li.CodingTools.BaseUtil;

import java.util.Base64;

public class Base64Code {

    public static String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decodeBase64(String encoded) {
        return Base64.getDecoder().decode(encoded);
    }

}
