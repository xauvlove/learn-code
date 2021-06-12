package com.xauv.concurrent.aqsImpl;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 的测试类
 *
 * 当 works 全都执行完毕后，才能继续执行主线程
 *
 * 会阻塞主线程
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        String[] works = new String[]{"work1", "work2", "work3", "work4"};
        CountDownLatch countDownLatch = new CountDownLatch(works.length);

        for (int i = 0; i < works.length; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    System.out.printf("Doing %s\n", works[finalI]);
                    //完成任务  信号量减一
                    countDownLatch.countDown();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            //完成所有任务后唤醒
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread running");
    }
}
