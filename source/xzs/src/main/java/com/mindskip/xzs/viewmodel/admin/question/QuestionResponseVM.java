package com.mindskip.xzs.viewmodel.admin.question;

import com.mindskip.xzs.viewmodel.BaseVM;



public class QuestionResponseVM extends BaseVM {

    private String id;

    private Integer questionType;

    private String textContentId;

    private String createTime;

    private String subjectId;

    private String createUser;

    private String score;

    private Integer status;

    private String correct;

    private String analyzeTextContentId;

    private Integer difficult;

    private String shortTitle;

    private String tags;//知识点(标签)

    private String tagsName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getTextContentId() {
        return textContentId;
    }

    public void setTextContentId(String textContentId) {
        this.textContentId = textContentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getAnalyzeTextContentId() {
        return analyzeTextContentId;
    }

    public void setAnalyzeTextContentId(String analyzeTextContentId) {
        this.analyzeTextContentId = analyzeTextContentId;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }
}
