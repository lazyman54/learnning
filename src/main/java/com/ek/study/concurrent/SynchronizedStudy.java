package com.ek.study.concurrent;

public class SynchronizedStudy {
    public static void main(String[] args) {
        final SyncClass syncClass = new SyncClass();

        Thread thread1 = new Thread(SyncClass::f1);
        Thread thread2 = new Thread(syncClass::f2);
        thread1.start();

        thread2.start();

    }
}

class SyncClass {

    public synchronized static void f1() {

        System.out.println("method 1");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void f2() {
        System.out.println("method 2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}