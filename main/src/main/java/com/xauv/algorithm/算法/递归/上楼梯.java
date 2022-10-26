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
 * @Date 2022/10/26 20:40
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.递归
 * @Desc
 *
 * 假设 n 阶台阶有 f(n) 种走法，如果现在走了一步：
 * 走一步，剩余 f(n-1) 种走法
 * 走两步，剩余 f(n-2) 种走法
 * 因此 f(n) = f(n-1) + f(n-2)
 */
public class 上楼梯 {

    public static int climbStairs(int level) {
        if (level == 1) {
            return 1;
        }
        if (level == 2) {
            return 2;
        }
        return climbStairs(level - 1) + climbStairs(level - 2);
    }

    /**
     * 非递归走台阶
     * @param level
     * @return
     */
    public static int climbStairs2(int level) {
        if (level == 1) {
            return 1;
        }
        if (level == 2) {
            return 2;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= level; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(10));
        System.out.println(climbStairs2(10));
    }
}
