package com.ek.study;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/6
 */
public class RegexStudy {
    public static void main(String[] args) {
        String patternStr = "([A-Z])";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher("myNameIsLazyman");
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            System.out.println(matcher.group(1));
            matcher.appendReplacement(sb, "/" + matcher.group(1).toLowerCase());
        }
        matcher.appendTail(sb);
        System.out.println(sb);
    }
}
