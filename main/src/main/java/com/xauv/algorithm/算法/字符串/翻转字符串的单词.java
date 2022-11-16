package com.xauv.algorithm.算法.字符串;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.google.errorprone.annotations.Var;

/**
 * @Date 2022/11/16 21:09
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.字符串
 * @Desc
 *
 * 字符串： the sky is blue
 * 反转后：blue is sky the
 *
 *
 * 翻转后，去掉两端空格，去掉多余空格，去掉标点符号
 *
 * 如：a good     example !
 * 翻转后：example good a
 *
 *
 */
public class 翻转字符串的单词 {

    /**
     * 思路：
     * 数组  【 , ,a, r ,e , , , y, o, u, , o, k, 】
     *
     * 消除多余空格
     * 去空  【a, r, e, , y, o, u, , o, k, , , , ,】
     *
     * 0 - 10 位逆序，[0, 10) 进行逆序，整个字符串（不含空）进行逆序
     * 逆序  【k, o, , u, o, y, , e, r, a, , , , ,】
     *
     * 分组逆序，每个单词逆序，[0, 2) [3, 6) [7, 10) 逆序
     * 逆序  【o, k, , y, o, u, , a, r, e, , , , ,】
     *
     * @param sentence
     * @return
     */
    public static String reversSentence(String sentence) {
        char[] chars = sentence.toCharArray();
        // 消除多余空格
        int len = 0;
        int cur = 0;
        // 默认为 true，防止一开始就是空格的情况
        boolean space = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                // 非空格
                chars[cur] = chars[i];
                cur++;
                space = false;
            } else {
                // 空格，如果上次没有经历过空格，
                if (!space) {
                    chars[cur] = chars[i];
                    cur++;
                    space = true;
                }
                // 空格，如果上次已经经历过空格，表示遇到了连续空格，进行下一次循环
            }
        }
        // 处理末尾空格情况
        len = space ? cur-1:cur;

        // 整体翻转，左闭右开
        reverse(chars, 0, len);

        // 分组翻转
        // 前一个非空格的位置
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') {
                continue;
            }
            reverse(chars, idx, i);
            idx = i + 1;
        }
        return new String(chars, 0, len);
    }

    /**
     * 翻转范围内的字符
     * @param chars
     * @param l
     * @param r
     */
    private static void reverse(char[] chars, int l, int r) {
        int li = l;
        int ri = r  - 1;
        while (li < ri) {
            char tmp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = tmp;
            li++;
            ri--;
        }
    }

    public static void main(String[] args) {
        String sentence = "a good     example ";
        System.out.println(reversSentence(sentence));
    }
}
