package com.mindskip.xzs.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mindskip.xzs.domain.ExamPaperAnswerInfo;
import com.mindskip.xzs.domain.UserEventLog;
import com.mindskip.xzs.event.CalculateExamPaperAnswerCompleteEvent;
import com.mindskip.xzs.listener.CalculateExamPaperAnswerListener;
import com.mindskip.xzs.service.ExamPaperAnswerService;
import com.mindskip.xzs.service.UserEventLogService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 参数一 ：consumeMode
 *      控制消费模式，您可以选择并发或有序接收消息。
 * 参数二：messageModel
 *      控制消息模式，
 *      广播模式：所有消费者都能接受到消息
 *      集群模式：无论有多少个消费者，只有一个消费者能够接收到消息。
 */
@Component
@RocketMQMessageListener(consumerGroup = "XZS", topic = RocketmqProducer.TEST_TOPIC)
public class RocketmqConsumer implements RocketMQListener<String> , RocketMQPushConsumerLifecycleListener {


    @Autowired
    UserEventLogService userEventLogService;
    @Autowired
    ExamPaperAnswerService examPaperAnswerService;

    @Override
    public void onMessage(String message) {

        //试卷入库
        System.out.println("入库成功");
        int a = 10/0;
        //记录日志
        System.out.println("日志记录成功");

//         JSONObject data = (JSONObject) JSON.toJSON(message);
//        UserEventLog userEventLog = JSON.parseObject((String) data.get("userEventLog"),UserEventLog.class);
//        ExamPaperAnswerInfo examPaperAnswerInfo = JSON.parseObject((String) data.get("examPaperAnswerInfo"),ExamPaperAnswerInfo.class);
//        //试卷结果做入库操作
//        examPaperAnswerService.insertExamPaperAnswer(examPaperAnswerInfo);
//        //记录用户操作日志
//        userEventLogService.insertByFilter(userEventLog);

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 每次拉取的间隔，单位为毫秒
        consumer.setPullInterval(1000);
        // 设置每次从队列中拉取的消息数为16
        consumer.setPullBatchSize(16);
    }
}
