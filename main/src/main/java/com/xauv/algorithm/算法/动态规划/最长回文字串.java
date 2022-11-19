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
 * @Date 2022/11/19 13:49
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.动态规划
 * @Desc
 *
 * 求字符串的最长回文字串
 *
 * 如：babad，则最长回文字串为：bab，aba，长度为 3
 * 如：
 *
 *
 */
public class 最长回文字串 {

    /**
     * 暴力法
     *
     * 找出所有字串，然后每个字串判断是否回文
     *
     * 使用两个指针
     * i: 字串开头
     * j: 字串结尾
     *
     * @return
     */
    public static String max() {
        return null;
    }

    /**
     *
     * 假设 s = babad
     *
     * dp[s.length][s.length]
     *
     * dp[i][j] 为 s[i] ~ s[j] 是否为回文串，存储 boolean 类型
     *
     * 初始值：
     * dp[0][0] = true
     * dp[i][i] = true，对角线为 true
     *
     * j >= i，二维数组的下半部分是无意义的，dp[4][3]，4 > 3 是无意义的
     *
     *
     * 思路：两种情况
     *
     * 1.如果 s[i, j] 长度 <= 2
     *
     * 例如 s[1,2] s[2,2] s[3,4]，
     * dp[i][j] 取决于 s[i][j]
     * 判断 s[i] 是否等于 s[j]，  dp[i][j] = ( s[i] == s[j] )，表示是否为回文串
     *
     *
     * 2.s[i, j] 长度 > 2
     * 如果 s[i+1, j-1] 为回文串，并且 s[i] == s[j]，那么 s[i, j] 为回文串
     *
     * 例如：a b a b a
     * 判断 s[1, 3] 是否为回文串，只需要判断 s[2, 2] 是否为回文串，并且 s[1] 是否等于 s[3]
     * 也就是说，如果判断串是否为回文串，取决于两个部分
     * 串的中间的字串部分
     * 串的两端的字符
     * 比如 a b a b a，判断 s[0, 4]，
     * 那么必须 s[1, 3]=bab 为回文串，
     * 并且两端 s[0] 和 s[4] 相等
     * s[0, 4] 才是回文串
     *
     *
     * @param s
     * @return
     */
    public static String maxSubsequence(String s) {
        if (s == null) {
            return "";
        }
        int len = s.length();
        if (len == 0) {
            return "";
        }
        boolean[][] dp = new boolean[len +1][len +1];

        for (int i = 0; i <= len; i++) {
            dp[i][i] = true;
        }

        char[] cs = s.toCharArray();

        String max = "";
        // 从下到上，从左到右的顺序
        // 需要根据左下的数据推导，由 dp[i+1][j-1] 推导 dp[i][j]
        for (int i = len-1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                // 字串长度小于等于 2 的情况
                if (j-i+1 <= 2 && cs[i] == cs[j]) {
                    dp[i][j] = true;
                    continue;
                }
                // 字串长度大于 2 的情况
                if (dp[i+1][j-1] && cs[i] == cs[j]) {
                    // 设置 dp
                    dp[i][j] = true;
                    // 记录最长回文字串
                    if (dp[i][j] && max.length() < (j-i+1)) {
                        max = String.valueOf(cs, i, (j-i+1));
                    }
                }
            }
        }
        return max;
    }

    /**
     * 思路 2 ：扩展中心法
     *
     * 比如：a b b a b a
     *
     * 扫描每一个字符，扫描到这个字符时，向两端扩展，看能扩展多远，【看对称多远】
     *
     * 比如，扫描到 a b b a [b] a
     * [b] 能够向左右扩展 1 位，因此，以 [b] 为中心的回文字串为 a[b]a
     *
     * 但这样的解法，得到的结果长度是奇数，其实  abbaaba 最长回文字串为 abba，用刚才的方法只能得到 bab 和 aba，显然不对
     *
     * ==========
     *
     * 考虑每个字符增加间隙
     * a # b # b # a # b # a
     *
     * 或者不增加间隔符，将间隙作为扫描字符，这样可以省 O(n) 的空间复杂度
     *
     * 扫描字符和间隙，
     *
     * @return
     */
    public static String longestPalindrome(String s) {

        char[] cs = s.toCharArray();

        String max = "";

        for (int i = cs.length - 2; i >= 1; i--) {
            // 以字符为中心，向左右扩展
            int len1 = palindromeLength(cs, i-1, i+1);
            // 以间隙为中心，向左右扩展
            int len2 = palindromeLength(cs, i, i+1);
            int len = Math.max(len1, len2);
            // 记录最长字串
            if (len > max.length()) {
                max = String.valueOf(cs, i-(len-1)/2, len);
            }
        }
        return max;
    }

    /**
     *
     * 从 l 开始向左扫描
     * 从 r 开始向右扫描
     * 获得的最长回文字串的长度
     *
     * @param cs
     * @param l
     * @param r
     * @return
     */
    private static int palindromeLength(char[] cs, int l, int r) {
        while (l >= 0 && r < cs.length) {
            if (cs[l] == cs[r]) {
                l--;
                r++;
            } else {
                break;
            }
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        String s = "abbab";
        System.out.println(longestPalindrome(s));
    }
}
