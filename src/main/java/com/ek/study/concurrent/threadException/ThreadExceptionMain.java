package com.ek.study.concurrent.threadException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExceptionMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 15; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        Thread.sleep(1000);
        System.out.println("************************");
        for (int i = 0; i < 15; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                throw new Exception("kill");
            });
        }


    }
}
