package com.xauv.concurrent.aqsImpl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基本的 CAS 问题
 */
public class BasicCAS {

    private static AtomicInteger atomicInteger =  new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(atomicInteger.compareAndSet(100, 110));
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(atomicInteger.compareAndSet(110, 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(atomicInteger.compareAndSet(100, 120));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
