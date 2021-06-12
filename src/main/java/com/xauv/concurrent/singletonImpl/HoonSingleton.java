package com.xauv.concurrent.singletonImpl;

public class HoonSingleton {

    private volatile static HoonSingleton instance = null;

    //性能好，安全，懒加载
    public static HoonSingleton getInstance() {
        if(instance == null) {
            synchronized (HoonSingleton.class) {
                //如果第一个线程执行到这里 不判断，那么会 创建两个对象
                if(instance == null) {
                    instance = new HoonSingleton();
                }
            }
        }
        return instance;
    }
}
