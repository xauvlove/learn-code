package com.xauv.concurrent.volatole$sychronized;

/**
 *
 */
public class VolatileSample {

    static int a = 0;
    static int b = 0;
    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws Exception {

        for (;;) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread t1 = new Thread(()->{
                //会发生指令重排
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread t2 = new Thread(() -> {
                //会发生指令重排
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("x = " + (x+"") + ", y = " + (y+""));
            if (x == 0 && y == 0) {
                System.out.println("x = 0, y = 0");
                break;
            }
        }
    }
}
