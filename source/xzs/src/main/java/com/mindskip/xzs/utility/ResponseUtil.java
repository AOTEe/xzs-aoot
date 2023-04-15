package com.mindskip.xzs.utility;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Integer SUCCESS = 200;

    public static Map<String,Object> success(Object data){
        Map<String,Object> response = new HashMap<>();
        response.put("code",ResponseUtil.SUCCESS);
        response.put("message","成功");
        response.put("data",data);
        return response;
    }

    public static Map<String,Object> success(){
        Map<String,Object> response = new HashMap<>();
        response.put("code",ResponseUtil.SUCCESS);
        response.put("message","成功");
        return response;
    }
}
