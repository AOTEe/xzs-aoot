package com.mindskip.xzs.bean;

import com.mindskip.xzs.base.BasePage;
import com.mindskip.xzs.viewmodel.BaseVM;

public class Org  extends BasePage {
    private String uuid;
    private String orgName;
    //0年级 1班级
    private String orgType;
    //orgType=1 表示所属年级
    private String belongTo;

    private String belongToName;
    private String createTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getBelongToName() {
        return belongToName;
    }

    public void setBelongToName(String belongToName) {
        this.belongToName = belongToName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
