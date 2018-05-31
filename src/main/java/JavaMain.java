import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

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
        System.out.println(TestEnv.H5.toString());


        Collection<Long> ids = new ArrayList<>();
        ids.add(12L);
        ids.add(13L);
        ids.add(14L);
        System.out.println();

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
        //System.out.println(URLDecoder.decode("http%3A%2F%2F10.20.160.198%2Fwiki%2Fdisplay%2Fdubbo%2Fhost+%3D+10.8.2.150+%3D%3E+host+%3D+10.8.15.42", "utf-8"));
        System.out.println(Long.MAX_VALUE);
        Long maxLong = (1L << 62) + ((1L << 62) - 1);
        System.out.println(maxLong);
        Object obj = new Object();
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

enum TestEnv {
    H5("haaa");
    private String code;

    TestEnv(String code) {
        this.code = code;
    }
}
