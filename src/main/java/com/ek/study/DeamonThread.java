package com.ek.study;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/20
 */
public class DeamonThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new DeamonTask());

        thread.setDaemon(true);
        thread.start();
        System.out.println("main done");
    }


}


class DeamonTask implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("daemon go on.....");
        }
    }
}
