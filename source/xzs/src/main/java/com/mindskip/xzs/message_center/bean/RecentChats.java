package com.mindskip.xzs.message_center.bean;

/**
 * 最近私聊列表
 */
public class RecentChats {

    private String userId;

    //用户标识,使用、拼接
    private String recentChats;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecentChats() {
        return recentChats;
    }

    public void setRecentChats(String recentChats) {
        this.recentChats = recentChats;
    }
}
