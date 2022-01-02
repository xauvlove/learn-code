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

    public static void main(String[] args) {
        System.out.println(cc(0));
        System.out.println(cc(1));
        System.out.println(cc(2));
        System.out.println(cc(3));
        System.out.println(cc(4));
        System.out.println(cc(5));
        System.out.println(cc(6));
        System.out.println(cc(7));
        System.out.println(cc(8));
        System.out.println(cc(9));
    }

    public static boolean cc(int a) {
        return (a&-a)==a;
    }
}
