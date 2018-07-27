import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.storm.shade.org.apache.commons.collections.map.HashedMap;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/13
 */
public class JavaMain {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashedMap();
        map.put("a", 12);
        map.put("b", null);
        System.out.println(JSON.toJSONString(map, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty));

        //Comparator<String> comparator = Collator.getInstance(Locale.CHINA);
       /* HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        Comparator<String> comparator1 = (o1, o2) -> {

            String stringArray="";
            try {
                stringArray = PinyinHelper.toHanyuPinyinString(o1,outputFormat, "");
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            System.out.println(stringArray);
            String[] strings = PinyinHelper.toHanyuPinyinStringArray(o2.toCharArray()[0]);
            System.out.println(Lists.newArrayList(strings));
            return 1;
        };
        List<String> sortList = Lists.newArrayList("上海", "合肥", "北京", "广州", "南京", "天津","怡情","赵国");

        sortList.sort(comparator1);

        //sortList.sort((String::compareToIgnoreCase));


        System.out.println(sortList);*/
        //System.out.println(Math.abs("43704824".hashCode()) % 20);


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
