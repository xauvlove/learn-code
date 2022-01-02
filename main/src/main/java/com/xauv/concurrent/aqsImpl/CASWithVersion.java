package com.xauv.concurrent.aqsImpl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 带版本的CAS
 * 解决 ABA 问题
 */
public class CASWithVersion {
    /**
     * 给出值后，给出版本
     */
    private static AtomicStampedReference asr =
            new AtomicStampedReference(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(asr.compareAndSet(100, 110, asr.getStamp(), asr.getStamp()+1));
                System.out.println(asr.compareAndSet(110, 100, asr.getStamp(), asr.getStamp()+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        /**
         * 先拿到版本，停留一段时间等待其他线程修改
         * 此时该线程执行时，版本不对，修改失败
         */
        new Thread(() -> {
            try {
                int stamp = asr.getStamp();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(asr.compareAndSet(110, 100, stamp, stamp+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
