package com.mindskip.xzs.video_room.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mindskip.xzs.context.WebContext;
import com.mindskip.xzs.utility.SnowFlakeGenerateIDUtil;
import com.mindskip.xzs.video_room.bean.Danmu;
import com.mindskip.xzs.video_room.repository.DanmuMapper;
import com.mindskip.xzs.video_room.service.DanmuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DanmuServiceImpl implements DanmuService {

    @Autowired
    DanmuMapper danmuMapper;
    @Autowired
    WebContext webContext;

    @Override
    public boolean post(Danmu danmu) {
        danmu.setId(new SnowFlakeGenerateIDUtil().generateID());
        danmu.setSenderId(webContext.getCurrentUser().getId());
        return danmuMapper.post(danmu);
    }

    @Override
    public List<Danmu> getDanmuListByVideoId(String videoId){

        List<Danmu> danmuList = danmuMapper.getDanmuListByVideoId(videoId);

        return danmuList;
    }
}
