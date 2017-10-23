package com.ek.study.concurrent;

import java.util.concurrent.*;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/20
 */
public class CompletionServiceStudy {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        CompletionService<String> csRef = new ExecutorCompletionService<String>(executorService);
        for (int i = 0; i < 5; i++) {
            final int j = i;
            csRef.submit(() -> {
                System.out.println("begin + " + j);
                Thread.sleep(1000);
                System.out.println("end + " + j);
                return "my " + j;
            });
        }

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("........" + csRef.poll(2, TimeUnit.SECONDS).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}

