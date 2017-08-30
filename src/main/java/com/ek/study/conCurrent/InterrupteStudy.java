package com.ek.study.conCurrent;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/8/22
 */
public class InterrupteStudy {
    public static void main(String[] args) throws InterruptedException {
        InterruptedSleepingThread thread = new InterruptedSleepingThread();
        thread.start();
        //Giving 10 seconds to finish the job.
        Thread.sleep(6000);
        //Let me interrupt
        thread.interrupt();
    }
}
class InterruptedSleepingThread extends Thread {

    @Override
    public void run() {
        doAPseudoHeavyWeightJob();
    }

    private void doAPseudoHeavyWeightJob() {
        for (int i=0;i<Integer.MAX_VALUE;i++) {
            //You are kidding me
            System.out.println(i + " " + i*2);
            //Let me sleep <evil grin>
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("Thread interrupted\n Exiting...");
                break;
            }else {
                sleepBabySleep();
            }
        }
    }

    /**
     *
     */
    protected void sleepBabySleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}