package com.mindskip.xzs.utility;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     *
     * @param str   字符串
     * @param split 分隔符
     * @return
     */
    public static List<String> string2List(String str,String split){
        String[] strings = str.split(split);
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        return list;
    }
}
