package com.mindskip.xzs.domain;

import java.io.Serializable;
import java.util.Date;

public class TaskExam implements Serializable {

    private static final long serialVersionUID = -7014704644631536195L;

    private String id;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 年级
     */
    private Integer gradeLevel;

    /**
     * 任务框架 内容为JSON
     */
    private String frameTextContentId;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    private Boolean deleted;

    /**
     * 创建人用户名
     */
    private String createUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getFrameTextContentId() {
        return frameTextContentId;
    }

    public void setFrameTextContentId(String frameTextContentId) {
        this.frameTextContentId = frameTextContentId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }
}