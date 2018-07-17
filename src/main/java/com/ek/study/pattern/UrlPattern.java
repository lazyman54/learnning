package com.ek.study.pattern;

import java.util.regex.Pattern;

public class UrlPattern {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("(/+\\w+)+/auth/(\\w+/*)+");
        String url1 = "/api/auth/cardbag/v1/index";
        String url2 = "abc/authad/aaa";
        String url3 = "abc/auth";
        String url4 = "/auth/";

        System.out.println(pattern.matcher(url1).matches());
        System.out.println(Pattern.matches("/auth/", url2));
        System.out.println(Pattern.matches("/auth/", url3));
        System.out.println(Pattern.matches("/auth/", url4));
    }
}
