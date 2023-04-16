package com.mindskip.xzs.viewmodel.admin.exam;

import com.mindskip.xzs.viewmodel.admin.question.QuestionEditRequestVM;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


public class ExamPaperTitleItemVM {

    @NotBlank(message = "标题内容不能为空")
    private String name;

    private Integer questionType;

    private Integer score;

    @Size(min = 1,message = "请添加题目")
    @Valid
    private List<QuestionEditRequestVM> questionItems;

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

    public List<QuestionEditRequestVM> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<QuestionEditRequestVM> questionItems) {
        this.questionItems = questionItems;
    }
}
