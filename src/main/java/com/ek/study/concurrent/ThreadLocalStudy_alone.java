package com.ek.study.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal是每个线程独有的
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/6/21
 */
public class ThreadLocalStudy_alone {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId =
            ThreadLocal.withInitial(() -> {
                System.out.println("here");
                return nextId.getAndIncrement();
            });

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            //System.out.println(get());
            new Thread(() ->
            {
                System.out.println("----name: " + Thread.currentThread().getName() + " num: " + threadId.get());
                System.out.println("----name: " + Thread.currentThread().getName() + " num: " + threadId.get());
            }
            ).start();
        }
    }
}
