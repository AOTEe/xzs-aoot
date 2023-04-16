package com.mindskip.xzs.viewmodel.admin.task;

import com.mindskip.xzs.viewmodel.admin.exam.ExamResponseVM;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class TaskRequestVM {

    private String id;

    //@NotNull
    private Integer gradeLevel;

    @NotNull
    private String title;

    private String subjectId;

    private String subjectName;

    private String orgId;

    private String orgName;

    @Size(min = 1, message = "请添加试卷")
    @Valid
    private List<ExamResponseVM> paperItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<ExamResponseVM> getPaperItems() {
        return paperItems;
    }

    public void setPaperItems(List<ExamResponseVM> paperItems) {
        this.paperItems = paperItems;
    }
}
