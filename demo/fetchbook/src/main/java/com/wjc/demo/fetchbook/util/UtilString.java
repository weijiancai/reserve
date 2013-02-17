package com.wjc.demo.fetchbook.util;

/**
 * 字符串工具类
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class UtilString {
    /**
     * 去掉两边的空白符，包括中文空白符" "
     *
     * @param str 字符串
     * @return 返回去掉空白符后的字符串
     */
    public static String trim(String str) {
        if(str == null) return null;

        str = str.trim();

        while (str.startsWith(" ")) {
            str = str.substring(1);
        }

        while (str.startsWith("　")) {
            str = str.substring(1);
        }

        while (str.endsWith(" ")) {
            str = str.substring(0, str.length() - 1);
        }

        while (str.endsWith("　")) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }
}
