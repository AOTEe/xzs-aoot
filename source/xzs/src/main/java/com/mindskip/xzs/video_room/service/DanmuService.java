package com.mindskip.xzs.video_room.service;

import com.mindskip.xzs.video_room.bean.Danmu;

import java.util.List;

public interface DanmuService {

    boolean post(Danmu danmu);

    List<Danmu> getDanmuListByVideoId(String videoId);
}
