package com.mindskip.xzs.utility.ga;

import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.service.QuestionService;
import com.mindskip.xzs.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * 种群：试卷list
 */

public class Population {

    private Paper[] papers;


    public static final double  KP_WEIGHT = 0.2;

    public static final double  DIFFCULTY_WEIGHt = 0.8;

    public Population(){

    }

    public Population(int populationSize) {
        papers = new Paper[populationSize];
    }


    /**
     * 获取种群中最优秀个体
     *
     * @return
     */
    public Paper getFitness() {
        Paper fitnessPaper = papers[0];
        for (int i = 1; i < papers.length; i++) {
            if (fitnessPaper.getAdaptationDegree() < papers[i].getAdaptationDegree())
                fitnessPaper = papers[i];
        }
        return fitnessPaper;
    }


    /**
     * 获取种群中某个个体
     *
     * @param index
     * @return
     */
    public Paper getPaper(int index) {
        return papers[index];
    }

    /**
     * 设置种群中某个个体
     *
     * @param index
     * @param paper
     */
    public void setPaper(int index, Paper paper) {
        papers[index] = paper;
    }

    public int getSize(){
        return papers.length;
    }
}
