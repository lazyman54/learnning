package com.ek.study.conCurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/21
 */
public class ThreadLocalStudy {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    System.out.println("here");
                    return nextId.getAndIncrement();
                }
            };

    public static int get() {
        return threadId.get();
    }


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
