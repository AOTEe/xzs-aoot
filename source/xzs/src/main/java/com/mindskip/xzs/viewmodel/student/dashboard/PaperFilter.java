package com.mindskip.xzs.viewmodel.student.dashboard;


import java.util.Date;

public class PaperFilter {
    private String userId;
    private Date dateTime;
    private Integer examPaperType;
    private Integer gradeLevel;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getExamPaperType() {
        return examPaperType;
    }

    public void setExamPaperType(Integer examPaperType) {
        this.examPaperType = examPaperType;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}
