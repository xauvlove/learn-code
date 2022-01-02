package com.xauv.concurrent.volatole$sychronized;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicUpdater {

    static final AtomicIntegerFieldUpdater<Student> updater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");

    static Unsafe unsafe = UnsafeInstance.getUnsafeInstance();

    //偏移量，变量在内存中的地址是一个范围[start, end]，那么偏移量是指：start 到某个属性所在内存地址的长度
    static long offset;

    static {
        try {
            assert unsafe != null;
            offset = unsafe.objectFieldOffset(Student.class.getDeclaredField("age"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(18);
        student.setName("com/xauv");
        System.out.println(student);
        //自增
        updater.incrementAndGet(student);
        System.out.println(student);

        //魔术类修改变量属性
        unsafe.compareAndSwapInt(student, offset, 19, 20);
        System.out.println(student);

    }
}

class Student {

    private String name;

    // 需要被原子修改的属性 必须是 volatile
    public volatile int age;

    public String getName() {
        return name;
    }

    public void setName(String student) {
        this.name = student;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
