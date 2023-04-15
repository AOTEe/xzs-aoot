package com.mindskip.xzs.viewmodel.student.exam;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


public class ExamPaperSubmitVM {

    @NotNull
    private String id;

    @NotNull
    private Integer doTime;

    private String score;

    @NotNull
    @Valid
    private List<ExamPaperSubmitItemVM> answerItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDoTime() {
        return doTime;
    }

    public void setDoTime(Integer doTime) {
        this.doTime = doTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<ExamPaperSubmitItemVM> getAnswerItems() {
        return answerItems;
    }

    public void setAnswerItems(List<ExamPaperSubmitItemVM> answerItems) {
        this.answerItems = answerItems;
    }
}
