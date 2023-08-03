package com.mindskip.xzs.messagecenter.controller;

import com.mindskip.xzs.messagecenter.bean.RecentChatsVO;
import com.mindskip.xzs.messagecenter.bean.Record;
import com.mindskip.xzs.messagecenter.service.RecordService;
import com.mindskip.xzs.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/record")
@Controller
public class RecordController {


    @Autowired
    RecordService recordService;

    @ResponseBody
    @RequestMapping("/post")
    public Map<String,Object> post(@RequestBody Record record){

        return ResponseUtil.success();
    }

    /**
     * 获取最近的聊天列表
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRecentChats")
    public Map<String,Object>  getRecentChats(@RequestParam(required = false) String userId){

        List<RecentChatsVO> recentChats = recordService.getRecentChats(userId);

        return ResponseUtil.success(recentChats);
    }

    @ResponseBody
    @RequestMapping("/getRecords")
    public Map<String,Object>  getRecords(
            @RequestParam String userId,
            @RequestParam String oppositeUserId,
            @RequestParam int index,
            @RequestParam int newMsgCount){

        List<Record> records = recordService.getRecordsByBothSideId(userId, oppositeUserId,index,newMsgCount);

        return ResponseUtil.success(records);
    }

    @ResponseBody
    @RequestMapping("/sendMsg")
    public Map<String,Object> sendMsg(@RequestBody Record record){

        recordService.sendMsg(record);
        return ResponseUtil.success();
    }
}
