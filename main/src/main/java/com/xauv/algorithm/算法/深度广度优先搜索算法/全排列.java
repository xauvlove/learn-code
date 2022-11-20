package com.xauv.algorithm.算法.深度广度优先搜索算法;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/11/20 18:29
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.深度广度优先搜索算法
 * @Desc
 *
 * 给定没有重复数字的序列
 * 返回所有可能的全排列
 * 例如，
 * 输入： 1 2 3
 * 输出：[1 2 3] [1 3 2] [2 1 3] [2 3 1] [3 1 2] [3 2 1]
 */
public class 全排列 {

    public static List<String> all(String digits) {

        if (digits == null || digits.equals("")) {
            return new ArrayList<>();
        }
        char[] ds = digits.toCharArray();
        List<String> res = new ArrayList<>();
        dfs(0, ds, new char[ds.length], res);
        return res;
    }

    public static void dfs(int len, char[] ds, char[] string, List<String> res) {
        if (len >= ds.length) {
            res.add(new String(string, 0, string.length));
            return;
        }
        char[] ls = new char[ds.length];
        if (string != null) {
            System.arraycopy(string, 0, ls, 0, len);
        } else {
            ls = new char[ds.length];
        }

        // 找出所有下一层的选择
        List<Character> choose = new ArrayList<>();
        for (char d : ds) {
            boolean find = true;
            for (int j = 0; j < len; j++) {
                if (d == ls[j]) {
                    find = false;
                    break;
                }
            }
            if (find) {
                choose.add(d);
            }
        }
        // 进行下一层的选择
        for (Character character : choose) {
            ls[len] = character;
            dfs(len + 1, ds, ls, res);
        }
    }

    public static void main(String[] args) {
        String s = "123456";
        System.out.println(all(s));
    }
}
