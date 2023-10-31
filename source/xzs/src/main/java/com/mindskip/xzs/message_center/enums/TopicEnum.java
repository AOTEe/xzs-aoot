package com.mindskip.xzs.message_center.enums;

public enum TopicEnum {
    VIDEO("VIDEO"),
    COMMENT("comment"),
    QUESTION("question");

    private String value;

    TopicEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
