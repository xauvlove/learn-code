package com.xauv.concurrent.volatole$sychronized;

/**
 * /\   /\             /\.__
 * ___  __)/___)/  __ _____  _)/|  |   _______  __ ____
 * \  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \
 * >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/
 * /__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
 * \/     \/                                    \/
 */

/**
 * @Date 2021/04/10 17:37
 * @Author ling yue
 * @Package com.xauv.volatole$sychronized
 * @Desc 验证内存可见性
 */
public class 内存可见性 {

    public static boolean a = false;

    public static Object object = new Object();

    // volatile_a 是内存可见的
    public static volatile boolean volatile_a = false;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> a = true);

        Thread t2 = new Thread(() -> {
            // 1.自旋
            while(!a) {

            }
            // 2.加锁，由于加锁，cpu可能会发生上下文切换，上下文切换后，cpu 缓存会失效，再次切换会重新加载变量到缓存
            /*while(!a) {
                synchronized (object) {

                }
            }*/
            System.out.println("a = " + true);
        });
        t2.start();
        //t2 线程优先执行，确保 a 被加载到 cpu 缓存中
        Thread.sleep(1000L);
        t1.start();
        t2.join();
        t1.join();
    }
}
