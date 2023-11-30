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
        System.out.println(Math.random());

    }
}
