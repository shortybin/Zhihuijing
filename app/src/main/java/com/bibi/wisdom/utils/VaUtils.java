package com.bibi.wisdom.utils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 验证工具类
 *
 * @author Sun.bl
 * @version [1.0, 2016/8/5]
 */
public class VaUtils {

    private VaUtils() {
        throw new AssertionError("Utils Class");
    }


    /**
     * 是否是手机号
     *
     * @param mobileNo 手机号:13[0-9] 15没有4 18[0-9] 170
     * @return boolean true是,false 否
     */
    public static boolean isMobileNo(String mobileNo) {
        if (null == mobileNo) {
            return false;
        }
//        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[012356789])|(17[012356789])|(14[012356789]))\\d{8}$");
        Pattern p = Pattern.compile("^1\\d{10}$");
        return p.matcher(mobileNo).matches();
    }

    /**
     * 手机号和座机号验证
     *
     * @return boolean true是,false 否
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        String expression = "(010\\d{8})|(0[2-9]\\d{9})|(13\\d{9})|(14[57]\\d{8})|(15[0-35-9]\\d{8})|(18[0-35-9]\\d{8})";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /** * 检测是否有emoji表情 * @param source * @return */
    public static boolean containsEmoji(String source) { //两种方法限制emoji
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /** * 判断是否是Emoji * @param codePoint 比较的单个字符 * @return */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 判断邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    //比如可以这样生成Map<String, RequestBody> requestBodyMap
//Map<String, String> requestDataMap这里面放置上传数据的键值对。
    public static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }


}
