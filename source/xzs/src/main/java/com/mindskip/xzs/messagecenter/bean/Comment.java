package com.mindskip.xzs.messagecenter.bean;

//评论表
public class Comment {
    private String id;
    private String topicId;
    private String topicType;
    private String previousCommentId;
    private String content;
    //评论者id
    private String userId;

    public Comment() {
    }

    public Comment(String id, String topicId, String topicType, String previousCommentId, String content, String userId) {
        this.id = id;
        this.topicId = topicId;
        this.topicType = topicType;
        this.previousCommentId = previousCommentId;
        this.content = content;
        this.userId = userId;
    }

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

    public String getPreviousCommentId() {
        return previousCommentId;
    }

    public void setPreviousCommentId(String previousCommentId) {
        this.previousCommentId = previousCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
