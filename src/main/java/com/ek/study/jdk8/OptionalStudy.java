package com.ek.study.jdk8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            System.out.println(s);
            return s.equals("b") || s.equals("h");
        }).sorted().forEach(set::add);
        System.out.println(set.size());
        // System.out.println(count);


    }
}

class User {
}
