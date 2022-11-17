package com.xauv.algorithm.算法.动态规划;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Arrays;

/**
 * @Date 2022/10/30 20:17
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 *
 * 给定无需整数序列，求最长上升子序列长度，不要求连续，严格上升【如果相等不算上升】
 *
 * 序列：10 2 2 5 7 1 101 18
 * 上升子序列：2 5 7 101 或者 2 5 7 18
 * 长度为 4
 *
 */
public class 最长上升子序列 {


    /**
     * dp[i] 为：以 array[i] 结尾的最长上升子序列长度
     *
     * 序列：10 2 2 5 7 1 101 18
     * @param array
     * @return
     */
    public static int maxIncreaseSubsequence(int[] array) {
        int[] dp = new int[array.length];
        // 初始化，所有元素最长长生子序列为 1
        Arrays.fill(dp, 1);
        int max = 0;
        // 遍历数组
        for (int i = 1; i < array.length; i++) {
            // 遍历之前已经处理过的元素
            for (int j = 0; j < i; j++) {
                // 如果当前元素大于之前元素，那么当前元素结尾最长子序列应为之前元素最长子序列 + 1
                if (array[i] > array[j]) {
                    int seqLen = dp[j] + 1;
                    if (seqLen > dp[i]) {
                        dp[i] = seqLen;
                    }
                    if (max < dp[i]) {
                        max = dp[i];
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 6, 4, 7, 9, 10, 8, 11};
        System.out.println(maxIncreaseSubsequence(arr));
    }
}
