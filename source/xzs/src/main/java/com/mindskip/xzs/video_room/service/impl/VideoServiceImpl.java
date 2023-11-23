package com.mindskip.xzs.video_room.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mindskip.xzs.domain.Tag;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.message_center.service.LikeService;
import com.mindskip.xzs.redis.RedisUtil;
import com.mindskip.xzs.service.TagService;
import com.mindskip.xzs.service.UserService;
import com.mindskip.xzs.utility.DateTimeUtil;
import com.mindskip.xzs.utility.JsonUtil;
import com.mindskip.xzs.utility.StringUtil;
import com.mindskip.xzs.video_room.bean.*;
import com.mindskip.xzs.video_room.repository.VideoMapper;
import com.mindskip.xzs.video_room.service.VideoService;
import com.mindskip.xzs.video_room.util.VideoUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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


    private static final String VIDEO_UPLOAD_PATH = "/static/video/"+DateTimeUtil.year(new Date())+"/";

    private static final String IMAGE_UPLOAD_PATH = "/static/image/"+DateTimeUtil.year(new Date())+"/";

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


    public VideoCategory saveCategory(VideoCategory category){
        String time = DateTimeUtil.dateFormat(new Date());
        if (StringUtils.isEmpty(category.getId())){
            category.setId(String.valueOf(UUID.randomUUID()));
            category.setName(category.getName());
            category.setCreateTime(time);
            category.setUpdateTime(time);
            //insert
            videoMapper.insertCategory(category);
        }else {
            category.setUpdateTime(time);
            category.setName(category.getName());
            //update
            videoMapper.updateCategory(category);
        }
        return category;
    }

    @Override
    public List<VideoCategory> categoryList() {

        List<VideoCategory> categories = videoMapper.categoryList();
        return categories;
    }

    @Override
    public Video upload(MultipartFile file){

        String originalFileName = file.getOriginalFilename();
        String systemPath = this.getClass().getResource("/").getPath();
        String fileUrl = VIDEO_UPLOAD_PATH + UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        System.out.println(fileUrl);
        File localFile = new File(systemPath + fileUrl);
        File parentFile = localFile.getParentFile();
        if (!parentFile.exists())
            parentFile.mkdirs();
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取视频第一帧作为封面
        String coverUrl = IMAGE_UPLOAD_PATH + UUID.randomUUID() + ".jpg";
        VideoUtil.getVideoPicture(systemPath + fileUrl, systemPath + coverUrl);
        Video video = new Video();
        video.setPath(fileUrl);
        video.setCover(coverUrl);
        return video;
    }

    @Override
    public void save(VideoEditVO videoEditVO,User user){
        Video  video = new Video();
        video.setId(UUID.randomUUID().toString());
        video.setPublisher(user.getId());
        video.setPublishTime(DateTimeUtil.dateFormat(new Date()));
        video.setTitle(videoEditVO.getTitle());
        video.setIntroduction(videoEditVO.getIntroduction());
        video.setPath(videoEditVO.getPath());
        video.setCover(videoEditVO.getCover());
        video.setType(videoEditVO.getType());
        video.setCategory(videoEditVO.getCategory());
        video.setTags(StringUtil.list2String(videoEditVO.getTags(),","));

        videoMapper.save(video);
    }

    @Override
    public String coverUpload(MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        String systemPath = this.getClass().getResource("/").getPath();
        String fileUrl = IMAGE_UPLOAD_PATH + UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        System.out.println(fileUrl);
        File localFile = new File(systemPath + fileUrl);
        File parentFile = localFile.getParentFile();
        if (!parentFile.exists())
            parentFile.mkdirs();
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

}
