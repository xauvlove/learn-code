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

    }
}
