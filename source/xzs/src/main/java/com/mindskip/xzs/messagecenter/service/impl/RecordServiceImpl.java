package com.mindskip.xzs.messagecenter.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mindskip.xzs.context.WebContext;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.messagecenter.bean.RecentChats;
import com.mindskip.xzs.messagecenter.bean.RecentChatsVO;
import com.mindskip.xzs.messagecenter.bean.Record;
import com.mindskip.xzs.messagecenter.repository.RecentChatsMapper;
import com.mindskip.xzs.messagecenter.repository.RecordMapper;
import com.mindskip.xzs.messagecenter.service.RecordService;
import com.mindskip.xzs.repository.UserMapper;
import com.mindskip.xzs.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl  implements RecordService {


    @Autowired
    RecentChatsMapper recentChatsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    WebContext webContext;


    @Override
    public void post(Record record) {
        recordMapper.post(record);
    }


    public List<RecentChatsVO> getRecentChats(String userId){


        RecentChats recentChats = recentChatsMapper.get(userId);
        //根据 recentChats 填充 recentChatsVO
        //record 、 user 表
        List<RecentChatsVO> recentChatsVOList = new ArrayList<>();
        if (recentChats != null && !StringUtils.isEmpty(recentChats.getRecentChats())) {
            String[] recentChatsArr = recentChats.getRecentChats().split("、");
            for (String oppositeId : recentChatsArr) {
                RecentChatsVO recentChatsVO = new RecentChatsVO();

                User user = userMapper.getUserById(userId);
                //最后一条聊天记录
                Record lastRecord = recordMapper.getLastRecordByBothSideId(userId,oppositeId);
                //未读消息数量
                int unReadCount = recordMapper.getUnReadCountByBothSideId(userId,oppositeId);

                recentChatsVO.setOppositeUserId(oppositeId);
                recentChatsVO.setOppositeUserName(user.getUserName());
                recentChatsVO.setOppositeUserPhoto(user.getImagePath());
                recentChatsVO.setLastMsg(lastRecord.getMsgContent());
                recentChatsVO.setLastMsgTime(lastRecord.getMsg_time());
                recentChatsVO.setUnreadCount(unReadCount);

                recentChatsVOList.add(recentChatsVO);
            }

        }
        return recentChatsVOList;
    }

    public List<Record> getRecordsByBothSideId(String myId,String oppositeId,int index,int newMsgCount){

        //聊天的时候收到新的消息或者是发送新消息，总数发生变化 +newMsgCount
        //limit  (size*(index-1)+newMsgCount),10
        //分页查询 每页10条记录  有没有更方便的分页查询方法!（感觉好麻烦）
        int pageSize = 10;
        PageHelper.offsetPage(pageSize * (index - 1) + newMsgCount, pageSize);
        List<Record> records = recordMapper.getRecordsByBothSideId(myId, oppositeId);
        return records;
    }

}
