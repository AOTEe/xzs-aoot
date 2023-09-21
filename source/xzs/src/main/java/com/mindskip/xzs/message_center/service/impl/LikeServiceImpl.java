package com.mindskip.xzs.message_center.service.impl;

import com.mindskip.xzs.message_center.bean.Like;
import com.mindskip.xzs.message_center.repository.LikeMapper;
import com.mindskip.xzs.message_center.service.LikeService;
import com.mindskip.xzs.redis.RedisUtil;
import com.mindskip.xzs.utility.JsonUtil;
import com.mindskip.xzs.utility.TimeUtil;
import com.mindskip.xzs.video_room.bean.VideoVO;
import com.mindskip.xzs.video_room.service.VideoService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService {


    @Autowired
    LikeMapper likeMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    VideoService videoService;



    @Override
    public void like(String topicId, String userId, String topicType) {

        //1、2 得是原子操作

        boolean cancelDislike = false;

        //1 缓存中的文件点赞列表add(userId)
        Set<Object> likeSet = redisUtil.sGet("VIDEO_LIKE_" + topicId);
        if (likeSet!=null)
            likeSet.add(userId);
        else {
            List<String> likes = likeMapper.getLikes(topicId);
            likeSet = new HashSet<>(likes);
            likeSet.add(userId);
        }
        redisUtil.sSet("VIDEO_LIKE_"+topicId,userId);


        //1.1原先是否点踩,有就移除
        Set<Object> dislikeSet = redisUtil.sGet("VIDEO_DISLIKE_" + topicId);
        if (dislikeSet == null){
            List<String> dislikes = likeMapper.getDislikes(topicId);
            dislikeSet = new HashSet<>(dislikes);
        }
        if (dislikeSet.contains(userId)){
            cancelDislike = true;
            dislikeSet.remove(userId);
            redisUtil.setRemove("VIDEO_DISLIKE_"+topicId,userId);
        }



        //2 文章的点赞总数+1
        VideoVO videoVO;
        String infoJson = (String) redisUtil.get("VIDEO_INFO_" + topicId);
        if (infoJson != null) {
            videoVO = JsonUtil.toJsonObject(infoJson, VideoVO.class);
        } else {
            videoVO = videoService.getVideoVO(topicId);
        }
        videoVO.setLikesNum(videoVO.getLikesNum() + 1);
        if (cancelDislike)
            videoVO.setLikesNum(videoVO.getDislikesNum() - 1);
        redisUtil.set("VIDEO_INFO_" + topicId, JsonUtil.toJsonStr(videoVO));


        //3 发布事件 （发布顺序是怎么样的）
        if (cancelDislike){
            //取消点踩事件
        }
        //点赞事件

        //持久化的操作放在消息队列中
//        Like like = likeMapper.getLike(topicId,userId);
//        if (like == null){
//            like.setId(String.valueOf(UUID.randomUUID()));
//            like.setStatus(1);
//            like.setTopicId(topicId);
//            like.setUserId(userId);
//            like.setTopicType(topicType);
//            like.setOperateTime(TimeUtil.now());
//            likeMapper.insert(like);
//        }else {
//            like.setStatus(1);
//            like.setOperateTime(TimeUtil.now());
//            likeMapper.updateStatus(like);
//        }

    }


    @Override
    public void cancelLike(String topicId, String userId, String topicType) {

        //取消点赞
        //1 点赞列表移除用户
        Set<Object> likeSet = redisUtil.sGet("VIDEO_LIKE_" + topicId);
        if (likeSet == null){
            List<String> likes = likeMapper.getLikes(topicId);
            likeSet = new HashSet<>(likes);
        }
        if (likeSet.contains(userId)){
            likeSet.remove(userId);
            redisUtil.sSet("VIDEO_LIKE_"+topicId,likeSet);
        }

        //2 视频点赞总数-1
        VideoVO videoVO;
        String infoJson = (String) redisUtil.get("VIDEO_INFO_" + topicId);
        if (infoJson != null) {
            videoVO = JsonUtil.toJsonObject(infoJson, VideoVO.class);
        } else {
            videoVO = videoService.getVideoVO(topicId);
        }
        videoVO.setLikesNum(videoVO.getLikesNum() - 1);
        redisUtil.set("VIDEO_INFO_" + topicId, JsonUtil.toJsonStr(videoVO));

//        Like like = likeMapper.getLike(topicId,userId);
//        if (like != null){
//            like.setStatus(0);
//            like.setOperateTime(TimeUtil.now());
//            likeMapper.updateStatus(like);
//        }

    }
    @Override
    public void cancelDislike(String topicId, String userId, String topicType) {

        //取消点踩
        //1 点踩列表移除用户
        Set<Object> dislikeSet = redisUtil.sGet("VIDEO_DISLIKE_" + topicId);
        if (dislikeSet == null){
            List<String> dislikes = likeMapper.getDislikes(topicId);
            dislikeSet = new HashSet<>(dislikes);
        }
        if (dislikeSet.contains(userId)){
            dislikeSet.remove(userId);
            redisUtil.sSet("VIDEO_DISLIKE_"+topicId,dislikeSet);
        }

        //2 视频点踩总数-1
        VideoVO videoVO;
        String infoJson = (String) redisUtil.get("VIDEO_INFO_" + topicId);
        if (infoJson != null) {
            videoVO = JsonUtil.toJsonObject(infoJson, VideoVO.class);
        } else {
            videoVO = videoService.getVideoVO(topicId);
        }
        videoVO.setLikesNum(videoVO.getDislikesNum() - 1);
        redisUtil.set("VIDEO_INFO_" + topicId, JsonUtil.toJsonStr(videoVO));

//        Like like = likeMapper.getLike(topicId,userId);
//        if (like != null){
//            like.setStatus(0);
//            like.setOperateTime(TimeUtil.now());
//            likeMapper.updateStatus(like);
//        }

    }


    @Override
    public void dislike(String topicId, String userId, String topicType) {


        //1、2 得是原子操作

        boolean cancelLike = false;

        //1 缓存中的文件点踩列表add(userId)
        Set<Object> dislikeSet = redisUtil.sGet("VIDEO_DISLIKE_" + topicId);
        if (dislikeSet==null){
            List<String> likes = likeMapper.getLikes(topicId);
            dislikeSet = new HashSet<>(likes);
        }
        dislikeSet.add(userId);
        redisUtil.sSet("VIDEO_DISLIKE_"+topicId,dislikeSet);


        //1.1原先是否点赞,有就移除
        Set<Object> likeSet = redisUtil.sGet("VIDEO_LIKE_" + topicId);
        if (likeSet == null){
            List<String> likes = likeMapper.getLikes(topicId);
            likeSet = new HashSet<>(likes);
        }
        if (likeSet.contains(userId)){
            cancelLike = true;
            likeSet.remove(userId);
            redisUtil.sSet("VIDEO_LIKE_"+topicId,likeSet);
        }

        //2 文章的点踩总数+1
        VideoVO videoVO;
        String infoJson = (String) redisUtil.get("VIDEO_INFO_" + topicId);
        if (infoJson != null) {
            videoVO = JsonUtil.toJsonObject(infoJson, VideoVO.class);
        } else {
            videoVO = videoService.getVideoVO(topicId);
        }
        videoVO.setLikesNum(videoVO.getDislikesNum() + 1);
        if (cancelLike)
            videoVO.setLikesNum(videoVO.getLikesNum() - 1);
        redisUtil.set("VIDEO_INFO_" + topicId, JsonUtil.toJsonStr(videoVO));


        //3 发布事件 （发布顺序是怎么样的）
        if (cancelLike){
            //取消点赞事件
        }
        //点踩事件

//        Like like = likeMapper.getLike(topicId,userId);
//        if (like == null){
//            like.setId(String.valueOf(UUID.randomUUID()));
//            like.setStatus(-1);
//            like.setTopicId(topicId);
//            like.setUserId(userId);
//            like.setTopicType(topicType);
//            like.setOperateTime(TimeUtil.now());
//            likeMapper.insert(like);
//        }else {
//            like.setStatus(-1);
//            like.setOperateTime(TimeUtil.now());
//            likeMapper.updateStatus(like);
//        }
    }

    @Override
    public List getLikes(String topicId) {
        return likeMapper.getLikes(topicId);
    }

    @Override
    public List getDislikes(String topicId) {
        return likeMapper.getDislikes(topicId);
    }

}
