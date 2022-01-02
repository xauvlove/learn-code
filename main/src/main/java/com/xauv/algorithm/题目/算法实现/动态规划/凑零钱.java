package com.xauv.algorithm.题目.算法实现.动态规划;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/11/07 15:58
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 * 有无数张面额为 1 2 5 的硬币
 * 如何获得 n 元钱，使用最少的硬币数量
 */
public class 凑零钱 {

    public static void main(String[] args) {
        System.out.println("凑够 50 元最少需要硬币数量：" + calculateByDp(50));
    }

    public static int calculateByDp(int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 5];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 2;
        dp[5] = 1;
        for (int curAmount = 6; curAmount <= amount; curAmount++) {
            // 当 amount = 6 时，需要看 amount - 各个硬币的剩余金额，那个是组成花费最小的
            // case1 比如 amount = 6, 如果先使用 1 元硬币，那么现在还剩 5 元要凑，那么看看 凑 5 元最少需要多少硬币
            // case2 比如 amount = 6，如果先使用 2元硬币，那么现在还需要凑 6-2=4 元，那么看看凑 4 元最少需要多少硬币
            // case3 比如 amount = 6，如果先使用 5元硬币，那么现在还需要凑 6-5=1 元，那么看看凑 1 元最少需要多少硬币
            // 其中 凑 0-5 元零钱我们早就计算出来了，那么只需要利用这些计算结果往上推即可

            // 当前金额减去一元硬币时，所剩余的金额
            int amount1 = curAmount - 1;
            int amount2 = curAmount - 2;
            int amount5 = curAmount - 5;

            int cost1 = dp[amount1];
            int cost2 = dp[amount2];
            int cost5 = dp[amount5];

            int minCost = Math.min(Math.min(cost1, cost2), cost5);
            dp[curAmount] = minCost + 1;
        }
        return dp[amount];
    }
}
