package com.mindskip.xzs.message_center.domain.entity;

public class Emote {
    private String id;
    private String type;
    private String text;
    private String url;
    private String jumpTitle;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJumpTitle() {
        return jumpTitle;
    }

    public void setJumpTitle(String jumpTitle) {
        this.jumpTitle = jumpTitle;
    }
}
