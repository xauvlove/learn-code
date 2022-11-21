package com.xauv.algorithm.算法.深度广度优先搜索算法;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.*;

/**
 * @Date 2022/11/20 17:29
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.BFS
 * @Desc
 *
 * 给出仅包含 0-9 的字符串
 * 返回他能表示的所以字母组合
 *
 * 数字的映射：
 * 【1: !@#】  【2: abc】 【3: def】
 * 【4: ghi】  【5: jkl】 【6: mno】
 * 【7: pqrs】 【8: tuv】 【9: wxyz】
 * 【*: +】    【0: _】   【#: -】
 *
 * 我们这里的题目的映射为：
 * 【0: abc】 【1: def】【2: ghi】
 * 【3: jkl】 【4: mno】【5: pqrs】
 * 【6: tuv】 【7: wxyz】
 *
 *
 * 比如：
 * 输入：23，2 和 3 能够映射 abc def 这些个字母，输出的字母长度等于输入字符串长度
 *
 * 类似于打电话时，按手机号码
 *
 * 输出: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 *
 * 思路：回溯
 *
 *
 *
 *
 */
public class 电话号码的字母组合 {

    private static char[][] letters = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.equals("")) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        char[] chars = digits.toCharArray();
        dfs(0, chars, null, res);
        return res;
    }

    public static void dfs(int index, char[] chars, char[] string ,List<String> res) {
        if (index >= chars.length) {
            // 已经进入到最后一层了，不能再深入，而且到这里已经得到了一个解
            res.add(new String(string, 0, string.length));
            return;
        }
        // 先枚举这一层可以做的所有选择
        char digit = chars[index];
        char[] chooses = letters[digit - '2'];
        char[] ls = new char[chars.length];
        if (string != null) {
            System.arraycopy(string, 0, ls, 0, index);
        } else {
            ls = new char[chars.length];
        }
        for (Character letter : chooses) {
            ls[index] = letter;
            dfs(index+1, chars, ls, res);
        }
    }

    public static void main(String[] args) {
        String s = "23";
        System.out.println(letterCombinations(s));
    }
}
