package com.ek.study.performance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SystemTimePerformance {

    public static void main(String[] args) throws InterruptedException {
        int count = 1000000;
        //singleThreadRun(count);
        multiThreadRun(count);
    }


    private static void singleThreadRun(int count) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            System.currentTimeMillis();
        }

        System.out.println(String.format("%d ms", (System.currentTimeMillis() - time)));
    }

    private static void multiThreadRun(int count) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(200);
        ((ThreadPoolExecutor) executorService).prestartAllCoreThreads();
        System.out.println(((ThreadPoolExecutor) executorService).getPoolSize());
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                System.currentTimeMillis();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(String.format("%d ms", (System.currentTimeMillis() - time)));

    }


}
