package com.ek.study.FilterChain;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/24
 */
public interface Filter {
    String invoke(Invoker invoker, String param);
}
