package com.mindskip.xzs.video_room.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindskip.xzs.redis.Publisher;
import com.mindskip.xzs.redis.RedisConfig;
import com.mindskip.xzs.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 即时通讯工具类
 */
@Component
public class WebSocketUtil {




    /**
     * 视频在线人数
     */
    public  ConcurrentMap<String, HashMap<String,ArrayList<OnlineViewsSessionInfo>>> ALL_VIDEOS_ONLINE_USER_MAP ;


    /**
     * 维护本台机器的websocket的session
     */
    public ConcurrentHashMap<String,Session> SESSION_MAP = new ConcurrentHashMap<>();

    @Autowired
    private  RedisUtil redisUtil;

    @Autowired
    private Publisher publisher;

    /**
     * 某视频在线人数+1
     *
     * @param onlineViewsServer
     */
    public  void add(OnlineViewsServer onlineViewsServer) {

        OnlineViewsSessionInfo onlineViewsSessionInfo = onlineViewsServer.getOnlineViewsSessionInfo();
        //session添加进本地map
        SESSION_MAP.put(onlineViewsSessionInfo.getSessionKey(),onlineViewsServer.getSession());

        //redis缓存添加
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(redisUtil.get("ALL_VIDEOS_ONLINE_USER_MAP"));
        String mapJson = (String) redisUtil.get("ALL_VIDEOS_ONLINE_USER_MAP");
        if (StringUtils.isEmpty(mapJson))
            ALL_VIDEOS_ONLINE_USER_MAP = new ConcurrentHashMap<>();
        else {
            //json转map
            try {
                ALL_VIDEOS_ONLINE_USER_MAP = objectMapper.readValue(mapJson, new TypeReference<ConcurrentMap<String, HashMap<String,ArrayList<OnlineViewsSessionInfo>>>>() {});
                System.out.println(ALL_VIDEOS_ONLINE_USER_MAP);
            } catch (IOException e) {
                System.out.println("ALL_VIDEOS_ONLINE_USER_MAP Json 解析异常...");
                e.printStackTrace();
            }

        }
        HashMap<String, ArrayList<OnlineViewsSessionInfo>> onlineUsers = ALL_VIDEOS_ONLINE_USER_MAP.get(onlineViewsSessionInfo.getVideoId());
        if (onlineUsers == null) {
            onlineUsers = new HashMap<>();
            ArrayList thisUserList = new ArrayList();
            thisUserList.add(onlineViewsSessionInfo);
            onlineUsers.put(onlineViewsSessionInfo.getUserId(),thisUserList);
        }else {
            ArrayList<OnlineViewsSessionInfo> onlineViewsServers = onlineUsers.get(onlineViewsSessionInfo.getUserId());
            if(onlineViewsServers == null)
                onlineViewsServers = new ArrayList<>();
            onlineViewsServers.add(onlineViewsSessionInfo);
            onlineUsers.put(onlineViewsSessionInfo.getUserId(),onlineViewsServers);
        }
        ALL_VIDEOS_ONLINE_USER_MAP.put(onlineViewsSessionInfo.getVideoId(), onlineUsers);
        objectMapper = new ObjectMapper();
        String redisMapJson = null;
        try {
            // 将 Map 转换为 JSON 字符串
            redisMapJson = objectMapper.writeValueAsString(ALL_VIDEOS_ONLINE_USER_MAP);
            // 打印生成的 JSON 字符串
            System.out.println(redisMapJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtil.set("ALL_VIDEOS_ONLINE_USER_MAP",redisMapJson);
        //发送消息人数发生变化
        sendOnlineNum(onlineViewsSessionInfo.getVideoId());
        System.out.println("用户："+ onlineViewsSessionInfo.getUserId() +"进入视频："+ onlineViewsSessionInfo.getVideoId() +"，当前在线人数："+onlineUsers.size());

        publisher.publish(RedisConfig.VIDEO_ONLINE_VIEWS,"用户上线拉...");


        return ;
    }


    /**
     * 某视频在线人数-1
     *
     * @param onlineViewsServer
     */
    public  void remove(OnlineViewsServer onlineViewsServer) {
        OnlineViewsSessionInfo viewsSessionInfo = onlineViewsServer.getOnlineViewsSessionInfo();
        String videoId = viewsSessionInfo.getVideoId();
        String userId = viewsSessionInfo.getUserId();
        //本地移除session
        SESSION_MAP.remove(viewsSessionInfo.getSessionKey());

        //移除redis缓存
        String mapJson = (String) redisUtil.get("ALL_VIDEOS_ONLINE_USER_MAP");
        ObjectMapper objectMapper = new ObjectMapper();
        if (StringUtils.isEmpty(mapJson))
            return;
        else {
            //json转map
            try {
                ALL_VIDEOS_ONLINE_USER_MAP = objectMapper.readValue(mapJson, new TypeReference<ConcurrentMap<String, HashMap<String,ArrayList<OnlineViewsSessionInfo>>>>() {});
                System.out.println(ALL_VIDEOS_ONLINE_USER_MAP);
            } catch (IOException e) {
                System.out.println("ALL_VIDEOS_ONLINE_USER_MAP Json 解析异常...");
                e.printStackTrace();
            }
        }
        HashMap<String, ArrayList<OnlineViewsSessionInfo>> onlineUsers = ALL_VIDEOS_ONLINE_USER_MAP.get(videoId);//当前视频在线用户信息
        if (onlineUsers != null) {
            ArrayList<OnlineViewsSessionInfo> onlineViewsSessionInfos = onlineUsers.get(userId);
            //Json转换之后对象变了,不可以直接onlineViewsSessionInfos.remove();
            //推荐使用迭代器进行删除
            Iterator<OnlineViewsSessionInfo> iter = onlineViewsSessionInfos.iterator();
            while (iter.hasNext()){
                OnlineViewsSessionInfo sessionInfo = iter.next();
                if (viewsSessionInfo.getSessionKey().equals(sessionInfo.getSessionKey()))
                    iter.remove();
            }
//            for (OnlineViewsSessionInfo item : onlineViewsSessionInfos) {
//                if (viewsSessionInfo.getSessionKey().equals(item.getSessionKey()))
//                    onlineViewsSessionInfos.remove(item);
//            }
            if (onlineViewsSessionInfos.size() == 0){
                onlineUsers.remove(userId);
                //发送消息人数发生变化
                //sendOnlineNum(viewsSessionInfo.getVideoId());
            }
            System.out.println("用户："+userId +"离开视频："+videoId +"，当前在线人数："+onlineUsers.size());
            if (onlineUsers.size() == 0)
                ALL_VIDEOS_ONLINE_USER_MAP.remove(videoId);
        }
        String redisMapJson = null;
        try {
            // 将 Map 转换为 JSON 字符串
            redisMapJson = objectMapper.writeValueAsString(ALL_VIDEOS_ONLINE_USER_MAP);
            // 打印生成的 JSON 字符串
            System.out.println(redisMapJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtil.set("ALL_VIDEOS_ONLINE_USER_MAP",redisMapJson);
        publisher.publish(RedisConfig.VIDEO_ONLINE_VIEWS,"用户下线拉...");
        return ;
    }

    /**
     *
     */
    public  void sendOnlineNum(String videoId){
        HashMap<String, ArrayList<OnlineViewsSessionInfo>> onlineUsers = ALL_VIDEOS_ONLINE_USER_MAP.get(videoId);
        for (String key : onlineUsers.keySet()) {
            ArrayList<OnlineViewsSessionInfo> onlineViewsServers = onlineUsers.get(key);
            for (OnlineViewsSessionInfo viewsSessionInfo : onlineViewsServers) {
                    if (SESSION_MAP.containsKey(viewsSessionInfo.getSessionKey())){
                    //本地存在redis中的session_key，则发送信息
                    Session session = (Session) SESSION_MAP.get(viewsSessionInfo.getSessionKey());
                    try {
                        String num = String.valueOf(onlineUsers.size());
                        session.getBasicRemote().sendText(num);
                    } catch (IOException e) {
                        System.out.println("广播在线人数异常...");
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
