package com.ek.study.conCurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/21
 */
public class CyclicBarrierStudy {
    public static void main(String[] args) {
        Solver solver = new Solver(new float[2][4]);
    }
}

class Solver {
    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        public void run() {
            System.out.println("try to handler " + myRow);
            try {
                barrier.await();
            } catch (InterruptedException ex) {
                return;
            } catch (BrokenBarrierException ex) {
                return;
            }
        }
    }

    public Solver(float[][] matrix) {
        data = matrix;
        N = matrix.length;
        Runnable barrierAction =
                () -> System.out.println("here");
        barrier = new CyclicBarrier(N, barrierAction);

        System.out.println(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            // threads.add(thread);
            thread.setName("thread_" + i);
            try {
                Thread.sleep(i * 10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }

        System.out.println("done");
        // wait until done
        /*for (Thread thread : threads)
            thread.join();*/
    }
}
