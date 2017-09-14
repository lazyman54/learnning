package com.ek.study.reflect;


import java.util.List;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/13
 */
public class TargetSource {
    private int inter;

    public String str;

    private ArgObj argObj;

    private List<ArgObj> argObjs;

    public void noArgMethod() {
        System.out.println("no arg method");
    }

    public void baseArgMethod(int abc) {
        System.out.println("base arg method: arg: " + abc);
    }

    public void objArgMethod(ArgObj abc) {
        System.out.println("base arg method: arg: " + abc);
    }

    public void listBaseArgMethod(List<Integer> baseArgs) {
        System.out.println("list base arg method, base Args: " + baseArgs);
    }

    public void listObjArgMethod(List<ArgObj> argObjs) {
        System.out.println("list obj arg method, obj Args: " + argObjs);
    }
}
