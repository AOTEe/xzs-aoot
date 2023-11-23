package com.mindskip.xzs.video_room.repository;

import com.mindskip.xzs.video_room.bean.Video;
import com.mindskip.xzs.video_room.bean.VideoCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {

    public Video getVideoById(String videoId);

    public int updateLikeNum(String videoId, int num);

    void insertCategory(VideoCategory category);

    void updateCategory(VideoCategory category);

    List<VideoCategory> categoryList();

    void save(Video video);
}
