package com.mindskip.xzs.message_center.service;

import com.mindskip.xzs.message_center.domain.vo.CommentVO;

import java.util.List;

public interface CommentService {
    List<CommentVO> getCommentsByTopicId(String topic,String userId);

    CommentVO add(String topicId, String topicType, String content,String userId, String atName, String parent);

    List<CommentVO> moreComments(String rootCommentId,int index);
}
