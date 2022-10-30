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
 * @Date 2022/10/30 21:46
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 *
 * 例如
 * 序列 1：1 3 5 9 7 11 58 23
 * 序列 2：3 6 5 1 7 12 23 28 58
 *
 * 公共子序列为：3 5 7 23
 *
 */
public class 最长公共子序列 {

    /**
     * dp[i][j] 为 array1 前 i 个元素 与 array2 前 j 个元素，最长公共子序列
     *
     * 序列 1：1 3 5 9 10
     * 序列 2：1 4 9 10
     *
     * dp[2][3] 为 [1, 3] 和 [1, 4, 9] 的公共子序列长度
     * dp[2][3] = 1
     *
     * 初始值
     * dp[i][0] = 0， dp[0][j] = 0
     *
     * 转移方程
     *
     * 如果 array[i-1] == array[j-1]，那么 dp[i][j] = dp[i-1][j-1] + 1
     *
     * @param array1
     * @param array2
     * @return
     */
    public static int lcs(int[] array1, int[] array2) {
        if (array1 == null || array2 == null || array1.length == 0 || array2.length == 0) {
            return -1;
        }
        int[][] dp = new int[array1.length+1][array2.length+1];
        for (int i = 0; i < array1.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < array2.length; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < array1.length + 1; i++) {
            for (int j = 1; j < array2.length + 1; j++) {
                // 如果第 i 个元素 == 第 j 个元素
                if (array1[i-1] == array2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[array1.length][array2.length];
    }

    public static int lcs2(int[] array1, int[] array2) {

        if (array1 == null || array2 == null || array1.length == 0 || array2.length == 0) {
            return -1;
        }
        return lcs(array1, array1.length, array2, array2.length);
    }

    /**
     *
     * 递归法
     *
     * 求 array1 前 i 个元素 和 array2 前 j 个元素最长公共子序列长度
     * @param array1
     * @param i
     * @param array2
     * @param j
     * @return
     */
    public static int lcs(int[] array1, int i, int[] array2, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (array1[i-1] == array2[j-1]) {
            return lcs(array1, i-1, array2, j-1) + 1;
        } else {
            return Math.max(lcs(array1, i-1, array2, j), lcs(array1, i, array2, j-1));
        }
    }

    public static void main(String[] args) {
        int[] array1 = {1,3,5,9,10};
        int[] array2 = {1,4,9,10};
        System.out.println(lcs(array1, array2));
    }
}
