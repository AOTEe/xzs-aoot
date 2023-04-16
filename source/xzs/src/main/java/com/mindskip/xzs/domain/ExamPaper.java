package com.mindskip.xzs.domain;

import java.io.Serializable;
import java.util.Date;

public class ExamPaper implements Serializable {

    private static final long serialVersionUID = 8509645224550501395L;

    private String id;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 学科
     */
    private String subjectId;

    private String subjectName;

    /**
     * 试卷类型( 1固定试卷 4.时段试卷 6.任务试卷)
     */
    private Integer paperType;

    /**
     * 年级
     */
    private Integer gradeLevel;

    /**
     * 试卷总分(千分制)
     */
    private Integer score;

    /**
     * 题目数量
     */
    private Integer questionCount;

    /**
     * 建议时长(分钟)
     */
    private Integer suggestTime;

    /**
     * 时段试卷 开始时间
     */
    private Date limitStartTime;

    /**
     * 时段试卷 结束时间
     */
    private Date limitEndTime;

    /**
     * 试卷框架 内容为JSON
     */
    private String frameTextContentId;

    private String createUser;

    private Date createTime;

    private Boolean deleted;

    private String taskExamId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getPaperType() {
        return paperType;
    }

    public void setPaperType(Integer paperType) {
        this.paperType = paperType;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Integer getSuggestTime() {
        return suggestTime;
    }

    public void setSuggestTime(Integer suggestTime) {
        this.suggestTime = suggestTime;
    }

    public Date getLimitStartTime() {
        return limitStartTime;
    }

    public void setLimitStartTime(Date limitStartTime) {
        this.limitStartTime = limitStartTime;
    }

    public Date getLimitEndTime() {
        return limitEndTime;
    }

    public void setLimitEndTime(Date limitEndTime) {
        this.limitEndTime = limitEndTime;
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

    public String getTaskExamId() {
        return taskExamId;
    }

    public void setTaskExamId(String taskExamId) {
        this.taskExamId = taskExamId;
    }
}
