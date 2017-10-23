package com.ek.study.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/10/19
 */
public class ConditionWaitStudy {
    public static void main(String[] args) {
        StringPool stringPool = new StringPool();

        /*Runnable[] runnables = new Runnable[12];*/
        /*for (int i = 0; i < 12; i++) {
            runnables[i] = () -> {

                String getStr = stringPool.get();
                System.out.println(Thread.currentThread().getName() + " get value: " + getStr);
                //stringPool.put(getStr);
            };
        }*/

        /*for (Runnable runnable : runnables) {
            new Thread(runnable).start();
        }*/

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3");
            stringPool.list.add("hello");
            try {
                stringPool.lock.lock();
                stringPool.condition.signalAll();
                Thread.sleep(1000);
                System.out.println("4");
                stringPool.lock.unlock();
                System.out.println("5-1");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        stringPool.get();

        System.out.println("now watch");
        //stringPool.put("hello two");
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //System.out.println("here");
        //stringPool.put("zheli");
    }
}

class StringPool {
    private int poolMaxSize = 0;
    private int semaphoreSize = 5;
    public List<String> list = new ArrayList<>();
    //private Semaphore semaphore = new Semaphore(semaphoreSize);
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public StringPool() {
        for (int i = 0; i < poolMaxSize; i++) {
            list.add("lazyman_" + i);
        }
    }

    public String get() {
        String resultStr = null;
        try {
            //semaphore.acquire();
            lock.lock();
            System.out.println("1");
            while (list.size() == 0) {
                System.out.println("2");
                condition.await();
                System.out.println("5-2");
            }
            System.out.println("6");
            resultStr = list.remove(0);
            lock.unlock();
            System.out.println("7");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return resultStr;
    }

    /*public void put(String str) {
        lock.lock();
        System.out.println("add: " + str);
        list.add(str);
        condition.signalAll();
        lock.unlock();
        semaphore.release();
    }*/


}
