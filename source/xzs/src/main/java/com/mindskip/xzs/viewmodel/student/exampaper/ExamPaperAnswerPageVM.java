package com.mindskip.xzs.viewmodel.student.exampaper;

import com.mindskip.xzs.base.BasePage;

public class ExamPaperAnswerPageVM extends BasePage {

    private String subjectId;

    private String createUser;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
