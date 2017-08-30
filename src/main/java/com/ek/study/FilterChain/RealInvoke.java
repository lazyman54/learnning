package com.ek.study.FilterChain;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/24
 */
public class RealInvoke implements Invoker<String> {
    @Override
    public String invoke(String param) {
        System.out.println("real exe" + param);
        return "finish";
    }
}
