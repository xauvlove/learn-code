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
 * @Date 2022/10/30 12:34
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.字符串匹配
 * @Desc
 */
public class 字符串字串所在为止 {

    /**
     * 查询字符串匹配的位置
     *
     * abcdefgh
     * cde
     *
     *
     * @param text
     * @param pattern
     * @return
     */
    public static int indexOfByForce(String text, String pattern) {
        if (text == null || pattern == null) {
            return -1;
        }
        char[] textChars = text.toCharArray();
        int tLen = textChars.length;
        if (tLen == 0) {
            return -1;
        }
        char[] patternChars = pattern.toCharArray();
        int pLen = patternChars.length;
        if (pLen == 0) {
            return -1;
        }
        if (tLen < pLen) {
            return -1;
        }

        // 利用两个指针
        int pi = 0;
        int ti = 0;

        while (pi < pLen && ti < tLen) {
            if (textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                ti = ti - (pi - 1);
                pi = 0;
            }
        }
        return (pi == pLen) ? (ti - pi) : -1;
    }

    /**
     * 查询字符串匹配的位置
     *
     * abcdefgh
     * cde
     *
     *
     * @param text
     * @param pattern
     * @return
     */
    public static int indexOfByForceOptimize(String text, String pattern) {
        if (text == null || pattern == null) {
            return -1;
        }
        char[] textChars = text.toCharArray();
        int tLen = textChars.length;
        if (tLen == 0) {
            return -1;
        }
        char[] patternChars = pattern.toCharArray();
        int pLen = patternChars.length;
        if (pLen == 0) {
            return -1;
        }
        if (tLen < pLen) {
            return -1;
        }

        // 利用两个指针
        int pi = 0;
        int ti = 0;
        // tLen - pLen 是匹配临界值
        int lenDelta = tLen - pLen;

        // ti - pi <= tLen - pLen 是匹配临界值
        // ti - pi 是每一轮比较中，首个字符的位置
        while (pi < pLen && (ti - pi) <= lenDelta) {
            if (textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                ti = ti - (pi - 1);
                pi = 0;
            }
        }
        return (pi == pLen) ? (ti - pi) : -1;
    }

    /**
     * 查询字符串匹配的位置
     *
     * abcdefgh
     * cde
     *
     *
     * @param text
     * @param pattern
     * @return
     */
    public static int indexOfByForce2(String text, String pattern) {
        if (text == null || pattern == null) {
            return -1;
        }
        char[] textChars = text.toCharArray();
        int tLen = textChars.length;
        if (tLen == 0) {
            return -1;
        }
        char[] patternChars = pattern.toCharArray();
        int pLen = patternChars.length;
        if (pLen == 0) {
            return -1;
        }
        if (tLen < pLen) {
            return -1;
        }

        int tiMax = tLen - pLen;

        for (int ti = 0; ti <= tiMax; ti++) {
            int pi = 0;
            for (; pi < pLen; pi++) {
                if (textChars[ti + pi] != patternChars[pi]) {
                    break;
                }
            }
            if (pi == pLen) {
                return ti;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int i = indexOfByForce("hello world", "h");
        System.out.println(i);
    }
}
