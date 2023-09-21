package com.mindskip.xzs.video_room.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mindskip.xzs.domain.Tag;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.message_center.service.LikeService;
import com.mindskip.xzs.redis.RedisUtil;
import com.mindskip.xzs.service.TagService;
import com.mindskip.xzs.service.UserService;
import com.mindskip.xzs.utility.JsonUtil;
import com.mindskip.xzs.video_room.bean.Video;
import com.mindskip.xzs.video_room.bean.VideoRelationVO;
import com.mindskip.xzs.video_room.bean.VideoVO;
import com.mindskip.xzs.video_room.repository.VideoMapper;
import com.mindskip.xzs.video_room.service.VideoService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VideoServiceImpl implements VideoService {


    @Autowired
    VideoMapper videoMapper;
    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    LikeService likeService;

    @Override
    public VideoVO getVideoVO(String videoId) {

        VideoVO videoVO = new VideoVO();
        String cacheInfo = (String) redisUtil.get("VIDEO_INFO_" + videoId);
        if (!StringUtils.isEmpty(cacheInfo)){
            videoVO  = JsonUtil.toJsonObject(cacheInfo,VideoVO.class);
            return videoVO;
        }
        Video video = videoMapper.getVideoById(videoId);
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
            redisUtil.set("VIDEO_INFO_"+videoId,JsonUtil.toJsonStr(videoVO));
        }
        return videoVO;
    }

    @Override
    public VideoRelationVO videoRelation(String videoId, String userId) {

        //是否点赞、收藏
        boolean like =false;
        boolean dislike = false;
        boolean favorite = false;

        VideoRelationVO relationVO = new VideoRelationVO();

        //VIDEO_LIKE_+ID
        Set<Object> likeSet = redisUtil.sGet("VIDEO_LIKE_" + videoId);
        if ( likeSet==null || likeSet.size()==0){
            likeSet = new HashSet<>(likeService.getLikes(videoId));
        }
        if (likeSet.contains(userId))
            like = true;
        //dislike
        if (!like){
            Set<Object> dislikeSet = redisUtil.sGet("VIDEO_DISLIKE_" + videoId);
            if(dislikeSet==null || dislikeSet.size()==0){
                dislikeSet = new HashSet<>(likeService.getDislikes(videoId));
            }
            if (dislikeSet.contains(userId))
                dislike = true;
        }
        //favorite不走redis缓存
        //收藏先不做
        relationVO.setDislike(dislike);
        relationVO.setLike(like);
        relationVO.setFavorite(favorite);
        return relationVO;
    }
}
