package com.xauv.concurrent.singletonImpl;

public enum EnumSingleton {
    /**
     * 线程安全，非懒加载
     */
    INSTANCE;
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
