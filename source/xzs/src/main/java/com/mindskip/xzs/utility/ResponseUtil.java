package com.mindskip.xzs.utility;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Integer SUCCESS = 200;
    public static Integer DANMU_SUCCESS = 0;

    public static Map<String,Object> success(Object data){
        Map<String,Object> response = new HashMap<>();
        response.put("code",SUCCESS);
        response.put("message","成功");
        response.put("data",data);
        return response;
    }

    public static Map<String,Object> success(){
        Map<String,Object> response = new HashMap<>();
        response.put("code",SUCCESS);
        response.put("message","成功");
        return response;
    }

    public static Map<String,Object> danmuSuccess(Object data){
        Map<String,Object> response = new HashMap<>();
        response.put("code",DANMU_SUCCESS);
        response.put("data",data);
        return response;
    }

    public static Response responseSuccess(Object data){
        Response response = new Response(SUCCESS,"成功",data);
        return  response;
    }

    public static Response responseSuccess(){
        Response response = new Response(SUCCESS,"成功",null);
        return  response;
    }
}
