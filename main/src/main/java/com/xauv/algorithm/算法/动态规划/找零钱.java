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
 * @Date 2022/10/30 13:55
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 */
public class 找零钱 {

    /**
     *
     * 分治法，有很多的重复计算，效率并不高
     *
     * 假设 dp(n) 是凑到 n 分零钱的最少硬币个数
     *
     * dp(20) 是凑到 20 分零钱的最少硬币个数
     *
     * 如果第一次选择了 25 分的硬币，那么 dp(n) = 1 + dp(n-25)
     * 如果第一次选择了 20 分的硬币，那么 dp(n) = 1 + dp(n-20)
     * 如果第一次选择了 5 分的硬币，那么 dp(n) = 1 + dp(n-5)
     * 如果第一次选择了 1 分的硬币，那么 dp(n) = 1 + dp(n-1)
     *
     * 因此 dp(n) = 1 + min{dp(n-25), dp(n-20), dp(n-5), dp(n-1)}
     *
     *
     */
    public static void changesByDivideAndConquer(int n) {
        int coins = coins1(n);
        System.out.printf("共需要 %d 个硬币", coins);
    }

    public static int coins1(int n) {
        if (n <= 0) {
            return Integer.MAX_VALUE;
        }
        if (n == 25 || n == 20 || n == 5 || n == 1) {
            return 1;
        }
        int min1 = Math.min(coins1(n-25), coins1(n-20));
        int min2 = Math.min(coins1(n-5), coins1(n-1));
        return Math.min(min1, min2) + 1;
    }

    /**
     * 记忆化搜索，自顶向下的搜索
     *
     * 使用分治，存在非常多的重叠子问题
     *
     * 考虑将子问题的结果记录下来，遇到子问题不再重复计算
     */
    public static void changesByCache(int n) {
        if (n <= 0) {
            return;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[5] = 1;
        dp[20] = 1;
        dp[25] = 1;
        int coins = coins2(n, dp);
        System.out.printf("共需要 %d 个硬币", coins);
    }

    public static int coins2(int n, int[] dp) {
        if (n <= 0) {
            return Integer.MAX_VALUE;
        }
        if (dp[n] == 0) {
           int min1 = Math.min(coins2(n-25, dp), coins2(n-20, dp));
           int min2 = Math.min(coins2(n-5, dp), coins2(n-1, dp));
           dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }


    /**
     * 递推，自底向上进行递推，到最后直接获取问题解
     *
     * 先计算子问题，并保存
     *
     * @param n
     */
    public static void changesByDp(int n) {
        if (n <= 0) {
            return;
        }
        int[] dp = new int[n + 1];
        int[] faces = new int[dp.length];
        for (int i = 1; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;
            if (i >= 1 && dp[i-1] < min) {
                min = dp[i-1];
                faces[i] = 1;
            }
            if (i >= 5 && dp[i-5] < min) {
                min = dp[i-5];
                faces[i] = 5;
            }
            if (i >= 20 && dp[i-20] < min) {
                min = dp[i-20];
                faces[i] = 20;
            }
            if (i >= 25 && dp[i-25] < min) {
                min = dp[i-25];
                faces[i] = 25;
            }
            dp[i] = min + 1;
        }
        int coins = dp[n];
        System.out.printf("共需要 %d 个硬币\n", coins);
        System.out.print("面额是：");
        int change = n;
        while (true) {
            int face = faces[change];
            change = change - face;
            System.out.print(face + ", ");
            if (change <= 0) {
                break;
            }
        }
        System.out.println();
    }

    /**
     * 递推，自底向上进行递推，到最后直接获取问题解
     *
     * 先计算子问题，并保存
     *
     * @param n
     */
    public static void changesByDp2(int n, int[] faces) {
        if (n <= 0) {
            return;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                if (i < face) {
                    continue;
                }
                min = Math.min(dp[i - face], min);
            }
            dp[i] = min + 1;
        }
        int coins = dp[n];
        System.out.printf("共需要 %d 个硬币\n", coins);
    }

    public static void main(String[] args) {
        changesByDp(41);

        changesByDp2(41, new int[]{3,5});
    }

}