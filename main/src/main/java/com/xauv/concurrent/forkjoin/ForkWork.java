package com.xauv.concurrent.forkjoin;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Date 2022/1/3 20:43
 * @Author Administrator
 * @Package com.xauv.concurrent.forkjoin
 * @Desc
 */
public class ForkWork extends RecursiveTask<Integer> {

    private static final int threadHold = 10;

    private int start;

    private int end;

    public ForkWork(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean e = end - start <= threadHold;
        if (e) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            int middle = (start + end) / 2;

            ForkWork work1 = new ForkWork(start, middle);
            ForkJoinTask<Integer> fork1 = work1.fork();

            ForkWork work2 = new ForkWork(middle + 1, end);
            ForkJoinTask<Integer> fork2 = work2.fork();

            Integer r1 = fork1.join();
            Integer r2 = fork2.join();
            sum += r1;
            sum += r2;
        }


        return sum;
    }

    public static void main(String[] args) {
        ForkWork work = new ForkWork(1, 100);
        System.out.println(work.compute());

        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
