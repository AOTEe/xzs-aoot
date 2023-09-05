package com.mindskip.xzs.video_room.bean;

public class VideoVO {

    private String id;
    private String publisher;
    private String publisherName;
    private String publishTime;
    private String title;
    private String introduction;
    private String path;
    private int likesNum;
    private int viewsNum;
    private int collectionsNum;
    private String tags;
    private String tagsName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    public int getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(int viewsNum) {
        this.viewsNum = viewsNum;
    }

    public int getCollectionsNum() {
        return collectionsNum;
    }

    public void setCollectionsNum(int collectionsNum) {
        this.collectionsNum = collectionsNum;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }
}
