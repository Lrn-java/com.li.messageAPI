package com.li.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于检查个人信息，简单快速
 *
 * @author Lrn
 */
public class Message {

    /**
     * 构造函数，添加信息
     *
     * @param user 用户名
     */
    public static void createMessage(String user) {
        setUser(user);
    }

    /**
     * 构造函数，添加信息
     *
     * @param user 用户名
     * @param pass 密码
     */
    public static void createMessage(String user, String pass) {
        setUser(user);
        setPass(pass);
    }

    /**
     * 构造函数，添加信息
     *
     * @param user  用户名
     * @param pass  密码
     * @param phone 手机号
     */
    public static void createMessage(String user, String pass, String phone) {
        setUser(user);
        setPass(pass);
        setPhone(phone);
    }

    /**
     * 构造函数，添加信息
     *
     * @param user  用户名
     * @param pass  密码
     * @param phone 手机号
     * @param email 邮箱
     */
    public static void createMessage(String user, String pass, String phone, String email) {
        setUser(user);
        setPass(pass);
        setPhone(phone);
        isValidEmail(email);
    }

    /**
     * 构造函数，添加信息
     *
     * @param user         用户名
     * @param pass         密码
     * @param phone        手机号
     * @param email        邮箱
     * @param dateOfBirtha 出生日期
     */
    public static void createMessage(String user, String pass, String phone, String email, String dateOfBirtha) {
        setUser(user);
        setPass(pass);
        setPhone(phone);
        isValidEmail(email);
        isValidDateOfBirth(dateOfBirtha);
    }


    /**
     * 检查出生日期是否符合 "YYYY-MM-DD" 格式。
     *
     * @param dateOfBirth 出生日期字符串
     * @return 如果符合格式返回 true，否则返回 false
     */
    public static boolean isValidDateOfBirth(String dateOfBirth) {

        // 正则表达式用于匹配 YYYY-MM-DD 格式的日期
        String regex = "\\b(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateOfBirth);
        // 如果匹配器找到匹配项，则日期有效
        return matcher.matches();
    }

    /**
     * 设置用户名
     *
     * @param user 用户名
     */
    public static void setUser(String user) {
        // 判断用户名是否符合逻辑
        if (!(user != null && !user.isEmpty() && user.length() <= 8))
            throw new RuntimeException("用户名异常，未添加成功");

    }

    /**
     * 设置密码
     *
     * @param pass 密码
     */
    public static void setPass(String pass) {
        if (!(pass != null && pass.length() >= 6 && pass.length() <= 16))
            throw new RuntimeException("密码创建失败,请重新检查");
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public static void setPhone(String phone) {
        String regex = "^1[3-9]\\d{9}$";
        if (phone != null && phone.matches(regex)) {

        }
    }

    /**
     * 检查电子邮箱地址是否符合常见的格式。
     *
     * @param email 电子邮箱地址字符串
     * @return 如果符合格式返回 true，否则返回 false
     */
    public static boolean isValidEmail(String email) {
        // 正则表达式用于匹配电子邮箱地址
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // 如果匹配器找到匹配项，则电子邮箱地址有效
        return matcher.matches();
    }

    /**
     * 验证身份证号码是否有效
     *
     * @param idCard 身份证号码，18位数字字符串
     * @return 如果身份证号码有效返回true，否则返回false
     */
    public static boolean isValidIDCard(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return false;
        }

        // 身份证号码的前17位
        String num17 = idCard.substring(0, 17);
        // 身份证号码的第18位校验码
        String checkCode = idCard.substring(17);

        // 校验出生日期
        if (!isDateValid(num17)) {
            return false;
        }

        // 计算并校验第18位
        return checkCode.equals(calculateCheckCode(num17));
    }

    /**
     * 验证身份证出生日期是否有效
     *
     * @param num17 身份证号码的前17位
     * @return 如果出生日期有效返回true，否则返回false
     */
    private static boolean isDateValid(String num17) {
        // 提取出生年月日
        String birthDate = num17.substring(6, 14);
        return birthDate.matches("\\d{4}(0[1-9]{1}|1[0-2]{1})(0[1-9]{1}|[1-2][0-9]{1}|3[0-1]{1})");
    }

    /**
     * 计算身份证号码的校验码
     *
     * @param num17 身份证号码的前17位
     * @return 校验码
     */
    static String calculateCheckCode(String num17) {
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            int num = Integer.parseInt(num17.charAt(i) + "");
            sum += num * weights[i];
        }
        String checkCodes = "10X98765432";
        int mod = sum % 11;
        return checkCodes.substring(mod, mod + 1);
    }

}