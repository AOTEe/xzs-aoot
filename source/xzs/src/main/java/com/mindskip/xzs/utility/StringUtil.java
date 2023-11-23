package com.mindskip.xzs.utility;

import java.util.List;

public class StringUtil {
    /**
     *
     * @param list  列表对象
     * @param split 分割符号
     * @return
     */
    public static String list2String(List<String> list, String split) {
        String result = "";
        for (String str : list) {
            result = result + split + str;
        }
        if (result.length() > 0)
            result = result.substring(1, result.length());
        return result;
    }
}
