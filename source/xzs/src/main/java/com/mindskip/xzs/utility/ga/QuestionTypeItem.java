package com.mindskip.xzs.utility.ga;

public class QuestionTypeItem {
    /**
     * 标题(大题标题)
     */
    private String name ;
    /**
     * 题型 1.单选题 2.多选题 3.判断题 4.填空题 5.简答题
     */
    private int questionType;
    /**
     * 每题分值
     */
    private double score;
    /**
     * 题数
     */
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
