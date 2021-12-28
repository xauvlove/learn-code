package com.xauv.concurrent.synchronizedImpl;

import org.openjdk.jol.info.ClassLayout;

import java.io.PipedInputStream;
import java.util.concurrent.TimeUnit;

public class SynchronizedTest {

    public static final Object lock = new Object();

    public  synchronized static void accessResource1(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void accessResource2() {
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void accessResource3() {
        synchronized (SynchronizedTest.class) {
            try {
                System.out.println("***");
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
       /* SynchronizedTest synchronizedTest1 = new SynchronizedTest();
        SynchronizedTest synchronizedTest2 = new SynchronizedTest();
        new Thread(synchronizedTest1::accessResource3).start();
        new Thread(synchronizedTest2::accessResource3).start();*/
        System.out.println("aaa" + ClassLayout.parseInstance(lock).toPrintable());

        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("bbb" + ClassLayout.parseInstance(lock).toPrintable());

            }
        }).start();
    }
}
