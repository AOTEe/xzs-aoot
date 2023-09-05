package com.mindskip.xzs.video_room.service.impl;

import com.mindskip.xzs.domain.Tag;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.service.TagService;
import com.mindskip.xzs.service.UserService;
import com.mindskip.xzs.video_room.bean.Video;
import com.mindskip.xzs.video_room.bean.VideoVO;
import com.mindskip.xzs.video_room.repository.VideoMapper;
import com.mindskip.xzs.video_room.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {


    @Autowired
    VideoMapper videoMapper;
    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;

    @Override
    public VideoVO getVideoVO(String videoId) {

        Video video = videoMapper.getVideoById(videoId);
        VideoVO videoVO = new VideoVO();
        if (video !=null){
            videoVO.setId(video.getId());
            videoVO.setPublisher(video.getPublisher());
            videoVO.setPublishTime(video.getPublishTime());
            videoVO.setTitle(video.getTitle());
            videoVO.setIntroduction(video.getIntroduction());
            videoVO.setPath(video.getPath());
            videoVO.setLikesNum(video.getLikesNum());
            videoVO.setViewsNum(video.getViewsNum());
            videoVO.setCollectionsNum(video.getCollectionsNum());
            videoVO.setTags(video.getTags());
            //获取用户、tags名称 video.getId()
            if (!StringUtils.isEmpty(video.getPublisher())){
                User publisher = userService.getUserById(video.getPublisher());
                if (publisher!=null)
                    videoVO.setPublisherName(publisher.getUserName());
            }
            if (!StringUtils.isEmpty(video.getTags())){
                List<Tag> tagsList = tagService.getTagsByIds(video.getTags().split(","));
                String tags= "";
                String tagsName = "";
                for (Tag tag : tagsList) {
                    tags += ',' + tag.getTagId();
                    tagsName += ',' + tag.getTagName();
                }
                if (tags.length()>0){
                    videoVO.setTags(tags.substring(1,tags.length()));
                    videoVO.setTagsName(tagsName.substring(1,tagsName.length()));
                }
            }

        }
        return videoVO;
    }
}
