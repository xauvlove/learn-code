package com.xauv.algorithm.刷题.动态规划;

/**
 * @author: Bing Juan
 * @date: 2022/11/21 17 16
 * @desc:
 *
 *
 * 给定两个字符串 str1 和 str2 ，请你算出将 str1 转为 str2 的最少操作数。
 * 你可以对字符串进行3种操作：
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.修改一个字符。
 *
 * 输入："nowcoder","new"
 * 输出：6
 * "nowcoder"=>"newcoder"(将'o'替换为'e')，修改操作1次
 * "nowcoder"=>"new"(删除"coder")，删除操作5次
 *
 * 输入："intention","execution"
 *
 * 返回值：5
 * 说明：
 * 一种方案为:
 * 因为2个长度都是9，后面的4个后缀的长度都为"tion"，于是从"inten"到"execu"逐个修改即可
 *
 */
public class 编辑距离 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * dp[i][j] 为 str1[0, i) 转换为 str2[0, j) 的操作次数
     *
     * case1：
     * 将 str1[0, i-1) 转换为 str2[0, j)
     * 也即删除一个字符
     *
     * case2：
     * 将 str1[0, i) 转换为 str2[0, j-1)
     * 也即添加一个字符
     *
     * case3:
     * 将 str1[0, i-1) 转换为 str2[0, j-1)
     *
     * 如果 str1[i-1] == str2[j-1]
     * 则无需转换
     *
     * 如果 str1[i-1] != str2[j-1]
     * 这需要将 str1[i-1] 替换为 str2[j-1]
     *
     *
     * @param str1 string字符串
     * @param str2 string字符串
     * @return int整型
     */
    public static int editDistance (String str1, String str2) {
        // write code here
        int[][] dp = new int[str1.length()+1][str2.length()+1];

        char[] cs1 = str1.toCharArray();
        char[] cs2 = str2.toCharArray();

        // 初始值
        // s1[0,0) -> s2[0,0) 编辑 0 次
        dp[0][0] = 0;

        // s1[i,0) -> s2[0,0) 删除 i 次，即编辑 i 次
        for (int i = 1; i <= cs1.length; i++) {
            dp[i][0] = i;
        }
        // s2[i,0) -> s1[0,0) 删除 i 次，即编辑 i 次
        for (int i = 1; i <= cs2.length; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // s1[0,i) - s1[0,i-1) -> s2[0,j)
                int t1 = dp[i-1][j] + 1;
                // s1[0,i) -> s2[0,j-1) + s2[j-1)
                int t2 = dp[i][j-1] + 1;
                int t3;
                if (cs1[i-1] == cs2[j-1]) {
                    t3 = dp[i-1][j-1];
                } else {
                    t3 = dp[i-1][j-1] + 1;
                }
                dp[i][j] = Math.min(Math.min(t1, t2), t3);
            }
        }
        return dp[cs1.length][cs2.length];
    }


}
