package com.mindskip.xzs.video_room.controller;

import com.alibaba.fastjson.JSONObject;
import com.mindskip.xzs.redis.RedisUtil;
import com.mindskip.xzs.utility.ResponseUtil;
import com.mindskip.xzs.video_room.bean.Danmu;
import com.mindskip.xzs.video_room.service.DanmuService;
import com.mindskip.xzs.video_room.websocket.WebSocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DanmuController {


    @Autowired
    DanmuService danmuService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    WebSocketUtil webSocketUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/api/danmu/demo")
    public Map<String,Object> getDanmuList(){
        System.out.println("demo...");
        List danmu = new ArrayList();
        for (int i = 0; i < 100; i++) {
            JSONObject object = new JSONObject();
            object.put("time",0);
            object.put("type","scroll");
            object.put("color","#00FF00");
            object.put("text","这是一条弹幕");
            danmu.add(object);
        }

        return ResponseUtil.danmuSuccess(danmu);
    }

    @RequestMapping("/api/danmu/post")
    public Map<String,Object> post(@RequestBody Danmu danmu){
        return ResponseUtil.danmuSuccess(danmuService.post(danmu));
    }

    @RequestMapping("/api/danmu/list")
    public Map<String,Object> list(@RequestParam  String videoId){
        return ResponseUtil.success(danmuService.getDanmuListByVideoId(videoId));
    }
}
