package com.li.EncryptionTools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用于SHA-1 加密方式
 *
 * @author Lrn
 */
public class Sha1Util {

    /**
     * 使用方法：
     *         String input = "Hello, World!"; // 要加密的字符串
     *         String sha1Hash = encryptToSha1(input);
     *
     *         System.out.println("SHA-1 Hash: " + sha1Hash);
     *
     */

    /**
     *  使用方法：
     *         // 已知的SHA-1哈希值
     *         String knownHash = "7b023f1ba9aad4e9e3d3c4ef21627fde...";
     *
     *         // 要验证的字符串
     *         String input = "Hello, World!";
     *
     *         // 验证字符串是否与已知哈希值匹配
     *         boolean isMatch = verifySha1(knownHash, input);
     *
     *         if (isMatch) {
     *             System.out.println("输入的字符串与已知的SHA-1哈希值匹配。");
     *         } else {
     *             System.out.println("输入的字符串与已知的SHA-1哈希值不匹配。");
     *         }
     */

    /**
     * 对字符串进行SHA-1加密
     *
     * @param str 要加密的字符串
     * @return 加密后的SHA-1哈希值
     */
    public static String encryptToSha1(String str) {
        try {
            // 获取MessageDigest实例，指定使用SHA-1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            // 对字符串进行UTF-8编码，然后更新digest
            digest.update(str.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            // 完成哈希计算，获取字节数组
            byte[] encodedhash = digest.digest();
            // 将字节数组转换为十六进制字符串
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            // 如果没有找到SHA-1算法，打印异常信息
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证字符串是否与已知的SHA-1哈希值匹配
     *
     * @param knownHash 已知的SHA-1哈希值
     * @param input     要验证的字符串
     * @return 如果字符串的哈希值与已知哈希值匹配，返回true，否则返回false
     */
    public static boolean verifySha1(String knownHash, String input) {
        try {
            // 获取MessageDigest实例，指定使用SHA-1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            // 对字符串进行UTF-8编码，然后更新digest
            digest.update(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            // 完成哈希计算，获取字节数组
            byte[] encodedhash = digest.digest();
            // 将计算得到的哈希值转换为十六进制字符串
            String calculatedHash = bytesToHex(encodedhash);

            // 比较计算得到的哈希值和已知哈希值
            return calculatedHash.equalsIgnoreCase(knownHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将字节数组转换为十六进制形式的字符串
     *
     * @param bytes 字节数组
     * @return 十六进制形式的字符串
     */
    private static String bytesToHex(byte[] bytes) {
        // 使用StringBuilder构建十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // 将字节转换为十六进制，并处理可能的单字符情况
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0'); // 如果是单字符，前面补0
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}