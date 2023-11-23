package com.mindskip.xzs.video_room.service;


import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.video_room.bean.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    public VideoVO getVideoVO(String videoId);

    public VideoRelationVO videoRelation(String videoId,String userId);

    public VideoCategory saveCategory(VideoCategory category);

    public List<VideoCategory> categoryList();

    public Video upload(MultipartFile file);

    public void save(VideoEditVO videoEditVO, User user);

    public String coverUpload(MultipartFile file);
}
