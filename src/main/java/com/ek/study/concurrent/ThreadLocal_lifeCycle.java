package com.ek.study.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池使用threadLocal必须每次使用完之后清理，否则会会出问题的
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/9/14
 */
public class ThreadLocal_lifeCycle {

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static ExecutorService executors = Executors.newFixedThreadPool(2);


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            executors.submit(() -> {

                String s = THREAD_LOCAL.get();
                if (s == null || s.equals("")) {
                    System.out.println("init");
                    THREAD_LOCAL.set("lazyman");
                }

                System.out.println("s: " + s);
                THREAD_LOCAL.remove();
            });

        }

    }

}
