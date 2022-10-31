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
 * @Date 2022/10/31 21:18
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 *
 * 如：
 * ABCBA 和 BABCA 的公共字串为：ABC
 *
 *
 */
public class 最长公共字串 {

    /**
     *
     * dp[i][j] 是以 str1[i-1] str2[j-1] 结尾的最长公共子串的长度
     *
     * ABCBA 和 BABCA
     *
     * 状态：
     * dp[2][3] 是以 A[B]CBA 和 BA[B]CA 结尾的公共字串长度，dp[2][3] = 2
     * dp[2][2] 是以 A[B]CBA 和 B[A]BCA 结尾的公共字串长度，dp[2][2] = 0
     *
     * 初始值：
     * dp[i][0] 和 dp[0][j] = 0
     *
     * 如果 str[i-1] = str2[j-1]，那么 dp[i][j] = dp[i-1][j-1] + 1
     * 如果 str[i-1] != str2[j-1]，那么 dp[i][j] = 0
     *
     * 最长公共子串长度为 max(dp[i][j])
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int commonSubsequence(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int max = 0;
        for (int i = 1; i < c1.length+1; i++) {
            for (int j = 1; j < c2.length+1; j++) {
                if (c1[i-1] == c2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(dp[i][j], max);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s1 = "abcbaaccaca";
        String s2 = "cbxbaaqcacacb";
        System.out.println(commonSubsequence(s1, s2));
    }
}
