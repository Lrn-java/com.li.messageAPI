package com.li.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密,解密
 * @author Lrn
 */
public class MD5Util {
    /**
     * 对输入的字符串进行MD5加密
     * @param bytes 需要加密的字符串
     * @return MD5加密后的字符串
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 对输入的字符串进行MD5加密
     * @param input 需要加密的字符串
     * @return MD5加密后的32位十六进制字符串
     */
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
