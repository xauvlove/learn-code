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
 * @Date 2022/11/19 11:49
 * @Author Administrator
 * @Package com.xauv.algorithm.刷题.字符串
 * @Desc
 *
 * 给定两个字符串，计算出 word1 转换为 word2 所使用的最少操作数
 *
 * 对字符串可以使用三种操作
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.替换一个字符
 *
 *
 * 例如：
 * word1 = horse
 * word2 = ros
 *
 * 最少需要三次操作
 *
 * horse -> rorse，将 h 替换为 r
 * rorse -> rose，将 r 删除
 * rose -> ros，将 e 删除
 *
 */
public class 编辑距离 {

    /**
     * 将 mice 转换为 arise
     *
     * 使用动态规划
     * dp[word1.length+1][word2.length+1]
     *
     * dp[0][0] = 0;
     *
     *
     *         [j]    a     r    i     s     e
     *     dp   0     1     2    3     4     5
     *
     *[i] 0     0
     *
     *  m 1
     *
     *  i 2
     *
     *  c 3
     *
     *  e 4
     *
     *
     * dp[i][j] 是 word1[0,i) 转换为 word2[0,j) 的最少操作数
     * word1[0,i) 是 word1 从第 0 位到第 i 位的这些字符拼接的字符串，例如 word1[0,3) = mic
     *
     * dp[2][3] 为 word1[0,2) 转换为 word2[0,3) 的最小操作数
     * 也就是 mi 转换成 ari 的最少操作数
     *
     * 我们要的答案是 dp[word1.length][word2.length]，
     * 表示为：word1[0][word1.length-1] 到 word2[0][word2.length-1] 的最少操作数
     *
     *
     * 令 s1 = word1 ，s2 = word2
     *
     * 初始值：
     * dp[0][0]：表示为 s1 的空子串转换为 s2 的空子串所需最小操作数，为 0
     * dp[0][1]：表示为 s1 的空字串转换为 s2 的第一个字符所需最少操作数， "" -> "a"，只需增加一个字符可完成转换，操作数：1
     * ...
     * dp[0][i]：表示为 s1 的空字串转换为 s2[0, i) 最少操作数，操作数：i
     *
     * 同理，
     * dp[i][0]：表示 s1[0, i) 转换为 s2 的空串，需要删除所有字符可完成转换，操作数：i
     *
     *
     * 初始值为：dp[0][0] = 0， dp[0][i] = i， dp[i][0] = i
     *
     *          [j]    a     r    i     s     e
     *     dp    0     1     2    3     4     5
     *
     * [i] 0     0     1     2    3     4     5
     *
     *  m  1     1
     *
     *  i  2     2
     *
     *  c  3     3
     *
     *  e  4     4
     *
     *
     * 初始值已经确定，
     * 状态转移方程：
     * 有 4 种情况【转换方法】
     *
     * 1.先删除 s1[0, i) 的最后一个字符，得到 s1[0, i-1)
     *   然后由 s1[0, i-1) 转换为 s2[0, j)
     *   这样的话，dp[i][j] = dp[i-1][j] + 1
     *
     * 2.先由 s1[0, i) 转换为 s2[0, j-1)，
     *   然后在最后插入字符 s2[j-1]，得到 s2[0, j)
     *   这样的话，dp[i][j] = dp[i][j-1] + 1
     *
     * 3.先删除 s1[0, i) 的最后一个字符，得到 s1[0, i-1)
     *   再删除 s2[0, j) 的最后一个字符，得到 s2[0, j-1)
     *   将 s1[0, i-1) 转换为 s2[0, j-1)
     *   然后在最后替换一个字符 s2[j-1]，得到 s2[0, j)
     *   这样的话，dp[i][j] = dp[i-1][j-1] + 1
     *
     *   举例：s1 = horse, s2 = ros
     *   现在 i = 5, j = 3，也就是 horse -> ros
     *   可以知道，现在进行的是 dp[i][j] = dp[5][3]，在进行 dp[5][3] 之前，我们已经把 dp[4][2] dp[4,1] ... 都计算完了
     *   --
     *   发现，s1[i-1] != s2[j-1] 也就是 s1[4] != s2[2]
     *   我们已经知道 dp[4][2] 也就是  hors -> ro 的最少操作数了，为 dp[i-1][j-1]
     *   也就是 hors -> ro 需要 dp[i-1][j-1] 步，此时 hors 转换为了 ro，我们只需要在 ro 后面加一个[替换] s 即可完成转换
     *   这样 dp[i][j] = dp[i-1][j-1] + 1
     *
     *
     *
     * 4.s1[0, i) = s2[0, j) 无需转换，其实只需要判断 s1[i-1] == s2[j-1] 即可，判断最后一个字符是否相等
     *   这样的话，dp[i][j] = dp[i-1][j-1]
     *
     *   例子：horse -> roe
     *   求 dp[5][3]，发现 s1[4] == s2[2]
     *   也就是说，我们无需操作最后一个字符，dp[5][3] 完全取决于 dp[4][2] 是多少，
     *   也就是，当 i=5,j=3 时， horse -> roe 完全取决于 hors -> ro 需要多少步
     *   因此 dp[5][3] = dp[4][2]
     *
     *
     * 4 种情况由三条路：上，左，左上，根据 dp[i-1][j]  dp[i][j-1]  dp[i-1][j-1] 求解
     *
     * 选取这 4 种情况最少的操作数
     *
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int editDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        char[] cs1 = word1.toCharArray();
        char[] cs2 = word2.toCharArray();
        int[][] dp = new int[cs1.length+1][cs2.length+1];
        // 初始化
        dp[0][0] = 0;
        for (int i = 1; i <= cs1.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= cs2.length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= cs1.length; i++) {
            for (int j = 1; j <= cs2.length; j++) {
                // 尝试情况 1
                int left = dp[i-1][j] + 1;
                // 尝试情况 2
                int top = dp[i][j-1] + 1;
                // 尝试情况 3 和 4
                int leftTop;
                if (cs1[i-1] == cs2[j-1]) {
                    leftTop = dp[i-1][j-1];
                } else {
                    leftTop = dp[i-1][j-1] + 1;
                }
                // 4 种情况取最小
                dp[i][j] = Math.min(Math.min(left, top), leftTop);
            }
        }
        return dp[cs1.length][cs2.length];
    }

    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";
        System.out.println(editDistance(s1, s2));
    }
}
