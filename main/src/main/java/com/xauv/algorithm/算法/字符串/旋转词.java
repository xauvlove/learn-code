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
 * @Date 2022/11/16 19:17
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.栈
 * @Desc
 *
 * 给定两个字符串，判断两个字符串是否互为旋转词
 *
 * 比如单词：back
 *
 * 旋转：ackb
 *
 * 旋转：ckba
 *
 * 我们发现：ackb 将每个字符拆解后为：a c k b，我们从 b 开始，循环往前读，仍然是 back
 *
 * 同理，ckba 拆解后为： c k b a，我们从 b 开始，循环往前读，仍然为 back
 *
 * 同理 kbac 也是旋转词
 *
 *
 *
 * 思路和规律：
 *
 * back 的旋转词有：
 * 【ackb】【ckba】【kbac】
 *
 * 我们将原词拼接起来为：backback
 *
 * 可以发现它的三个旋转词全都包含在拼接词中
 * 比如：ackb 被包含在 b[ackb]ack 中
 *      ckba 被包含在 ba[ckba]ck 中
 *      kbac 被包含在 bac[kbac]k 中
 *
 * 也就是说，旋转词是两个原词拼接的字串
 */
public class 旋转词 {

    public static boolean isRotateWord(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return false;
        }
        String cbWord = word1 + word1;
        return cbWord.contains(word2);
    }

    public static void main(String[] args) {
        System.out.println(isRotateWord("back", "ckba"));
        System.out.println(isRotateWord("back", "cbka"));
    }
}
