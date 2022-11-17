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
 * @Date 2022/11/17 19:21
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 *
 * 有一个棋盘，棋盘每个位置都放了一个礼物
 *
 * 棋盘只能往下或者往右移动，每次移动可以拿走一个礼物
 * 从棋盘左上角开始
 * 问最多拿走多大价值的礼物
 *
 * 棋盘
 * [1, 3, 1]
 * [1, 5, 1]
 * [4, 2, 1]
 *
 * 路径 1->3->5->2->1 可以拿走最大价值礼物
 */
public class 礼物的最大价值 {

    public static int maxValueGift(int[][] grid, int m, int n) {
        int[][] dp = new int[m][n];
        int maxValue = grid[0][0];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + grid[i-1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + grid[0][i-1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                if (dp[i][j] > maxValue) {
                    maxValue = dp[i][j];
                }
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[][] array = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(maxValueGift(array, array.length, array[0].length));
    }
}
