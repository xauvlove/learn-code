package com.xauv.concurrent.lock;

/**
 * /\   /\             /\.__
 * ___  __)/___)/  __ _____  _)/|  |   _______  __ ____
 * \  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \
 * >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/
 * /__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
 * \/     \/                                    \/
 */

/**
 * @Date 2021/04/10 16:38
 * @Author ling yue
 * @Package com.xauv.volatole$sychronized
 * @Desc
 *
 * 使用控制台 jps 命令 可查看 java 进程
 * 其中包含一个死锁的进程，使用 jstack + 进程id 命令 可查看死锁原因排查死锁
 */
public class DeadLock {

    public static final String A = "A";

    public static final String B = "B";

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
           synchronized (A) {
               try {
                   Thread.sleep(2000L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (B) {
                   System.out.println("t1");
               }
           }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("t2");
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
