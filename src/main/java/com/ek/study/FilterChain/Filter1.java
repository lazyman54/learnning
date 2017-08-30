package com.ek.study.FilterChain;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/24
 */
public class Filter1 implements Filter {
    @Override
    public String invoke(Invoker invoker, String param) {
        System.out.println("filter1 invoke begin");
        String result = invoker.invoke(param);
        System.out.println("filter1 invoke end");
        return result;
    }
}
