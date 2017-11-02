package com.ek.study.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/31
 */
public class PatternStudy {
    private static final Pattern PATTERN = Pattern.compile("/(\\w+)$");

    public static void main(String[] args) {

        Matcher matcher = PATTERN.matcher("/data-engine/antifraud/status/130677709591953408");
        StringBuffer sb = new StringBuffer("");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            matcher.appendReplacement(sb, "/{*}");
            System.out.println(sb.toString());
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }
}
