package com.xauv.concurrent.aqsImpl;

public class TestMyLock {
    private int m = 0;
    private MyLock myLock = new MyLock();
    public int next() {
        myLock.lock();
        try {
            //TimeUnit.SECONDS.sleep(2);
            return m++;
        } finally {
           myLock.unlock();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(testMyLock.next());
            }).start();
        }
    }
}
