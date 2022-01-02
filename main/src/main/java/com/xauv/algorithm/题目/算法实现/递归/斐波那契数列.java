package com.xauv.algorithm.题目.算法实现.递归;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/11/07 15:39
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 *
 * 0、1、1、2、3、5、8、13、21、34、55
 * 1、2、3、4、5、6、7、8、9、10
 * f(n) = f(n-1) + f(n-2)
 */
public class 斐波那契数列 {

    public static void main(String[] args) {
        System.out.println("递归计算：" + calculate(10));
        System.out.println("dp 计算：" + calculateByDP(10));
        System.out.println("变量 计算：" + calculateTemVariable(10));
    }

    /**
     * 使用两个变量的解法
     * 使空间复杂度降低
     * @param index
     * @return
     */
    public static int calculateTemVariable(int index) {
        int last = 0;
        int cur = 1;
        int next = -1;
        for (int i = 3; i <= index; i++) {
            next = last + cur;
            last = cur;
            cur = next;
        }
        return next;
    }

    /**
     * dp 解法
     * @param index
     * @return
     */
    public static int calculateByDP(int index) {
        int[] dp = new int[index+1];
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= index; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[index];
    }

    /**
     * 递归
     * @param index
     * @return
     */
    public static int calculate(int index) {
        if (index == 1) {
            return 0;
        }
        if (index == 2) {
            return 1;
        }
        return calculate(index - 1) + calculate(index - 2);
    }
}
