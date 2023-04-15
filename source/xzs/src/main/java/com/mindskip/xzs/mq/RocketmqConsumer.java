//package com.mindskip.xzs.mq;
//
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RocketMQMessageListener(consumerGroup = "bootGroup",topic = "TopicOne")
//public class RocketmqConsumer implements RocketMQListener {
//
//    @Override
//    public void onMessage(Object message) {
//        System.out.println("消费消息:"+message);
//    }
//}
