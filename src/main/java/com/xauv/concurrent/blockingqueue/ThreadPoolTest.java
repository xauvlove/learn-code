package com.xauv.concurrent.blockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                System.out.println(finalI);
            });
        }
        //停止正在执行的任务，终止等待执行的任务并返回处于等待队列中的任务
        threadPool.shutdownNow();
    }
}
