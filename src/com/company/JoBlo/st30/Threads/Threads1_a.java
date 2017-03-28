package com.company.JoBlo.st30.Threads;

import java.util.concurrent.TimeUnit;

/**
 * Created by olko1016 on 01/27/2017.
 */
public class Threads1_a {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    ;

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    ;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested()) {
                    i++;
                }
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }

    ;
}
