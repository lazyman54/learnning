package com.ek.study.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/20
 */
public class ThreadExceptionStudy {
    private static ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        executorService.prestartAllCoreThreads();
        System.out.println("num: " + executorService.getPoolSize());

        executorService.execute(() -> {
            System.out.println("exe.......");
            throw new IllegalArgumentException();
        });
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num: " + executorService.getPoolSize());
        executorService.shutdownNow();
    }
}
