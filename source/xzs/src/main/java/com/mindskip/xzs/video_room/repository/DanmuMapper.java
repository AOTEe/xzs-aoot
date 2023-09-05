package com.mindskip.xzs.video_room.repository;

import com.mindskip.xzs.video_room.bean.Danmu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DanmuMapper {
    boolean post(Danmu danmu);

    List<Danmu> getDanmuListByVideoId(String videoId);
}
