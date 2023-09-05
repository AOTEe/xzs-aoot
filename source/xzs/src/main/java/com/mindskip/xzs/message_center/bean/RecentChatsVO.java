package com.mindskip.xzs.message_center.bean;

/**
 * 最近聊天列表VO
 */
public class RecentChatsVO {
    private String oppositeUserId;
    private String oppositeUserName;
    private String oppositeUserPhoto;
    private String lastMsg;
    private String lastMsgTime;
    private int unreadCount;

    public String getOppositeUserId() {
        return oppositeUserId;
    }

    public void setOppositeUserId(String oppositeUserId) {
        this.oppositeUserId = oppositeUserId;
    }

    public String getOppositeUserName() {
        return oppositeUserName;
    }

    public void setOppositeUserName(String oppositeUserName) {
        this.oppositeUserName = oppositeUserName;
    }

    public String getOppositeUserPhoto() {
        return oppositeUserPhoto;
    }

    public void setOppositeUserPhoto(String oppositeUserPhoto) {
        this.oppositeUserPhoto = oppositeUserPhoto;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getLastMsgTime() {
        return lastMsgTime;
    }

    public void setLastMsgTime(String lastMsgTime) {
        this.lastMsgTime = lastMsgTime;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
