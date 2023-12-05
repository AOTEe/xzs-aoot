package com.mindskip.xzs.utility.ga;

import com.mindskip.xzs.domain.Question;

import java.util.*;

/**
 * 种群中的个体：一套试卷
 */
public class Paper {
    /**
     * id
     */
    private int id;
    /**
     * 适应度
     */
    private double adaptationDegree =0.00;
    /**
     * 知识点覆盖率
     */
    private double kpCoverage = 0.00;
    /**
     * 试卷难度系数
     */
    private double difficulty = 0.00;
    /**
     * 试卷总分
     */
    private double totalScore = 0.00;

    /**
     * 试题集合
     */
    private List<Question> questions = new ArrayList<>();

    /**
     * 试题ID和questionTypeItem的映射关系
     */
    private Map<String,QuestionTypeItem> typeRelation = new HashMap<>();

    public Paper(){

    }

    public Paper(int size) {
        for (int i = 0; i < size; i++) {
            questions.add(null);
        }
    }
    /**
     * 计算总分
     * @return
     */
    public double getTotalScore(){
        if (totalScore == 0){
            double total = 0;
            for (Question question : questions) {
                total += question.getScore();
            }
            totalScore = total;
        }
        return totalScore;
    }

    /**
     * 计算试卷个体难度系数 计算公式： 每题难度*分数求和除总分
     * 难度5分值-》1分制
     * @return
     */
    public double getDifficulty(){
        if (difficulty == 0) {
            double _difficulty = 0;
            for (Question question : questions) {
                try {
                    _difficulty += question.getScore()*0.2 * question.getDifficult();
                }catch (Exception e){
                    System.out.println(question.getId());
                    e.printStackTrace();
                }

            }
            difficulty = _difficulty / getTotalScore();
        }
        return difficulty;
    }

    /**
     * 计算个体适应度 公式为：f=1-(1-M/N)*f1-|EP-P|*f2
     * 其中M/N为知识点覆盖率，EP为期望难度系数，P为种群个体难度系数，f1为知识点分布的权重
     * ，f2为难度系数所占权重。当f1=0时退化为只限制试题难度系数，当f2=0时退化为只限制知识点分布
     * 试题难度为1、2、3、4、5——》0.2、0.4、0.6、0.8、1
     * @param rule 组卷规则
     * @param f1   知识点分布的权重
     * @param f2   难度系数的权重
     */
    public void setAdaptationDegree(PaperRule rule, double f1, double f2) {
        if (adaptationDegree == 0) {
            adaptationDegree = 1 - (1 - getKpCoverage()) * f1 - Math.abs(rule.getDifficulty() - getDifficulty()) * f2;
        }
    }


    public int getQuestionSize(){
        return questions.size();
    }

    public void setKpCoverage(PaperRule rule){
        if (kpCoverage == 0){
            Set<String> result = new HashSet<>();
            Set<String> paperPoints = new HashSet<>();
            result.addAll(rule.getPoints());
            for (Question question : questions) {
                String[] points = question.getTags().split(",");
                paperPoints.addAll(Arrays.asList(points));
            }
            //取交集
            result.retainAll(paperPoints);
            //覆盖率怎么算会不会不合适,一题有多个知识点
            kpCoverage = result.size()/rule.getPoints().size();
        }
    }

    public boolean containsQuestion(Question question) {
        if (question != null) {
            for (Question _question : questions) {
                if (_question != null) {
                    if (_question.getId().equals(question.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 添加问题
     *
     * @param question
     */
    public void addQuestion(int index, Question question,QuestionTypeItem questionTypeItem) {
        this.questions.set(index, question);
        this.totalScore = 0;
        this.adaptationDegree = 0;
        this.difficulty = 0;
        this.kpCoverage = 0;
        addTypeRelation(question.getId(),questionTypeItem);
    }

    public void addQuestion(Question question,QuestionTypeItem questionTypeItem) {
        this.questions.add(question);
        this.totalScore = 0;
        this.adaptationDegree = 0;
        this.difficulty = 0;
        this.kpCoverage = 0;
        addTypeRelation(question.getId(),questionTypeItem);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAdaptationDegree() {
        return adaptationDegree;
    }

    public void setAdaptationDegree(double adaptationDegree) {
        this.adaptationDegree = adaptationDegree;
    }

    public double getKpCoverage() {
        return kpCoverage;
    }

    public void setKpCoverage(double kpCoverage) {
        this.kpCoverage = kpCoverage;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getTypeRelationSize(){
        return typeRelation.size();
    }

    public QuestionTypeItem getTypeRelation(String questionId) {
        return typeRelation.get(questionId);
    }

    public Map<String,QuestionTypeItem> getTypeRelation(){
        return typeRelation;
    }
    public void addTypeRelation(String questionId, QuestionTypeItem item) {
        this.typeRelation.put(questionId, item);
    }


    public void removeTypeRelation(String questionId) {
        this.typeRelation.remove(questionId);
    }
}
