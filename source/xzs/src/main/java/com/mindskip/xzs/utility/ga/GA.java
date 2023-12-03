package com.mindskip.xzs.utility.ga;

import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.service.QuestionService;
import com.mindskip.xzs.utility.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 遗传算法组卷实现类
 *
 */
@Component
public class GA {
    /**
     * 变异概率
     */
    private static final double mutationRate = 0.085;
    /**
     * 精英主义
     */
    private static final boolean elitism = true;
    /**
     * 淘汰数组大小
     */
    private static final int tournamentSize = 5;

    private static final Logger logger = LoggerFactory.getLogger(GA.class);

    @Autowired
    QuestionService questionService;


    public Population initPopulation(int populationSize,boolean initFlag , PaperRule rule ){

        Population population = new Population(populationSize);

        if (initFlag){
            Paper paper;
            Random random = new Random();
            for (int i = 0; i < populationSize; i++) {
                paper = new Paper();
                paper.setId(i + 1);
                while (paper.getTotalScore() != rule.getTotalMark()) {
                    paper.getQuestions().clear();
                    List<String> points = rule.getPoints();
                    QuestionTypeItem[] questionTypeItems = rule.getQuestionTypeItems();
                    for (QuestionTypeItem questionTypeItem : questionTypeItems) {
                        if (questionTypeItem.getNum()>0){
                            generateQuestion(questionTypeItem.getQuestionType(), random, questionTypeItem.getNum(), questionTypeItem.getScore(),rule.getSubjectId(), points, paper ,questionTypeItem);
                        }
                    }
                }
                // 计算试卷知识点覆盖率
                paper.setKpCoverage(rule);
                // 计算试卷适应度
                paper.setAdaptationDegree(rule, Population.KP_WEIGHT, Population.DIFFCULTY_WEIGHt);
                population.setPaper(i,paper);
            }

        }

        return population;
    }

    public void generateQuestion(int type, Random random, int questionNum, double score, String subjectId,
                                 List<String> points, Paper paper, QuestionTypeItem questionTypeItem) {

        List<Question> qList = questionService.queryQuestionsBySubjectAndPoints(type,subjectId,points );
        if (qList.size() < questionNum) {
            return;
        }
        Question tmpQuestion;
        for (int j = 0; j < questionNum; j++) {
            int index = random.nextInt(qList.size() - j);
            // 初始化分数
            qList.get(index).setScore((int) score);//每一个题型的初始分是一致的
            if (paper.containsQuestion(qList.get(index))){
                System.out.println("初始化时重复添加试题.....");
            }
            paper.addQuestion(qList.get(index),questionTypeItem);
            // 保证不会重复添加试题
            //随机选中的试题移动到最后面几位中,随机数生成的范围逐步减小
            tmpQuestion = qList.get(qList.size() - j - 1);
            qList.set(qList.size() - j - 1, qList.get(index));
            qList.set(index, tmpQuestion);



        }
    }

    // 进化种群
    public  Population evolvePopulation(Population pop, PaperRule rule) {
        Population newPopulation = new Population(pop.getSize());
        int elitismOffset;
        // 精英主义
        if (elitism) {
            elitismOffset = 1;
            // 保留上一代最优秀个体
            Paper fitness = pop.getFitness();
            fitness.setId(0);
            newPopulation.setPaper(0, fitness);
        }
        // 种群交叉操作，从当前的种群pop来创建下一代种群newPopulation
        for (int i = elitismOffset; i < newPopulation.getSize(); i++) {
            // 选择
            Paper parent1 = select(pop);// tips:挑选 随机选择1/2种群中最优秀个体
            Paper parent2 = select(pop);
            while (parent2.getId() == parent1.getId()) {
                parent2 = select(pop);
            }
            if (parent1.getTypeRelationSize()!=10 || parent2.getTypeRelationSize()!=10)
                System.out.println("父母不为10");
            // 交叉
            Paper child = crossover(parent1, parent2, rule);
            if (child.getTypeRelationSize()!=10 )
                System.out.println("孩子不为10");
            child.setId(i);
            newPopulation.setPaper(i, child);
            for (int i1 = 0; i1 < newPopulation.getSize(); i1++) {
                if (newPopulation.getPaper(i1)!=null&&newPopulation.getPaper(i1).getTypeRelationSize()!=10)
                    System.out.println("会不会进来这里呢......");
            }
        }
        // 种群变异操作
        Paper tmpPaper;
        for (int i = elitismOffset; i < newPopulation.getSize(); i++) {
            tmpPaper = newPopulation.getPaper(i);
            mutate(tmpPaper,rule.getPoints());
            // 计算知识点覆盖率与适应度
            tmpPaper.setKpCoverage(rule);
            tmpPaper.setAdaptationDegree(rule, Population.KP_WEIGHT, Population.DIFFCULTY_WEIGHt);
        }
        return newPopulation;
    }

