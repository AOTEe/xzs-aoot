package com.mindskip.xzs.message_center.domain.entity;

/**
 * 点赞表
 */
public class Like {

    private String id;

    //评论或者视频的id
    private String topicId;

    // 类型有video、question、comment
    private String topicType;

    //点赞者id
    private String userId;

    //1:赞 0:取消赞 -1:点踩
    private int status;

    private String operateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Like() {
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
}
