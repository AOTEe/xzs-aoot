package com.mindskip.xzs.domain;

import java.io.Serializable;
import java.util.Date;

public class TaskExamCustomerAnswer implements Serializable {

    private static final long serialVersionUID = -556842372977600137L;

    private String id;

    /**
     * 任务Id
     */
    private String taskExamId;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 任务完成情况(Json)
     */
    private String textContentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskExamId() {
        return taskExamId;
    }

    public void setTaskExamId(String taskExamId) {
        this.taskExamId = taskExamId;
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

    public String getTextContentId() {
        return textContentId;
    }

    public void setTextContentId(String textContentId) {
        this.textContentId = textContentId;
    }
}