    /**
     * 交叉算子
     *
     * @param parent1
     * @param parent2
     * @return
     */
    public  Paper crossover(Paper parent1, Paper parent2, PaperRule rule) {
        Paper child = new Paper(parent1.getQuestionSize());
        int s1 = (int) (Math.random() * parent1.getQuestionSize());
        int s2 = (int) (Math.random() * parent1.getQuestionSize());

        // parent1的startPos endPos之间的序列，会被遗传到下一代
        int startPos = s1 < s2 ? s1 : s2;
        int endPos = s1 > s2 ? s1 : s2;
        for (int i = startPos; i < endPos; i++) {
            QuestionTypeItem typeRelation = parent1.getTypeRelation(parent1.getQuestion(i).getId());
            if (typeRelation == null){
                System.out.println("parent1 typeRelation is null");
            }
            child.addQuestion(i, parent1.getQuestion(i),typeRelation);
        }

        // 继承parent2中未被child继承的question
        // 防止出现重复的元素

        for (int i = 0; i < startPos; i++) {
            if (!child.containsQuestion(parent2.getQuestion(i))) {
                QuestionTypeItem typeRelation = parent2.getTypeRelation(parent2.getQuestion(i).getId());
                if (typeRelation == null){
                    System.out.println("parent2 typeRelation is null 【1】");
                }
                child.addQuestion(i, parent2.getQuestion(i),typeRelation);

            } else {
                int type = parent2.getQuestion(i).getQuestionType();
                //指定类型和知识点的试题数组
                List<Question> questions = questionService.queryQuestionsBySubjectAndPoints(type, rule.getSubjectId(), rule.getPoints());

                if (questions.size()>1){
                    boolean loop = true;
                    while (loop){
                        Question temp = questions.get((int) (Math.random() * questions.size()));
                        if (!child.containsQuestion(temp)){
                            temp.setScore((int) getScoreByQuestionId(parent2,parent2.getQuestion(i).getId()));
                            QuestionTypeItem typeRelation = parent2.getTypeRelation(parent2.getQuestion(i).getId());
                            if (typeRelation == null){
                                System.out.println("parent2 typeRelation is null 【1】");
                            }
                            child.addQuestion(i, temp,typeRelation);
                            loop = false;
                        }
                    }
                }else {
                    logger.info("【遗传算法组卷】交叉算子阶段题库不足,题型{},学科ID{},知识点{}",type, rule.getSubjectId(), rule.getPoints());
                }

            }
        }
        for (int i = endPos; i < parent2.getQuestionSize(); i++) {
            if (!child.containsQuestion(parent2.getQuestion(i))) {
                QuestionTypeItem typeRelation = parent2.getTypeRelation(parent2.getQuestion(i).getId());
                child.addQuestion(i, parent2.getQuestion(i),typeRelation);
            } else {
                int type = parent2.getQuestion(i).getQuestionType();
                List<Question> questions = questionService.queryQuestionsBySubjectAndPoints(type, rule.getSubjectId(), rule.getPoints());

                if (questions.size()>1){
                    boolean loop = true;
                    while (loop){
                        Question temp = questions.get((int) (Math.random() * questions.size()));
                        if (!child.containsQuestion(temp)){
                            temp.setScore((int) getScoreByQuestionId(parent2,parent2.getQuestion(i).getId()));
                            QuestionTypeItem typeRelation = parent2.getTypeRelation(parent2.getQuestion(i).getId());
                            if (typeRelation == null){
                                System.out.println("parent2 typeRelation is null 【2】");
                            }
                            child.addQuestion(i, temp,typeRelation);
                            loop = false;
                        }
                    }
                }else {
                    logger.info("【遗传算法组卷】交叉算子阶段题库不足,题型{},学科ID{},知识点{}",type, rule.getSubjectId(), rule.getPoints());
                }
            }
        }

        return child;
    }


