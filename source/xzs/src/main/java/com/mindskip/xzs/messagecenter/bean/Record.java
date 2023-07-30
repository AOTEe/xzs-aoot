package com.mindskip.xzs.messagecenter.bean;

/**
 * 私聊实体类
 */
public class Record {

    /**
     * 主键标识
     */
    private String id  ;

    /**
     * 所属会话标识
     */
    private String chatId  ;

    /**
     * 消息标识 text:普通文本 image:图片 file：文件
     */
    private String msgType ;

    /**
     * 发送者标识
     */
    private String senderId ;

    /**
     * 发送名称
     */
    private String senderName  ;
    /**
     * 发送者头像
     */
    private String senderPhoto  ;
    /**
     * 接收者标识
     */
    private String receiverId ;

    /**
     * 接收者名称
     */
    private String receiverName ;
    /**
     * 接收者头像
     */
    private String receiverPhoto ;

    /**
     * 消息状态:是否已读
     */
    private String msgStatus  ;

    /**
     * 消息内容 文件和图片存放的是地址
     */
    private String msgContent ;

    /**
     * 文件（图片）名称
     */
    private String msgFileName  ;

    /**
     * 文件（图片）路径
     */
    private String msgFilePath  ;
    /**
     * 文件（图片）大小
     */
    private String msgFileSize   ;

    /**
     * 记录时间
     */
    private String msgTime  ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhoto() {
        return senderPhoto;
    }

    public void setSenderPhoto(String senderPhoto) {
        this.senderPhoto = senderPhoto;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhoto() {
        return receiverPhoto;
    }

    public void setReceiverPhoto(String receiverPhoto) {
        this.receiverPhoto = receiverPhoto;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgFileName() {
        return msgFileName;
    }

    public void setMsgFileName(String msgFileName) {
        this.msgFileName = msgFileName;
    }

    public String getMsgFilePath() {
        return msgFilePath;
    }

    public void setMsgFilePath(String msgFilePath) {
        this.msgFilePath = msgFilePath;
    }

    public String getMsgFileSize() {
        return msgFileSize;
    }

    public void setMsgFileSize(String msgFileSize) {
        this.msgFileSize = msgFileSize;
    }

    public String getMsg_time() {
        return msgTime;
    }

    public void setMsg_time(String msgTime) {
        this.msgTime = msgTime;
    }
}
