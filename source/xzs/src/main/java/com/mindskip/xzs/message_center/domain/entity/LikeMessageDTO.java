package com.mindskip.xzs.message_center.domain.entity;

public class LikeMessageDTO {
    /**
     * like、cancelLike、dislike、cancelDislike
     */
    private String actionType ;

    private String topicId;

    private String userId;

    /**
     * video、comment
     */
    private String topicType;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }


    public LikeMessageDTO() {
    }

    public LikeMessageDTO(String actionType, String topicId, String userId, String topicType) {
        this.actionType = actionType;
        this.topicId = topicId;
        this.userId = userId;
        this.topicType = topicType;
    }
}
