package com.ek.study.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/31
 */
public class PatternStudy {


    public static void main(String[] args) {
        //getPhone();
        System.out.println("abc".getBytes());

    }


    /**
     * 匹配ip地址（127.0.0.1）
     */
    private static void getIp() {
        String str = "127.0.0.255";
        /*不获取匹配ip地址*/
        boolean matches = Pattern.matches("((?:(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d))\\.){3}(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d)))", str);
        System.out.println(matches);
    }

    private static void getPhone() {

        boolean matches = Pattern.matches("^1\\d{10}$", "18578239990");
        System.out.println(matches);
    }

    private static void betterPattern() {
        String regex1 = "try|tries";
        String regex2 = "tr(?:y|ies)";
    }


    private static void getUpperChar() {
        final Pattern PATTERN = Pattern.compile("([A-Z|_])");
        Matcher matcher = PATTERN.matcher("BootSatrapHello_world");
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }


    }

    private void getLastThing() {
        final Pattern PATTERN = Pattern.compile("/(\\w+)$");
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
