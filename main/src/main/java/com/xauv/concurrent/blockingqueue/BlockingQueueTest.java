package com.xauv.concurrent.blockingqueue;

import java.util.concurrent.DelayQueue;

public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Movie> queue = new DelayQueue<>();

        Movie m1 = new Movie(3000L, "m1");
        Movie m2 = new Movie(1000L, "m2");
        Movie m3 = new Movie(2000L, "m3");
        queue.put(m1);
        queue.put(m2);
        queue.put(m3);
        while(queue.size() > 0) {
            System.out.println(queue.take().getName());
        }
    }
}
