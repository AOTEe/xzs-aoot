package com.mindskip.xzs.message_center.websocket;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 即时通讯工具类
 */
public class WebSocketUtil {
    /**
     * 存储单用户链接，考虑到用户可能在多点登录，使用list进行存储
     */
    public static ConcurrentMap<String, List<WebSocketServer>> userMap = new ConcurrentHashMap<String, List<WebSocketServer>>();


    /**
     * 添加用户
     *
     * @param userUnid
     * @param ws
     */
    public static void add(String userUnid, WebSocketServer ws) {
        //添加到用户列表
        List<WebSocketServer> wsList = userMap.get(userUnid);
        if (wsList == null) {
            wsList = new ArrayList<WebSocketServer>();
        }
        wsList.add(ws);
        userMap.put(userUnid, wsList);
    }

    /**
     * 删除用户
     * @param ws
     */
    public static void remove(WebSocketServer ws) {
        List<WebSocketServer> wsList = userMap.get(ws.getUserId());
        if (null != wsList) {
            wsList.remove(ws);
        }
    }

    /**
     * websocket发送消息到指定用户
     * @param userId
     * @param msgJson
     */
    public static void sendMsg(String userId, String msgJson) {
        if (StringUtils.isEmpty(userId))
            return;
        List<WebSocketServer> wsList = userMap.get(userId);
        if (wsList != null) {
            for (WebSocketServer ws : wsList) {
                try {
                    ws.getSession().getBasicRemote().sendText(msgJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
