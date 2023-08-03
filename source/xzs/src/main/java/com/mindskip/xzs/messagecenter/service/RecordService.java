package com.mindskip.xzs.messagecenter.service;

import com.mindskip.xzs.messagecenter.bean.RecentChatsVO;
import com.mindskip.xzs.messagecenter.bean.Record;

import java.util.List;

public interface RecordService {

    public void post(Record record);

    public List<Record> getRecordsByBothSideId(String myId,String oppositeId,int index,int newMsgCount);

    public List<RecentChatsVO> getRecentChats(String userId);

    public void sendMsg(Record record);

}
