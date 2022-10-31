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
 * @Date 2022/10/31 21:58
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 *
 * n 件物品，和一个最大承重为 W 的背包
 * 每件物品重量 wi，价值 vi
 *
 * 每个物品只有一件，也就是物品只能选 0 件或者 1 件，因此叫做 0-1 背包0
 */
public class 背包问题 {

    /**
     * 状态：
     * dp[i][j] 为，最大承重为 j，有前 i 件物品可选时，的最大总价值
     *
     * i ∈ [0, n]
     * j ∈ [0, W]
     *
     * 假设
     * 物品编号为：    0 1 2 3 4
     * values[] 为： 6 3 5 4 6
     * weights[] 为：2 2 6 5 4
     *
     * dp[3][7] 重大承重为 7，有前 3 件物品可选时的最大价值
     * 也就是，
     * 只能选择价值为 values[0] values[1] values[2] 的物品，
     * 只能选择价值为 weights[0] weights[1] weights[2] 的物品，
     * 也就是说：只能选择物品编号为 0 1 2 的物品，dp[3][7] = 9
     *
     *
     * 初始值：
     * dp[i][0] = 0
     * dp[0][j] = 0
     *
     *
     * 递推公式：
     *
     *
     * @return
     */
    public static int pack(int[] values, int weights[], int capacity) {
        return 0;
    }

}
