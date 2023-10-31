package com.mindskip.xzs.message_center.domain.vo;

import java.util.List;
import java.util.Map;
import com.mindskip.xzs.message_center.domain.entity.Emote;

public class Content {
    private String message;

    /**
     * @人员列表id
     */
    private List<String> members;

    private Map<String,Emote> emote;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Map<String, Emote> getEmote() {
        return emote;
    }

    public void setEmote(Map<String, Emote> emote) {
        this.emote = emote;
    }
}
