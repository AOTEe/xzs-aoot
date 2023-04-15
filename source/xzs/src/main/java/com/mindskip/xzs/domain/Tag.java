package com.mindskip.xzs.domain;

import com.mindskip.xzs.base.BasePage;

public class Tag extends BasePage {
    private String tagId;
    private String tagName;
    private String subjectId;
    private String subjectName;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagTitle) {
        this.tagName = tagTitle;
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
}
