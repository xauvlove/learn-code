package com.xauv.algorithm.算法.递归;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Stack;

/**
 * @Date 2022/10/26 21:29
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.递归
 * @Desc
 */
public class 递归转非递归 {

    /**
     * 求 1+2+3+....+n 和
     *
     * @param n
     * @return
     */
    public static int sum(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sum(n - 1);
    }

    /**
     * 非递归，压栈、出栈
     * @param n
     * @return
     */
    public static int sum2(int n) {
        if (n == 1) {
            return 1;
        }
        int sum = 0;
        Stack<Frame> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            Frame frame = new Frame(i, sum);
            stack.push(frame);
        }
        while (!stack.isEmpty()) {
            Frame pop = stack.pop();
            sum = sum + pop.add();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sum2(100));
    }

    private static class Frame {

        int n;

        int sum;

        public Frame(int n,  int sum) {
            this.n = n;
            this.sum = sum;
        }

        public int add() {
            return sum + n;
        }
    }
}
