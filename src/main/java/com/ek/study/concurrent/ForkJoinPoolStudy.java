package com.ek.study.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/20
 */
public class ForkJoinPoolStudy {
    private static final int maxInt = Integer.MAX_VALUE >> 5;

    public static void main(String[] args) {
        System.out.println(traditionalWay());
    }

    private static long traditionalWay() {
        long sum = 0L;
        long beginTime = System.currentTimeMillis();
        for (int i = 1; i <= maxInt; i++) {
            sum += i;
        }

        System.out.println(System.currentTimeMillis() - beginTime);
        return sum;
    }

    private static long forkJoinWay() {
        Long aLong = 0L;
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        long current = System.currentTimeMillis();
        ForkJoinTask<Long> submit = forkJoinPool.submit(new MyRecursiveTask(1, maxInt));
        try {
            aLong = submit.get();
            System.out.println(System.currentTimeMillis() - current);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return aLong;
    }
}

class MyRecursiveTask extends RecursiveTask<Long> {
    private final int beginPosition;
    private final int endPosition;

    public MyRecursiveTask(int beginPosition, int endPosition) {
        super();
        this.beginPosition = beginPosition;
        this.endPosition = endPosition;
    }

    @Override
    protected Long compute() {
        long sumVal = 0L;
        if ((endPosition - beginPosition) != 0) {
            int middle = (endPosition + beginPosition) / 2;
            MyRecursiveTask leftTask = new MyRecursiveTask(beginPosition, middle);
            MyRecursiveTask rightTask = new MyRecursiveTask((middle + 1), endPosition);
            invokeAll(leftTask, rightTask);
            long left = leftTask.join();
            long right = rightTask.join();
            return left + right;
        } else {
            return (long) endPosition;
        }
    }
}
