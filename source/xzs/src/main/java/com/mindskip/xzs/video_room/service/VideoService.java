package com.mindskip.xzs.video_room.service;


import com.mindskip.xzs.video_room.bean.VideoRelationVO;
import com.mindskip.xzs.video_room.bean.VideoVO;

public interface VideoService {
    public VideoVO getVideoVO(String videoId);

    public VideoRelationVO videoRelation(String videoId,String userId);
}
