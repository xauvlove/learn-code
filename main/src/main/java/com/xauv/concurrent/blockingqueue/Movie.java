package com.xauv.concurrent.blockingqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Movie implements Delayed {

    private Long delayedTime;

    private String name;

    private Long submitTime;

    public Movie(Long delayedTime, String name) {
        this.delayedTime = delayedTime;
        this.name = name;
        this.submitTime = System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return  delayedTime + submitTime - System.currentTimeMillis();
    }

    public Long getDelayedTime() {
        return delayedTime;
    }

    public void setDelayedTime(Long delayedTime) {
        this.delayedTime = delayedTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(delayedTime - o.getDelay(TimeUnit.NANOSECONDS));
    }

    @Override
    public String toString() {
        return "Movie{" +
                "delayedTime=" + delayedTime +
                ", name='" + name + '\'' +
                '}';
    }
}
