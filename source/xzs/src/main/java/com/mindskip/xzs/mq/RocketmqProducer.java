package com.mindskip.xzs.mq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RocketmqProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public static final String EXAM_TOPIC = "EXAM_TOPIC";

    public static final String TEST_TOPIC = "TEST_TOPIC";

    //发送消息的实例
    public void sendMessage(String topic,String msg){
        rocketMQTemplate.convertAndSend(topic,msg);
    }

    public void syncSendMessage(String topic,String message){
        SendResult sendResult = rocketMQTemplate.syncSend(topic, message);
        System.out.println(sendResult);
    }



    //发送是事务消息的实例
    public void sendMessageInTransaction(String topic,String msg) {
        String[] tags = {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder.withPayload(msg).build();
            String destination = topic + ":" + tags[i % tags.length];

            TransactionSendResult sendResult =
                    rocketMQTemplate.sendMessageInTransaction(destination, message, destination);

            System.out.printf("%s%n", sendResult);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
