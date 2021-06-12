package com.xauv.concurrent.volatole$sychronized;

import sun.misc.Unsafe;
import java.util.ArrayList;
import java.util.List;

public class SynchronizedAtomic {

    static Integer a = 0;

    static Unsafe unsafe = UnsafeInstance.getUnsafeInstance();

    public static void main(String[] args) throws Exception {

        SynchronizedAtomic j = new SynchronizedAtomic();
        List<Thread> ts = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            Thread t = new Thread(j::add2);
            ts.add(t);
            t.start();
        }
        //等待所以线程执行完
        for (Thread t : ts) {
            t.join();
        }
        System.out.println(a);
    }

    //隐式锁，使用 synchronized
    public synchronized void add() {
        a = a + 1;
    }

    //手动加 monitor，和 synchronized 关键字效果一样
    //但是手动加，可以跨越方法进行加 monitor
    public void add2() {
        unsafe.monitorEnter(this);
        a = a + 1;
        unsafe.monitorExit(this);
    }

    //手动cas替换
    public void add3() {
        unsafe.compareAndSwapInt(this, 1, a + 1, a);
    }
}