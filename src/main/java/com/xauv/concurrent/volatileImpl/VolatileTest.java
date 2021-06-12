package com.xauv.concurrent.volatileImpl;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    static int MAX = 5;
    static volatile int value = 0;

    public static void main(String[] args) {

        /**
         * 读操作
         */
        new Thread(() -> {
            int localVal = value;
            while(value < MAX) {
                if(localVal != value) {
                    System.out.println("value is " + value + ", localVal is " + localVal);
                    localVal = value;
                }
            }
        }, "reader").start();

        /**
         * 写操作，对读操作可见，让读操作感知到 value 改变
         */
        new Thread(() -> {
            while(value < MAX) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("value will be changed to " + (++value));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "outer").start();
    }
}
