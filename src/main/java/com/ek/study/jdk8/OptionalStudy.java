package com.ek.study.jdk8;

import java.util.*;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/5
 */
public class OptionalStudy {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.stream().filter((s) -> {
            //System.out.println(s);
            return s.equals("b") || s.equals("h");
        }).sorted().forEach(set::add);
        System.out.println(set.size());
        // System.out.println(count);

        List<String> ss = Arrays.asList("eric", "bbb", "bbbd", "baa");
        String result = ss.stream().filter(str -> {
            System.out.println(str);
            return str.length() == 3;
        }).map(String::toUpperCase).findFirst().get();
        System.out.println(result);

        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("aa");
            }
        });
        t.setDaemon(true);
        t.start();
    }
}

