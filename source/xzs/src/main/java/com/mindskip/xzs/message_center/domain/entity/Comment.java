package com.mindskip.xzs.message_center.domain.entity;

//评论表
public class Comment {
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 视频、试题、评论(root)的id
     */
    private String topicId;
    /**
     * 1:video、2:question、3:comment
     */
    private String topicType;
    /**
     * 上条评论的id
     */
    private String previousCommentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论者id
     */
    private String userId;
    /**
     * @的用户 形如 {"e测试服务平台":1980912898}
     */
    private String at;
    /**
     * 点赞数
     */
    private int like = 0;
    /**
     * 点踩数
     */
    private int dislike = 0;
    /**
     * 评论时间
     */
    private String commentTime;

    public Comment() {
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

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

}
