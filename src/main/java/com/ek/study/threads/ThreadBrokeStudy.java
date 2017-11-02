package com.ek.study.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程里如果抛出runtimeException，那么线程会挂掉，为什么我结尾查看线程池里线程数量时还是pool size不变呢，那是因为挂了之后线程池会重新启动新的线程来填充数量
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/11/2
 */
public class ThreadBrokeStudy {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        threadPoolExecutor.prestartAllCoreThreads();
        System.out.println(threadPoolExecutor.getPoolSize());
        threadPoolExecutor.execute(() -> {
            System.out.println("try to throw runtime exception 1, thread name " + Thread.currentThread().getName());
            Integer a = null;
            //System.out.println(a.toString());
        });
        threadPoolExecutor.execute(() -> {
            System.out.println("try to throw runtime exception 2, thread name " + Thread.currentThread().getName());
            //throw new RuntimeException();
        });
        threadPoolExecutor.execute(() -> {
            System.out.println("try to throw runtime exception 3, thread name " + Thread.currentThread().getName());
            //throw new RuntimeException();
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadPoolExecutor.getPoolSize());

    }
}
