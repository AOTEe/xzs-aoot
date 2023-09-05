package com.mindskip.xzs.video_room.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/onlineViewsWebSocket/{videoId}/{userId}")
@RestController
public class OnlineViewsServer  {

    private Session session;

    private OnlineViewsSessionInfo onlineViewsSessionInfo;


    private static WebSocketUtil webSocketUtil;

    @Autowired
    public void setWebSocketUtil(WebSocketUtil webSocketUtil){
        OnlineViewsServer.webSocketUtil = webSocketUtil;
    }



    @OnOpen
    public void onOpen(Session session,@PathParam("videoId") String videoId, @PathParam("userId") String userId) {


        System.out.println("onOpen");
        System.out.println(userId);
        this.session = session;
        this.onlineViewsSessionInfo = new OnlineViewsSessionInfo();
        this.onlineViewsSessionInfo.setUserId(userId);
        this.onlineViewsSessionInfo.setVideoId(videoId);
        webSocketUtil.add(this);


    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onclose");
        webSocketUtil.remove(this);

    }

    @OnMessage
    public void onMessage(String params, Session session) {
        System.out.println(session);
        System.out.println(params);
    }

    @OnError
    public void onError(Session session, Throwable error) {
//        logger.info(session.getId() + "连接发生错误" + error.getMessage());
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public OnlineViewsSessionInfo getOnlineViewsSessionInfo() {
        return onlineViewsSessionInfo;
    }

    public void setOnlineViewsSessionInfo(OnlineViewsSessionInfo onlineViewsSessionInfo) {
        this.onlineViewsSessionInfo = onlineViewsSessionInfo;
    }
}
