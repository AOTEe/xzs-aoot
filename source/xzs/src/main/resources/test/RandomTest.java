import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.repository.QuestionMapper;
import com.mindskip.xzs.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomTest {


    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionMapper questionMapper;

    @Test
    public void test01(){
        /**
         * 指定题型、学科、知识点、难度随机选择一定数量的试卷
         * 生成规则 指定难度 N*0.7 + N-1*0.15 + N+1*0.15
         * 例如 30题里头选10题, 7,1,2
         * 查询所有符合条件的试题的ID,返回ID LIST
         * 再从LIST中随机选取10个ID,再查询试题详情
         */
        Question question = new Question();
        question.setQuestionType(1);
        question.setSubjectId("400538256497840128");
        question.setDifficult(3);
        int n = 10;
        Integer allCount = questionMapper.selectCountByCondition(question);
        if (allCount<10)
            System.out.println("题库数量不足");
        List<String> questions = questionMapper.selectQuestionByCondition(question);
        System.out.println(question.toString());

    }
}
