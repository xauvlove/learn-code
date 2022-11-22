package com.xauv.algorithm.算法.深度广度优先搜索算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Bing Juan
 * @date: 2022/11/21 15 55
 * @desc:
 */
public class 字母全排列 {

    public static List<String> allArrange(String string) {
        if (string == null || string.length() == 0) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        char[] chars = string.toCharArray();
        arrange(0, chars, new char[]{}, res);
        return res;
    }

    public static void arrange(int len, char[] chars, char[] curRes, List<String> res) {
        if (len >= chars.length) {
            res.add(String.valueOf(curRes));
            return;
        }

        // 获取所有选择
        List<Character> chooses = new ArrayList<>();
        for (Character c : chars) {
            chooses.add(c);
        }
        for (Character c : curRes) {
            chooses.remove(c);
        }

        char[] curNext = new char[chars.length];
        System.arraycopy(curRes, 0, curNext, 0, len);
        for (Character choose : chooses) {
            curNext[len] = choose;
            arrange(len+1, chars, curNext, res);
        }
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(allArrange(s));
    }
}
