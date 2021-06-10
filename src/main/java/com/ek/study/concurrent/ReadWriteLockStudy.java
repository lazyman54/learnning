package com.ek.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/22
 */
public class ReadWriteLockStudy {

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private final CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        ReadWriteLockStudy readWriteLockStudy = new ReadWriteLockStudy();
        readWriteLockStudy.read();
        readWriteLockStudy.countDownLatch.await();
    }

    private void read() {

       /* for (int i = 0; i < 32767; i++) {
            readLock.lock();
        }*/


        /*readLock.lock();
        writeLock.lock();*/
        /*readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();readLock.lock();readLock.lock();*/


        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    readLock.lock();
                    System.out.println("********" + Thread.currentThread().getName() + "***********");
                    Thread.sleep(2000);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            });
            thread.start();
            //thread.join();
        }
    }
}
