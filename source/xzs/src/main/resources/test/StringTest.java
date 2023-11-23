import com.mindskip.xzs.utility.StringUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringTest {
    @Test
    public void test01(){
        String value="白日依山尽,#_#。欲穷千里目,#_#。";
        int gaps = (value.length()-value.replaceAll("#_#","").length())/"#_#".length();
        for (int i = 0; i < gaps; i++) {
            value = value.replaceFirst("#_#","<span class=\"gapfilling-span \">"+(i+1)+"</span>");
        }
        System.out.println(value);
    }

    @Test
    public void test02(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        System.out.println(StringUtil.list2String(list,","));
    }
}
