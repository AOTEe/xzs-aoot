package com.mindskip.xzs.message_center.websocket;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chatWebSocket/{userId}")
@RestController
public class WebSocketServer {

    private Session session;

    private String userId = "";


    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        System.out.println("onOpen");
        System.out.println(userId);
        WebSocketUtil.add(userId,this);
        this.session = session;

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onclose");
        WebSocketUtil.remove(this);
//        CoreChatWebSocketUtil.remove(this);
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
