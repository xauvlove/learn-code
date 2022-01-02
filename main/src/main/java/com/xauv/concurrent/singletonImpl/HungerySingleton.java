package com.xauv.concurrent.singletonImpl;

public class HungerySingleton {

    private static HungerySingleton instance = new HungerySingleton();
    private HungerySingleton(){}

    public static HungerySingleton getInstance() {
        return instance;
    }
}
