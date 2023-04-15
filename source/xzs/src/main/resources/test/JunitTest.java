import com.mindskip.xzs.XzsApplication;
import com.mindskip.xzs.service.OrgService;
import com.mindskip.xzs.service.impl.OrgServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = XzsApplication.class)
public class JunitTest {
    
    @Autowired
    OrgServiceImpl orgService;
    
    @Test
    public void orgTest(){
        List orgTree = orgService.getOrgTree();
        for (Object o : orgTree) {

        }
    }
}
