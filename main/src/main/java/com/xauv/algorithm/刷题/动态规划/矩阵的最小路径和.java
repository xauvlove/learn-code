package com.xauv.algorithm.刷题.动态规划;

/**
 * @author: Bing Juan
 * @date: 2022/11/18 14 16
 * @desc:
 *
 * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 *
 * 数据范围: 1 \le n,m\le 5001≤n,m≤500，矩阵中任意值都满足 0 \le a_{i,j} \le 1000≤a
 * i,j
 * ​
 *  ≤100
 * 要求：时间复杂度 O(nm)O(nm)
 *
 * 例如：当输入[[1,3,5,9],[8,1,3,4],[5,0,6,1],[8,8,4,0]]时，对应的返回值为12，
 * 所选择的最小累加和路径如下图所示
 *
 * [1,3,5,9]
 * [8,1,3,4]
 * [5,0,6,1]
 * [8,8,4,0]
 *
 * 返回值 = 12
 */
public class 矩阵的最小路径和 {

    public static int minPathSum (int[][] matrix) {
        // write code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = matrix[i][0] + dp[i-1][0];
        }
        for (int i = 1; i < cols; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i-1];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
            }
        }
        return dp[rows-1][cols-1];
    }

    public static void main(String[] args) {
        int[][] arr = {
                {3,3,1,6,7,6,0,7,8,0},
                {5,0,5,0,5,3,9,8,0,4},
                {2,8,8,9,0,6,8,7,6,7},
                {6,1,0,9,0,9,6,0,6,6},
                {2,3,6,9,5,3,2,4,3,4},
                {1,5,2,1,4,5,8,4,2,6},
                {3,0,0,5,0,0,4,6,2,2},
                {4,6,6,2,5,1,7,9,8,0},
                {3,1,7,7,2,4,2,0,8,6},
                {9,4,7,9,9,7,1,4,5,5}};
        
        arr = new int[][]{{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPathSum(arr));
    }
}
