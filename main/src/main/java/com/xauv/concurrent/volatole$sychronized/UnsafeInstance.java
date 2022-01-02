package com.xauv.concurrent.volatole$sychronized;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 魔术类
 * 可用于手动加内存屏障
 * 手动调用 CAS 算法进行值替换
 */
public class UnsafeInstance {

    public static Unsafe getUnsafeInstance() {
       try {
           Field field = Unsafe.class.getDeclaredField("theUnsafe");
           field.setAccessible(true);
           return (Unsafe) field.get(null);
       } catch (Exception e) {
           return null;
       }
    }
}
