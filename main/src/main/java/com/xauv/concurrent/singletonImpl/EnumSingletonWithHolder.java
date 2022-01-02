package com.xauv.concurrent.singletonImpl;

public class EnumSingletonWithHolder {
    private EnumSingletonWithHolder() {}

    private enum EnumSingleton {
        INSTANCE;
        private EnumSingletonWithHolder instance;

        /**
         * 由于是内部类，
         * 内部类是延时加载的，也就是说只会在第一次使用时加载
         * 因此这个构造函数被加载 仅当getInstance()被调用时
         * {@link EnumSingletonWithHolder#getInstance()}
         */
        EnumSingleton() {
            instance = new EnumSingletonWithHolder();
        }
        private EnumSingletonWithHolder getInstance() {
            return instance;
        }
    }

    public EnumSingletonWithHolder getInstance() {
        return EnumSingleton.INSTANCE.getInstance();
    }
}
