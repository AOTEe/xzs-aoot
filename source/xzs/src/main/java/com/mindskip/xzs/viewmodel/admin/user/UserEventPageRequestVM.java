package com.mindskip.xzs.viewmodel.admin.user;

import com.mindskip.xzs.base.BasePage;


public class UserEventPageRequestVM extends BasePage {

    private String userId;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
