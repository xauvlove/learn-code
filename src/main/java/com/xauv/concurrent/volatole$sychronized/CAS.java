package com.xauv.concurrent.volatole$sychronized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * cas 替换 ABA
 */
public class CAS {

    static AtomicInteger value = new AtomicInteger(1);

    static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) throws Exception {

        //模拟ABA
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "开始CAS替换,现在 value = " + value.get());
            boolean isSuccess = value.compareAndSet(1, 2);
            if (isSuccess) {
                System.out.println(Thread.currentThread() + "CAS替换完成,现在 value = " + value.get());
            } else {
                System.out.println(Thread.currentThread() + "CAS替换失败,现在 value = " + value.get());
            }
        });
        Thread t2 = new Thread(CAS::aba);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("-------------------------ABA问题模拟完成-------------------------");

        //ABA解决，使用工具类

        Thread tt1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int version = 3;
            System.out.println(Thread.currentThread() + "开始CAS替换,现在 value = " + reference.getReference() + " 使用版本 = " + version);
            boolean isSuccess = reference.compareAndSet(1, 2, version, 4);
            if (isSuccess) {
                System.out.println(Thread.currentThread() + "CAS替换完成,现在 value = " + reference.getReference() + " 版本 = " + reference.getStamp());
            } else {
                System.out.println(Thread.currentThread() + "CAS替换失败,现在 value = " + reference.getReference() + " 版本 = " + reference.getStamp());
            }
        });
        Thread tt2 = new Thread(CAS::abaWithVersion);
        tt1.start();
        tt2.start();
        tt1.join();
        tt2.join();
    }

    public static void aba() {
        System.out.println(Thread.currentThread() + "开始自增,现在 value = " + value.get());
        value.incrementAndGet();
        System.out.println(Thread.currentThread() + "自增完成,现在 value = " + value.get());
        System.out.println(Thread.currentThread() + "开始自减,现在 value = " + value.get());
        value.decrementAndGet();
        System.out.println(Thread.currentThread() + "自减完成,现在 value = " + value.get());
    }

    public static void abaWithVersion() {
        System.out.println(Thread.currentThread() + "开始增加,现在 value = " + reference.getReference() + " 版本 = " + reference.getStamp());
        reference.compareAndSet(1, 2, 1, 2);
        System.out.println(Thread.currentThread() + "增加完成,现在 value = " + reference.getReference() + " 版本 = " + reference.getStamp());
        System.out.println(Thread.currentThread() + "开始减少,现在 value = " + reference.getReference() + " 版本 = " + reference.getStamp());
        reference.compareAndSet(2, 1, 2, 3);
        System.out.println(Thread.currentThread() + "减少完成,现在 value = " + reference.getReference() + " 版本 = " + reference.getStamp());
    }
}