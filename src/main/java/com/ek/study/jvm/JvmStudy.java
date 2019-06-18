package com.ek.study.jvm;

public class JvmStudy {

    public static void main(String[] args) {

        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(() -> {
            synchronized (obj1) {
                System.out.println("thread 1 lock obj 1");
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println("thread 1 lock obj 2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (obj2) {
                System.out.println("thread 2 lock obj 2");
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println("thread 2 lock obj 1");
                }
            }
        }).start();

    }

}
