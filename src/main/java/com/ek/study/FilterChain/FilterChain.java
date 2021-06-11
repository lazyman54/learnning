package com.ek.study.FilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/24
 */
public class FilterChain {
    public static void main(String[] args) {
        FilterChain chain = new FilterChain();

        Invoker invoker = chain.builderFilterChain(new RealInvoke());
        System.out.println("ready.....go");
        invoker.invoke("param");

    }

    public Invoker builderFilterChain(final Invoker invoker) {

        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter1());
        filters.add(new Filter2());

        Invoker last = invoker;

        for (Filter filter : filters) {

            final Invoker next = last;

/*            last = new Invoker(){
                @Override
                public String invoke(String param) {
                    return filter.invoke(next, param);
                }
            };*/
            last = param -> {
                return filter.invoke(next, param);
            };
        }
        return last;
    }

}
