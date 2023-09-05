package com.mindskip.xzs.message_center.repository;

import com.mindskip.xzs.message_center.bean.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    public void post(Record record);

    public List<Record> getRecordsByBothSideId(String myId, String oppositeId);

    public int getRecordsCountByBothSideId(String myId, String oppositeId);

    public Record getLastRecordByBothSideId(String myId, String oppositeId);

    public int getUnReadCountByBothSideId(String myId, String oppositeId);

    public int getUnReadCountAll(String myId);

}
