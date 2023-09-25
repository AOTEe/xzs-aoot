package com.mindskip.xzs.message_center.consumer;

import com.mindskip.xzs.message_center.bean.LikeMessageDTO;
import com.mindskip.xzs.message_center.service.LikeService;
import com.mindskip.xzs.mq.MQConstant;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = MQConstant.LIKE_GROUP,topic = MQConstant.LIKE_TOPIC)
public class LikeRelationConsumer implements RocketMQListener<LikeMessageDTO> {

    private static final Logger logger = LoggerFactory.getLogger(LikeRelationConsumer.class);
    @Autowired
    LikeService likeService;

    @Override
    public void onMessage(LikeMessageDTO message) {

        switch (message.getActionType()) {
            case "like":
                likeService.likeToDB(message.getTopicId(), message.getUserId(), message.getTopicType());
                break;
            case "dislike":
                likeService.dislikeToDB(message.getTopicId(), message.getUserId(), message.getTopicType());
                break;
            case "cancelLike":
                likeService.cancelLikeToDB(message.getTopicId(), message.getUserId());
                break;
            case "cancelDislike":
                likeService.cancelDislikeToDB(message.getTopicId(), message.getUserId());
                break;
        }
        logger.info("消费【LIKE_TOPIC】消息 类型：{},topicId{},userId{},topicType{}",
                message.getActionType(),
                message.getTopicId(),
                message.getUserId(),
                message.getTopicType());
    }
}
