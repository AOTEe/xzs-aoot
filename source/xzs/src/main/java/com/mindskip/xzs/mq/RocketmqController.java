//package com.mindskip.xzs.mq;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//@RestController
//@RequestMapping("/MQ")
//public class RocketmqController {
//    private final String topic = "TopicOne";
//    @Resource
//    private RocketmqProducer producer;
//
//    @RequestMapping("/sendMessage")
//    public String sendMessage(String message) {
//        producer.sendMessage(topic, message);
//        return "消息发送完成";
//    }
//
//    //这个发送事务消息的例子中有很多问题，需要注意下。
//    @RequestMapping("/sendTransactionMessage")
//    public String sendTransactionMessage(String message) {
//        producer.sendMessageInTransaction(topic, message);
//        return "消息发送完成";
//    }
//
//}
