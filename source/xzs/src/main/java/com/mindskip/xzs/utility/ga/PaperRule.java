package com.mindskip.xzs.utility.ga;

import java.util.List;

public class PaperRule {
    private String id;
    /**
     *
     */
    private String subjectId;
    /**
     *  总分
     */
    private int totalMark;
    /**
     *  难度(0-1)
     */
    private double difficulty;
    /**
     *
     */
    private QuestionTypeItem[] questionTypeItems;
    /**
     *  知识点
     */
    private List<String> points;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getPoints() {
        return points;
    }

    public void setPoints(List<String> points) {
        this.points = points;
    }

    public QuestionTypeItem[] getQuestionTypeItems() {
        return questionTypeItems;
    }

    public void setQuestionTypeItems(QuestionTypeItem[] questionTypeItems) {
        this.questionTypeItems = questionTypeItems;
    }
}
