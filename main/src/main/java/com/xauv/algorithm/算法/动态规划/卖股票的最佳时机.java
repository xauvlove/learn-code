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
 * @Date 2022/11/17 19:43
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 *
 * 股票价格按照时间顺序存储，
 * 如果买卖一次股票，最多可以获取多少收益
 *
 * 股价：[7, 1, 5, 3, 6, 4]
 * 时间：[1, 2, 3, 4, 5, 6]
 *
 * 可以发现，在第 2 天买，第 5 天卖，可以获取最大利润，利润为：6 - 1 = 5
 *
 * 如果股价为：[7, 6, 4, 3, 1]，那么无论怎么买卖都是亏钱的，那么收益为 0
 *
 */
public class 卖股票的最佳时机 {

    /**
     *
     * 非动态规划，
     *
     * 倒推法，把每一天都当作卖
     *
     * 比如：
     * 股价：[7, 1, 5, 3, 6, 4]
     * 时间：[1, 2, 3, 4, 5, 6]
     *
     * 把第 6 天当作卖出日，发现第 2 天买可以获取最大利润，【第 2 天是前面价格最低的一天】
     * 把第 5 天当作卖出日，发现第 2 天买可以获取最大利润
     * ...
     * 把第 2 天当作卖出日，发现第 2 天买可以获取最大利润，也就是利润为 0
     * 把第 1 天当作卖出日，前面没有买入日了，利润为 0
     *
     * 我们只要找到最小的价格，就是开盘期间最低价，以后的任何时候卖出都是有收益的
     *
     *
     * @param price
     * @return
     */
    public static int maxProfit(int[] price) {
        int maxProfit = 0;
        int minPrice = price[0];
        for (int i = 1; i < price.length; i++) {
            if (price[i] < minPrice) {
                minPrice = price[i];
            } else {
                maxProfit = Math.max(maxProfit, price[i] - minPrice);
            }
        }
        return maxProfit;
    }

    /**
     *
     * dp[i][j] 为第 i 天买，第 j 天卖
     *
     * @param price
     * @return
     */
    public static int maxProfitDp(int[] price) {
        int maxProfit = 0;
        int[][] dp = new int[price.length][price.length];
        for (int i = 1; i < dp.length; i++) {
            for (int j = i + 1; j < dp[i].length; j++) {
                dp[i][j] = price[j] - price[i];
                maxProfit = Math.max(maxProfit, dp[i][j]);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] array = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfitDp(array));
    }
}
