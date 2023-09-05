package com.mindskip.xzs.video_room.websocket;

import java.util.UUID;

public class OnlineViewsSessionInfo {
    private String videoId = "";

    private String userId = "";

    private String sessionKey = UUID.randomUUID().toString();

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
