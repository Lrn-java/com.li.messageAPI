package com.li.Message;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.li.Message.Message.calculateCheckCode;

/**
 * 检测身份证信息
 * @author Lrn
 */
public class IDCardInfoExtractor {

    // 身份证前6位对应的地区
    private static final Map<String, String> REGION_MAP = new HashMap<>();

    static {
        REGION_MAP.put("110000", "");
        REGION_MAP.put("110100", "");
    }

    /**
     * 提取身份证信息
     * @param idCard 身份证号码，18位数字字符串
     * @return 身份证信息的Map
     */
    public static Map<String, Object> extractIDCardInfo(String idCard) {
        Map<String, Object> infoMap = new HashMap<>();
        if (isValidIDCard(idCard)) {
            infoMap = new HashMap<>();
            String province = idCard.substring(0, 2);
            String city = idCard.substring(2, 4);
            String district = idCard.substring(4, 6);
            String birthDate = idCard.substring(6, 14);
            String genderCode = idCard.substring(16, 17);
            String legal = calculateCheckCode(idCard.substring(0, 17)).equals(idCard.substring(17)) ? "合法" : "不合法";

            infoMap.put("归属地", getRegionName(province + city + district));
            infoMap.put("省份", getRegionName(province));
            infoMap.put("城市", getRegionName(city));
            infoMap.put("区域", getRegionName(district));
            infoMap.put("出生日", birthDate);
            infoMap.put("性别", "男".equals(genderCode) ? "男" : "女");
            infoMap.put("合法", legal);
        } else {
            infoMap.put("合法", "不合法");
        }
        return infoMap;
    }

    /**
     * 根据身份证号码前6位获取归属地名称
     * @param regionCode 地区代码
     * @return 归属地名称
     */
    private static String getRegionName(String regionCode) {
        return REGION_MAP.getOrDefault(regionCode, "地区");
    }

    /**
     * 校验身份证号码是否有效
     * @param idCard 身份证号码
     * @return 是否有效
     */
    public static boolean isValidIDCard(String idCard) {
        // 身份证号码长度校验
        if (idCard == null || idCard.length() != 18) {
            return false;
        }

        return true;
    }

}