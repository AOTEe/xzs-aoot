package com.mindskip.xzs.message_center.service;

import java.util.List;

public interface LikeService {
    void like(String topicId, String userId, String topicType);

    void cancelLike(String topicId, String userId, String topicType);

    void cancelDislike(String topicId, String userId, String topicType);

    void dislike(String topicId, String userId, String topicType);

    List getLikes(String topicId);

    List getDislikes(String topicId);

    void likeToDB(String topicId, String userId, String topicType);

    void cancelLikeToDB(String topicId, String userId);

    void dislikeToDB(String topicId, String userId, String topicType);

    void cancelDislikeToDB(String topicId, String userId);

    void commentAction(String commentId,String userId,int action);
}
