import org.junit.Test;

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
}
