package com.mindskip.xzs.message_center.domain.vo;

import com.mindskip.xzs.message_center.domain.entity.Emote;

import java.util.List;



public class CommentVO {

    private String id;

    private String topicId;

    private String topicType;

    private String previousCommentId;

    private String previousUserId;

    private String previousUserName;

    private String userId;

    private String userName;

    private String avatar;

    private String commentTime;

    private int like;

    private int dislike;

    /**
     * 1:赞 0:无动作 -1:点踩
     */
    private int action;

    private int subReplyCount;

    /**
     * 评论内容信息
     */
    private Content content;
    /**
     * 评论者信息
     */
    private Member member;
    /**
     * 表情信息
     */
    private Emote emote;
    /**
     *  子评论
     */
    private List<CommentVO> subReply;


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

    public String getPreviousUserId() {
        return previousUserId;
    }

    public void setPreviousUserId(String previousUserId) {
        this.previousUserId = previousUserId;
    }

    public String getPreviousUserName() {
        return previousUserName;
    }

    public void setPreviousUserName(String previousUserName) {
        this.previousUserName = previousUserName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Emote getEmote() {
        return emote;
    }

    public void setEmote(Emote emote) {
        this.emote = emote;
    }

    public List<CommentVO> getSubReply() {
        return subReply;
    }

    public void setSubReply(List<CommentVO> subReply) {
        this.subReply = subReply;
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

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getSubReplyCount() {
        return subReplyCount;
    }

    public void setSubReplyCount(int subReplyCount) {
        this.subReplyCount = subReplyCount;
    }
}
