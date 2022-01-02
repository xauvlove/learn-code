package com.xauv.concurrent.volatole$sychronized;

public class SingletonSample {

    private int value;

    static volatile SingletonSample singleton;

    public static void main(String[] args) {
        SingletonSample singleton = SingletonSample.getSingleton();
        System.out.println(singleton);;
    }

    public static SingletonSample getSingleton() {
        if (singleton == null) {
            synchronized (SingletonSample.class) {
                if (singleton == null) {
                    singleton = new SingletonSample();
                }
            }
        }
        return singleton;
    }
}