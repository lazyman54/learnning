package com.ek.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/15
 */
public class JavaReferenceStudy {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();


        map.put("old", new ArrayList<>());
        map.get("old").add("insert");
        List<String> aNew = map.getOrDefault("new", new ArrayList<>());
        aNew.add("new insert");
        System.out.println(map);
    }
}
