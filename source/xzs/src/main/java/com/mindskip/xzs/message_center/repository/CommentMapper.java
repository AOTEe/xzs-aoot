package com.mindskip.xzs.message_center.repository;

import com.mindskip.xzs.message_center.domain.entity.Comment;
import com.mindskip.xzs.message_center.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void add(Comment comment);

    List<CommentVO> getCommentsByTopicId(String topicId,String userId);

    List<CommentVO> moreComments(String rootCommentId);

    void updateComment(Comment comment);

    Comment getCommentById(String commentId);

}
