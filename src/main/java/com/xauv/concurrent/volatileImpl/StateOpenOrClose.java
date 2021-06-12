package com.xauv.concurrent.volatileImpl;

import java.util.concurrent.TimeUnit;

public class StateOpenOrClose implements Runnable{

    private static boolean switchKey = true;

    @Override
    public void run() {
        while(switchKey) {
            doWork();
        }
    }

    private void doWork() {
        System.out.println("Working");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        switchKey = false;
    }


    public static void main(String[] args) {
        StateOpenOrClose stateOpenOrClose = new StateOpenOrClose();
        new Thread(stateOpenOrClose).start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                stateOpenOrClose.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
