package com.ek.study.dynamicProxy;

import sun.misc.ProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/24
 */
public class ProxyStudy {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();


        InvocationHandler handler = new InvokerHandler(userService);

        IUserService instance = (IUserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), new Class[]{IUserService.class}, handler);
        instance.userTalking("lazyman");
        //System.out.println(instance.userReject());

    }



}
