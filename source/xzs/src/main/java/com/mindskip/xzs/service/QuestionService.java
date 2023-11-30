package com.mindskip.xzs.service;

import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.viewmodel.admin.question.QuestionEditRequestVM;
import com.mindskip.xzs.viewmodel.admin.question.QuestionPageRequestVM;
import com.github.pagehelper.PageInfo;

import java.io.File;
import java.util.List;

public interface QuestionService extends BaseService<Question> {

    PageInfo<Question> page(QuestionPageRequestVM requestVM);

    Question insertFullQuestion(QuestionEditRequestVM model, String userId);

    Question updateFullQuestion(QuestionEditRequestVM model);

    QuestionEditRequestVM getQuestionEditRequestVM(String questionId);

    QuestionEditRequestVM getQuestionEditRequestVM(Question question);

    Integer selectAllCount();

    List<Integer> selectMothCount();

    void batchImportQuestionFromExcel(File file, User user);

     List<Question> queryQuestionsBySubjectAndPoints(Integer type,String subjectId,List<String> points);
}
