package com.mindskip.xzs.message_center.service.impl;

import com.github.pagehelper.PageHelper;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.message_center.domain.entity.Comment;
import com.mindskip.xzs.message_center.domain.vo.CommentVO;
import com.mindskip.xzs.message_center.domain.vo.Content;
import com.mindskip.xzs.message_center.domain.vo.Member;
import com.mindskip.xzs.message_center.repository.CommentMapper;
import com.mindskip.xzs.message_center.service.CommentService;
import com.mindskip.xzs.repository.UserMapper;
import com.mindskip.xzs.utility.DateTimeUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<CommentVO> getCommentsByTopicId(String topic,String userId) {

        List<CommentVO> commentVOList = commentMapper.getCommentsByTopicId(topic,userId);

        return commentVOList;
    }

    /**
     *
     * @param parent
     * @param topicId
     * @param topicType
     * @param content
     * @param atName
     * @return
     */
    public CommentVO add(String topicId, String topicType, String content,String userId, String atName,String parent){
        //插入一条评论数据

        Comment comment = new Comment();
        comment.setId(String.valueOf(UUID.randomUUID()));
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCommentTime(DateTimeUtil.dateFormat(new Date()));
        comment.setPreviousCommentId(parent);
        comment.setTopicId(topicId);
        comment.setTopicType(topicType);
        comment.setAt(atName);
        commentMapper.add(comment);
        //@at 暂不实现
        //返回前端需要的数据结构
        /**
         * replies : {
         *
         * }
         */
        CommentVO commentVO = new CommentVO();
        commentVO.setTopicType(topicType);
        commentVO.setTopicId(topicId);
        commentVO.setCommentTime(comment.getCommentTime());
        commentVO.setId(comment.getId());
        //emote要怎么实现（数据库存储表情包？）
        Content conTenT = new Content();
        conTenT.setMessage(content);
        commentVO.setContent(conTenT);

        User user = userMapper.getUserById(userId);
        Member member = new Member();
        member.setUname(user.getUserName());
        member.setUid(userId);
        member.setSex(user.getSex().equals("1")?"男":"女");
        member.setAvatar(user.getImagePath());
        commentVO.setMember(member);

        //
        return  commentVO;
    }

    @Override
    public List<CommentVO> moreComments(String rootCommentId,int index) {

        //默认10条每页
        PageHelper.offsetPage(10 * (index - 1) , 10);
        List<CommentVO> comments = commentMapper.moreComments(rootCommentId);

        return comments;
    }
}
