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
 * @Date 2022/10/26 23:06
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.递归
 * @Desc
 */
public class 尾递归优化 {

    /**
     * 使用递归进行阶乘
     *
     * 空间复杂度 O(n)
     *
     * 这种不是尾递归，jvm 无法优化成 while 形式
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n <= 1) {
            return n;
        }
        return n * factorial(n-1);
    }

    /**
     * 阶乘改为尾递归
     *
     * 结果保存到参数中
     * @param n
     * @return
     */
    public static int factorialOptimize(int n, int fac) {
        if (n <= 1) {
            return fac;
        }
        fac = fac * n;
        return factorialOptimize(n-1, fac);
    }

    public static int fib(int n, int first, int second) {
        if (n <= 1) {
            return first;
        }
        return fib(n-1, second, first + second);
    }

    public static int fib(int n) {
        return fib(n, 1, 1);
    }

    public static void main(String[] args) {
        int fac = factorialOptimize(4, 1);
        System.out.println(fac);

        System.out.println(fib(422));
    }
}
