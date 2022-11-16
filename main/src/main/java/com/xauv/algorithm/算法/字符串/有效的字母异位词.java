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
 * @Date 2022/11/16 20:34
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.字符串
 * @Desc
 *
 * 判断两个字符串是否字母异位词
 *
 * 比如字符串：anagram 和 nagaram
 *
 * anagram：3 个 a，1 个 n，1 个 g，1 个 r，1 个 m
 * nagaram：3 个 a，1 个 n，1 个 g，1 个 r，1 个 m
 *
 * 发现字母数量一致，但字母位置不一致，这种叫做字母异位词
 *
 */
public class 有效的字母异位词 {

    /**
     *
     * 假设字符串全都是小写字母，不包含特殊符号
     *
     * 思路 1：使用哈希表统计字母个数
     *
     * 思路 2：使用 int[]，表示 26 个英文小写字母数量统计
     *
     *
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c-'a'] = counts[c-'a'] + 1;
        }
        for (char c : t.toCharArray()) {
            counts[c-'a'] = counts[c-'a'] - 1;
        }
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }
}
