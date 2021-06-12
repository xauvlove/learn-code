package com.xauv.concurrent.volatileImpl;

public class Singleton {
    private volatile static Singleton instance = null;

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
