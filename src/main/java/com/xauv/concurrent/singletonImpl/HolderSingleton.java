package com.xauv.concurrent.singletonImpl;

public class HolderSingleton {

    private HolderSingleton(){}

    public static HolderSingleton getInstance() {
        return Holder.instance;
    }

    /**
     *
     * holder会加载，但只有调用 getInstance() 才会进行instance的实例化，实现了懒加载
     * 由于这是一个类，只能加载一次，因此也是一个线程安全的
     * 应用较广泛
     */
    private static class Holder {
        private static HolderSingleton instance = new HolderSingleton();
    }
}
