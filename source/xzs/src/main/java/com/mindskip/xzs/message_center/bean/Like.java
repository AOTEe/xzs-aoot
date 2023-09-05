package com.mindskip.xzs.message_center.bean;

/**
 * 点赞表
 */
public class Like {

    private String id;

    //评论或者视频的id
    private String topicId;

    private String topicType;

    //点赞者id
    private String userId;

    //1:赞 0:取消赞
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Like(String id, String topicId, String topicType, String userId, String status) {
        this.id = id;
        this.topicId = topicId;
        this.topicType = topicType;
        this.userId = userId;
        this.status = status;
    }

    public Like() {
    }
}
