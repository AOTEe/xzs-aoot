package com.mindskip.xzs.listener;

import com.mindskip.xzs.domain.*;
import com.mindskip.xzs.domain.enums.ExamPaperTypeEnum;
import com.mindskip.xzs.domain.enums.QuestionTypeEnum;
import com.mindskip.xzs.event.CalculateExamPaperAnswerCompleteEvent;
import com.mindskip.xzs.service.ExamPaperAnswerService;
import com.mindskip.xzs.service.ExamPaperQuestionCustomerAnswerService;
import com.mindskip.xzs.service.TaskExamCustomerAnswerService;
import com.mindskip.xzs.service.TextContentService;
import com.mindskip.xzs.utility.SnowFlakeGenerateIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @version 3.5.0
 * @description:  The type Calculate exam paper answer listener.
 * Copyright (C), 2020-2021, 武汉思维跳跃科技有限公司
 * @date 2021/12/25 9:45
 */
@Component
public class CalculateExamPaperAnswerListener implements ApplicationListener<CalculateExamPaperAnswerCompleteEvent> {

    private final ExamPaperAnswerService examPaperAnswerService;
    private final ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;
    private final TextContentService textContentService;
    private final TaskExamCustomerAnswerService examCustomerAnswerService;

    /**
     * Instantiates a new Calculate exam paper answer listener.
     *
     * @param examPaperAnswerService                 the exam paper answer service
     * @param examPaperQuestionCustomerAnswerService the exam paper question customer answer service
     * @param textContentService                     the text content service
     * @param examCustomerAnswerService              the exam customer answer service
     */
    @Autowired
    public CalculateExamPaperAnswerListener(ExamPaperAnswerService examPaperAnswerService, ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService, TextContentService textContentService, TaskExamCustomerAnswerService examCustomerAnswerService) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.examPaperQuestionCustomerAnswerService = examPaperQuestionCustomerAnswerService;
        this.textContentService = textContentService;
        this.examCustomerAnswerService = examCustomerAnswerService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(CalculateExamPaperAnswerCompleteEvent calculateExamPaperAnswerCompleteEvent) {
        Date now = new Date();
        //做examPaperAnswer、examPaperQuestionAnswers的插入操作
        ExamPaperAnswerInfo examPaperAnswerInfo = (ExamPaperAnswerInfo) calculateExamPaperAnswerCompleteEvent.getSource();
        ExamPaper examPaper = examPaperAnswerInfo.getExamPaper();
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        examPaperAnswer.setId(new SnowFlakeGenerateIDUtil().generateID());
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers = examPaperAnswerInfo.getExamPaperQuestionCustomerAnswers();

        examPaperAnswerService.insertByFilter(examPaperAnswer);
        //不需要将answer另外保存至一张表
//        examPaperQuestionCustomerAnswers.stream().filter(a -> QuestionTypeEnum.needSaveTextContent(a.getQuestionType())).forEach(d -> {
//            TextContent textContent = new TextContent(d.getAnswer(), now);
//            textContent.setId(new SnowFlakeGenerateIDUtil().generateID());
//            textContentService.insertByFilter(textContent);
//            d.setTextContentId(textContent.getId());
//            d.setAnswer(null);
//        });
        for (ExamPaperQuestionCustomerAnswer item : examPaperQuestionCustomerAnswers) {
//            try {
//                //这ID生成的方法有bug，时间间隔太短，ID会复用
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            String id = UUID.randomUUID().toString();
            item.setId(id);

            item.setExamPaperAnswerId(examPaperAnswer.getId());
        }
        examPaperQuestionCustomerAnswerService.insertList(examPaperQuestionCustomerAnswers);

        switch (ExamPaperTypeEnum.fromCode(examPaper.getPaperType())) {
            case Task: {
                examCustomerAnswerService.insertOrUpdate(examPaper, examPaperAnswer, now);
                break;
            }
            default:
                break;
        }
    }
}
