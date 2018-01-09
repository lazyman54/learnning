import java.io.UnsupportedEncodingException;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/13
 */
public class JavaMain {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String province = "山东省";
        System.out.println(province.substring(0, province.lastIndexOf("省")));
        //cpuJobPerMs();
    }

    private static void cpuJobPerMs() {
        long oldCurrent = System.currentTimeMillis();
        int count = 0;
        long newCurrent;
        while ((newCurrent = System.currentTimeMillis()) == oldCurrent) {

        }
        while (System.currentTimeMillis() == newCurrent) {
            //System.out.println("a");
            int a = 5 + 5;
            count++;
        }
        System.out.println("*************" + count + "*****************");
    }
}
