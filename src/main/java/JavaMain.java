import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/13
 */
public class JavaMain {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

//        Test test1 = new Test();
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] digest = md5.digest("my".getBytes());

        String encode = MD5Encoder.encode(digest);
        System.out.println(encode);
//        test1.a = 1;
//        Test.b = 1;   //Test.b=1
//        Test test2 = new Test();
//        test2.a = 2;
//        Test.b = 2;   //Test.b =2
//        System.out.println(test1.a); // 1
//        System.out.println(Test.b); // 2
//        System.out.println(test2.a); // 2
//        System.out.println(Test.b); // 2
        String s = Md5Crypt.md5Crypt("my".getBytes());
        System.out.println();
        System.out.println(s);
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


abstract class Test {

    public int a;
    public static int b;

    static void say(){}

}

enum TestEnv {
    H5("haaa");
    private String code;

    TestEnv(String code) {
        this.code = code;
    }
}
