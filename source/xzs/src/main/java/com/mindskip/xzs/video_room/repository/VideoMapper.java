package com.mindskip.xzs.video_room.repository;

import com.mindskip.xzs.video_room.bean.Video;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoMapper {

    public Video getVideoById(String videoId);
}
