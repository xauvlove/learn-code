package com.xauv.concurrent.aqsImpl;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier 等到指定的线程都完成准备工作
 * 所有的线程才能继续各自往下执行
 * 不妨碍主线程执行
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(8);
        for (int i = 0; i < cyclicBarrier.getParties(); i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                    System.out.println(Thread.currentThread().getName() + "准备好了");
                    //准备工作完成后 唤醒状态
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始执行");
            }).start();
        }
    }
}
