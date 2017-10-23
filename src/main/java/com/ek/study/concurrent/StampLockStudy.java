package com.ek.study.concurrent;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/8/22
 */
public class StampLockStudy {
    public static void main(String[] args) throws InterruptedException {
        final StampedLock lock = new StampedLock();
        new Thread(() -> {
            long readLong = lock.writeLock();
            System.out.println(Thread.currentThread().getName());
            System.out.println("sleep");
            LockSupport.parkNanos(6100000000L);
            System.out.println("await");
            lock.unlockWrite(readLong);
        }).start();
        Thread.sleep(100);
        for (int i = 0; i < 3; ++i) {
            new Thread(new OccupiedCPUReadThread(lock)).start();
        }
    }

    private static class OccupiedCPUReadThread implements Runnable {
        private StampedLock lock;

        public OccupiedCPUReadThread(StampedLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + "sleep");
            long lockr = 0;
            try {
                lockr = lock.readLockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println(Thread.currentThread().getName() + " get read lock");
            lock.unlockRead(lockr);
        }
    }
}
