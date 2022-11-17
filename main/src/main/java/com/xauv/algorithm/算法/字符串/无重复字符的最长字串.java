package com.xauv.algorithm.算法.字符串;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/


/**
 * @Date 2022/11/16 22:50
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.字符串
 * @Desc
 *
 * 给定字符串，找i出不含重复字符的最长字串
 *
 * 如：abcabcb
 * 最长无重复字符的最长字串为：abc，长度为 3
 *
 * 如：bbbb
 * 最长无重复字串为 b
 *
 * 如：pwwkew
 * 为：wke，长度为 3
 *
 */
public class 无重复字符的最长字串 {

    /**
     * 以 pwwkew 为例
     *
     *
     * 位置  结尾字符   以字符结尾最长无重复字串  长度
     *  0      p         p                  1
     *  1      w         pw                 2
     *  2      w         w                  1
     *  3      k         wk                 2
     *  4      e         wke                3
     *  5      w         kew                3
     *
     *
     *
     *
     * @param sequence
     * @return
     */
    public static String maxUniqueCharSubsequence(String sequence) {
        char[] chars = sequence.toCharArray();
        int[] res = new int[chars.length];
        res[0] = 1;
        int max = res[0];
        String subsequence = String.valueOf(chars[0]);
        // 当扫描到 i 的时候，遍历之前的元素
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            int re = res[i - 1];
            // li 是上一个字符，最长不重复字串的开始索引
            int li = i - re;
            res[i] = 1;
            for (int j = li; j < i; j++) {
                if (chars[j] == c) {
                    res[i] = 1;
                } else {
                    res[i] = res[i] + 1;
                }
            }
            if (max < res[i]) {
                max = res[i];
                subsequence = String.valueOf(chars, i-max+1, max);
            }
        }
        return subsequence;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(maxUniqueCharSubsequence(s));
    }
}
