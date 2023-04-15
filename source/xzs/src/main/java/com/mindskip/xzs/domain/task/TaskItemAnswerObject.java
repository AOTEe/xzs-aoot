package com.mindskip.xzs.domain.task;


public class TaskItemAnswerObject {
    private String examPaperId;
    private String examPaperAnswerId;
    private Integer status;

    public TaskItemAnswerObject(){

    }

    public TaskItemAnswerObject(String examPaperId, String examPaperAnswerId, Integer status) {
        this.examPaperId = examPaperId;
        this.examPaperAnswerId = examPaperAnswerId;
        this.status = status;
    }

    public String getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(String examPaperId) {
        this.examPaperId = examPaperId;
    }

    public String getExamPaperAnswerId() {
        return examPaperAnswerId;
    }

    public void setExamPaperAnswerId(String examPaperAnswerId) {
        this.examPaperAnswerId = examPaperAnswerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
