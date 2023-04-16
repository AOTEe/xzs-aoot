package com.mindskip.xzs.domain.exam;


import java.util.List;

public class ExamPaperTitleItemObject {

    private String name;

    private Integer questionType;

    private Integer score;

    private List<ExamPaperQuestionItemObject> questionItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<ExamPaperQuestionItemObject> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<ExamPaperQuestionItemObject> questionItems) {
        this.questionItems = questionItems;
    }
}
