package com.xauv.concurrent.aqsImpl;

public class TestMyLock2 {
    private int m = 0;
    private MyLock lock = new MyLock();

    public void a() {
        lock.lock();
        System.out.println("a --");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b --");
        lock.unlock();
    }

    public static void main(String[] args) {
        TestMyLock2 testMyLock2 = new TestMyLock2();
        new Thread(()->{testMyLock2.a();}).start();
    }
}
