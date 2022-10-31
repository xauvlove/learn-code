package com.xauv.algorithm.算法.动态规划;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/30 20:01
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 */
public class 最大连续子序列和 {

    /**
     *
     * 假设 dp[i] 是以 array[i] 结尾的最大连续子序列和
     *
     *
     * 数组 array[] 索引：  0  1   2  3   4  5  6   7  8
     *    序列 array[] ： -2  1  -3  4  -1  2  1  -5  4
     *
     * dp[0] = -2，以 array[0] 结尾的最大连续子序列和为 -2
     * dp[1] = 1，以 array[1] 结尾的最大连续子序列和为 1
     * dp[2] = -2，以 array[2] 结尾的最大连续子序列和为 -2
     * dp[3] = 4，以 array[3] 结尾的最大连续子序列和为 4
     * ...
     * ...
     * ...
     *
     *
     * @param array
     * @return
     */
    public static int maxSubsequenceSummary(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < array.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + array[i];
            } else {
                dp[i] = array[i];
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {-2,  1,  -3,  4,  -1,  2,  1,  -5,  4};
        System.out.println(maxSubsequenceSummary(array));
    }
}
