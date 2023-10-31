package com.mindskip.xzs.message_center.service;

import com.mindskip.xzs.message_center.domain.vo.RecentChatsVO;
import com.mindskip.xzs.message_center.domain.entity.Record;

import java.util.List;

public interface RecordService {

    public void post(Record record);

    public List<Record> getRecordsByBothSideId(String myId,String oppositeId,int index,int newMsgCount);

    public List<RecentChatsVO> getRecentChats(String userId);

    public void sendMsg(Record record);

}
