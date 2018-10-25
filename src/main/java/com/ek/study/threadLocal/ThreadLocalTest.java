package com.ek.study.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        ((ThreadPoolExecutor) executorService).prestartAllCoreThreads();
        threadLocal.set("parent");
        inheritableThreadLocal.set("inheritable parent");
        transmittableThreadLocal.set("transimit parent");

        new Thread(() -> {
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
            for (int i = 0; i < 5; i++) {
                executorService.submit(() -> {
                    System.out.println(inheritableThreadLocal.get());

                });

                Runnable runnable = TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get()), true);

                executorService.submit(runnable);
            }

        }).start();


    }

}
