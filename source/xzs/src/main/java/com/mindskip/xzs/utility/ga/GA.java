package com.mindskip.xzs.utility.ga;

import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.service.QuestionService;
import com.mindskip.xzs.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
                    // 单选题
                    if (rule.getSingleNum() > 0) {
                        generateQuestion(1, random, rule.getSingleNum(), rule.getSingleScore(),rule.getExamId(), points, paper);
                    }
                    // 多选题
                    if (rule.getMultipleNum() > 0) {
                        generateQuestion(2, random, rule.getMultipleNum(), rule.getMultipleScore(),rule.getExamId(), points, paper);
                    }
                    // 判断题
                    if (rule.getJudgementNum() > 0) {
                        generateQuestion(3, random, rule.getJudgementNum(), rule.getJudgementScore(),rule.getExamId(), points, paper);
                    }
                    // 填空题
                    if (rule.getCompleteNum() > 0) {
                        generateQuestion(4, random, rule.getCompleteNum(), rule.getCompleteScore(),rule.getExamId(), points, paper);
                    }
                    // 简答题
                    if (rule.getSubjectiveNum() > 0) {
                        generateQuestion(5, random, rule.getSubjectiveNum(), rule.getSubjectiveScore(),rule.getExamId(), points, paper);
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

    public void generateQuestion(int type, Random random, int qustionNum, double score, String subjectId, List<String> points,
                                  Paper paper) {

        List<Question> qList = questionService.queryQuestionsBySubjectAndPoints(type,subjectId,points );
        if (qList.size() < qustionNum) {
            return;
        }
        Question tmpQuestion;
        for (int j = 0; j < qustionNum; j++) {
            int index = random.nextInt(qList.size() - j);
            // 初始化分数
            qList.get(index).setScore((int) score);//每一个题型的初始分是一致的
            paper.addQuestion(qList.get(index));

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
            // 交叉
            Paper child = crossover(parent1, parent2, rule);
            child.setId(i);
            newPopulation.setPaper(i, child);
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
            child.addQuestion(i, parent1.getQuestion(i));
        }

        // 继承parent2中未被child继承的question
        // 防止出现重复的元素

        for (int i = 0; i < startPos; i++) {
            if (!child.containsQuestion(parent2.getQuestion(i))) {
                child.addQuestion(i, parent2.getQuestion(i));
            } else {
                int type = getTypeByIndex(i, rule);
                //指定类型和知识点的试题数组
                List<Question> questions = questionService.queryQuestionsBySubjectAndPoints(type, rule.getExamId(), rule.getPoints());
                Question temp = questions.get((int) (Math.random() * questions.size()));
                temp.setScore((int) getScoreByType(type,rule));
                child.addQuestion(i, temp);
            }
        }
        for (int i = endPos; i < parent2.getQuestionSize(); i++) {
            if (!child.containsQuestion(parent2.getQuestion(i))) {
                child.addQuestion(i, parent2.getQuestion(i));
            } else {
                int type = getTypeByIndex(i, rule);
                List<Question> questions = questionService.queryQuestionsBySubjectAndPoints(type, rule.getExamId(), rule.getPoints());
                Question temp = questions.get((int) (Math.random() * questions.size()));
                temp.setScore((int) getScoreByType(type,rule));
                child.addQuestion(i, temp);
            }
        }

        return child;
    }

    private  int getTypeByIndex(int index, PaperRule rule) {
        int type = 0;
        // 单选
        if (index < rule.getSingleNum()) {
            type = 1;
        } else if (index < rule.getSingleNum() + rule.getMultipleNum()) {
            // 多选
            type = 2;
        }else if (index < rule.getSingleNum() + rule.getMultipleNum() +rule.getJudgementNum()) {
            // 判断
            type = 3;
        }else if (index < rule.getSingleNum() + rule.getMultipleNum() +rule.getJudgementNum() + rule.getCompleteNum()) {
            // 填空
            type = 4;
        } else {
            // 主观
            type = 5;
        }
        return type;
    }

    private double getScoreByType(int type,PaperRule rule){
        if (type == 1)
            return rule.getSingleScore();
        else if (type == 2)
            return rule.getMultipleScore();
        else if (type == 3)
            return rule.getJudgementScore();
        else if (type == 4)
            return rule.getCompleteScore();
        else
            return rule.getSubjectiveScore();
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
                // 从题库中获取和变异的题目类型一样分数相同的题目（不包含变异题目）
                List<String> points = retainAll(StringUtil.string2List(tmpQuestion.getTags(),","),rulePoints);
                if (points.size()==0){
                    System.out.println(i);
                }
                list = questionService.queryQuestionsBySubjectAndPoints(tmpQuestion.getQuestionType(),
                        tmpQuestion.getSubjectId(), points);
                if (list.size() > 0) {
                    // 随机获取一道
                    index = (int) (Math.random() * list.size());
                    // 设置分数
                    list.get(index).setScore(tmpQuestion.getScore());
                    paper.addQuestion(i, list.get(index));
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
    private static List<String> retainAll(List list1,List list2){
        HashSet set1 = new HashSet(list1);
        HashSet set2 = new HashSet(list2);

        set1.retainAll(set2);

        return new ArrayList<>(set1);
    }
}
