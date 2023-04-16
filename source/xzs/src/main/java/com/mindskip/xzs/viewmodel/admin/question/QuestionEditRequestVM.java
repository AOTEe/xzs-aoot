package com.mindskip.xzs.viewmodel.admin.question;


import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;


public class QuestionEditRequestVM {

    private String id;
    @NotNull
    private Integer questionType;
    @NotNull
    private String subjectId;
    @NotBlank
    private String title;

    private Integer gradeLevel;

    private String tags;

    private String tagsName;

    @Valid
    private List<QuestionEditItemVM> items;
    //@NotBlank
    private String analyze;

    private List<String> correctArray;

    private String correct;
    @NotBlank
    private String score;

    @Range(min = 1, max = 5, message = "请选择题目难度")
    private Integer difficult;

    private Integer itemOrder;

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public List<QuestionEditItemVM> getItems() {
        return items;
    }

    public void setItems(List<QuestionEditItemVM> items) {
        this.items = items;
    }

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
    }

    public List<String> getCorrectArray() {
        return correctArray;
    }

    public void setCorrectArray(List<String> correctArray) {
        this.correctArray = correctArray;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
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
