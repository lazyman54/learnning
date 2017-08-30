package com.ek.study.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/24
 */
public class InvokerHandler implements InvocationHandler {

    private Object object;

    public InvokerHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("****************");
        System.out.println("proxy: " + proxy.getClass().getName());
        System.out.println("method: " + method);
        System.out.println("args: " + args);
        System.out.println("****************");
        System.out.println("method: " + method.getName() + "before");
        Object invoke = method.invoke(object, args);
        System.out.println("method: " + method.getName() + "after");
        return invoke;
    }
}