    private double getScoreByQuestionId(Paper paper,String questionId){

        QuestionTypeItem typeRelation = paper.getTypeRelation(questionId);

        if (typeRelation != null)
            return typeRelation.getScore();
        else
            logger.info("【遗传算法组卷】根据questionId获取试题Item失败...");
        return 0;
    }

    /**
     * 突变算子 每个个体的每个基因都有可能突变
     *
     * @param paper
     */
    public  void mutate(Paper paper,List<String> rulePoints) {
        Question tmpQuestion;
        List<Question> list;
        int index;

        for (int i = 0; i < paper.getQuestionSize(); i++) {
            if (Math.random() < mutationRate) {
                // 进行突变，第i道
                tmpQuestion = paper.getQuestion(i);
                //
                QuestionTypeItem TypeRelationBefore = paper.getTypeRelation(tmpQuestion.getId());
                // 从题库中获取和变异的题目类型一样分数相同的题目（不能包含变异题目）
                List<String> points = retainAll(StringUtil.string2List(tmpQuestion.getTags(),","),rulePoints);
                if (points.size()==0){
                    System.out.println(i);
                }
                list = questionService.queryQuestionsBySubjectAndPoints(tmpQuestion.getQuestionType(),
                        tmpQuestion.getSubjectId(), points);
                int dbQuestions = list.size();
                //查询到的试题和paper中的试题取交集
                List<Question> remain = retainAllQuestion(list,paper.getQuestions());
                if (dbQuestions>remain.size()) {//不包含原有题目
                    boolean loop = true;
                    while (loop){
                        // 随机获取一道
                        index = (int) (Math.random() * list.size());
                        Question newQ = list.get(index);
                        if (!paper.containsQuestion(newQ)) {
                            // 设置分数
                            newQ.setScore(tmpQuestion.getScore());
                            //添加新的QuestionTypeRelation,移除旧的
                            paper.addQuestion(i, list.get(index),TypeRelationBefore);
                            paper.removeTypeRelation(tmpQuestion.getId());
                            if (paper.getTypeRelationSize()!=10){
                                System.out.println("异常");
                            }
                            loop = false;
                        }
                    }

                }else {
                    logger.info("【遗传算法组卷】突变时试题数量不足,试题类型{},科目ID{},知识点{}",tmpQuestion.getQuestionType(),
                            tmpQuestion.getSubjectId(), points);
                }
            }
        }
    }

    /**
     * 选择算子
     *
     * @param population
     */
    private static Paper select(Population population) {
        Population pop = new Population(tournamentSize);
        for (int i = 0; i < tournamentSize; i++) {
            pop.setPaper(i, population.getPaper((int) (Math.random() * population.getSize())));
        }
        return pop.getFitness();
    }

    /**
     * 两个list求交集list
     * @param list1
     * @param list2
     * @return
     */
    private static <T> List<T> retainAll(List<T> list1, List<T> list2){
        HashSet set1 = new HashSet(list1);
        HashSet set2 = new HashSet(list2);

        set1.retainAll(set2);

        return new ArrayList<>(set1);
    }

    private static List<Question> retainAllQuestion(List<Question> list1, List<Question> list2){
        List<Question> result = new ArrayList<>();
        for (Question question : list1) {
            for (Question question1 : list2) {
                if (question.getId().equals(question1.getId()))
                    result.add(question);
            }
        }
        return  result;
    }
}
