package com.learn.demo.springbootdemo.util;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.isEmpty() || str.replaceAll(" ", "").isEmpty();
    }

    public static boolean isBlank(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            if (isBlank(strs[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean contains(String str, String key) {
        return str != null && str.contains(key);
    }

    public static boolean contains(String... strs) {
        for (int i = 0; i < strs.length - 1; i++) {
            if (contains(strs[i], strs[strs.length - 1])) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(String[] strs, String key) {
        for (int i = 0; i < strs.length; i++) {
            if (contains(strs[i], key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 首字母大写
     *
     * @param in
     * @return
     */
    public static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1);
        return out;
    }

}
