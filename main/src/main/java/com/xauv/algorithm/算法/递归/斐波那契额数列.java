package com.xauv.algorithm.算法.递归;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/26 20:14
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.递归
 * @Desc
 *
 * 1 1 2 3 5 8 13 21 34
 */
public class 斐波那契额数列 {

    public static int fib(int c) {
        if (c == 1) {
            return 1;
        }
        if (c == 2) {
            return 1;
        }
        System.out.println(c);
        return fib(c-1) + fib(c-2);
    }

    public static int solveByVer(int c) {
        int p1 = 1;
        int p2 = 1;
        // 由于 p1 p2 已经占了 2 位了，因此从第三位开始
        for (int i = 2; i < c; i++) {
            int tmp = p2;
            p2 = p1 + p2;
            p1 = tmp;
        }
        return p2;
    }

    public static int solveByDp(int c) {
        int[] dp = new int[c];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[c-1];
    }

    public static void main(String[] args) {
        //System.out.println(fib(333));
        System.out.println(solveByVer(44));
        System.out.println(solveByDp(44));
    }
}
