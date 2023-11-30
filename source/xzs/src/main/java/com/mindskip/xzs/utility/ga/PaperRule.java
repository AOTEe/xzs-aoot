package com.mindskip.xzs.utility.ga;

import java.util.List;

public class PaperRule {
    private String id;
    /**
     *
     */
    private String examId;
    /**
     *  总分
     */
    private int totalMark;
    /**
     *  难度(0-1)
     */
    private double difficulty;
    /**
     *  单选题
     */
    private int singleNum;

    /**
     *  每题分值
     */
    private double singleScore;
    /**
     *  填空题
     */
    private int completeNum;

    /**
     *  每题分值
     */
    private double completeScore;

    /**
     * 判断题
     */
    private int judgementNum;

    /**
     *  每题分值
     */
    private double judgementScore;

    /**
     * 多选题
     */
    private int multipleNum;

    /**
     *  每题分值
     */
    private double multipleScore;
    /**
     *  主观题(简答题)
     */
    private int subjectiveNum;

    /**
     *  每题分值
     */
    private double subjectiveScore;

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

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
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

    public int getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(int singleNum) {
        this.singleNum = singleNum;
    }

    public double getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(double singleScore) {
        this.singleScore = singleScore;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public double getCompleteScore() {
        return completeScore;
    }

    public void setCompleteScore(double completeScore) {
        this.completeScore = completeScore;
    }

    public int getJudgementNum() {
        return judgementNum;
    }

    public void setJudgementNum(int judgementNum) {
        this.judgementNum = judgementNum;
    }

    public double getJudgementScore() {
        return judgementScore;
    }

    public void setJudgementScore(double judgementScore) {
        this.judgementScore = judgementScore;
    }

    public int getMultipleNum() {
        return multipleNum;
    }

    public void setMultipleNum(int multipleNum) {
        this.multipleNum = multipleNum;
    }

    public double getMultipleScore() {
        return multipleScore;
    }

    public void setMultipleScore(double multipleScore) {
        this.multipleScore = multipleScore;
    }

    public int getSubjectiveNum() {
        return subjectiveNum;
    }

    public void setSubjectiveNum(int subjectiveNum) {
        this.subjectiveNum = subjectiveNum;
    }

    public double getSubjectiveScore() {
        return subjectiveScore;
    }

    public void setSubjectiveScore(double subjectiveScore) {
        this.subjectiveScore = subjectiveScore;
    }

    public List<String> getPoints() {
        return points;
    }

    public void setPoints(List<String> points) {
        this.points = points;
    }
}
