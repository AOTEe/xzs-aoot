import com.mindskip.xzs.utility.SnowFlakeGenerateIDUtil;

public class IDTest {
    public static void main(String[] args) {
        SnowFlakeGenerateIDUtil IDGenerator = new SnowFlakeGenerateIDUtil();
        for (int i = 0; i < 100; i++) {
            String s = IDGenerator.generateID();

            System.out.println(s);
        }
    }
}
