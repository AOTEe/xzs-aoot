package com.mindskip.xzs.message_center.repository;

import com.mindskip.xzs.message_center.bean.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    void insert(Like like);

    void updateStatus(Like like);

    int getRelation(String topicId,String userId);

    List<String>  getLikes(String topicId);

    List<String>  getDislikes(String topicId);

    Like getLike(String topicId, String userId);
}
