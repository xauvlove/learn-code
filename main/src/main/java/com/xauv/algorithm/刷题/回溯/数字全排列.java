package com.xauv.algorithm.刷题.回溯;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Bing Juan
 * @date: 2022/11/21 16 10
 * @desc:
 */
public class 数字全排列 {

    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        if (num == null || num.length == 0) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        dfs(0, num, new ArrayList<>(), res);
        return res;
    }

    public static void dfs(int len, int[] num, ArrayList<Integer> cur, ArrayList<ArrayList<Integer>> res) {
        if (len >= num.length) {
            res.add(cur);
            return;
        }
        // 找出下面所有的选择
        List<Integer> chooses = new ArrayList<>();
        for (Integer i : num) {
            chooses.add(i);
        }
        for (Integer c : cur) {
            chooses.remove(c);
        }

        for (Integer choose : chooses) {
            ArrayList<Integer> next = new ArrayList<>(cur);
            next.add(choose);
            dfs(len+1, num, next, res);
        }
    }

    public static void main(String[] args) {
        int[] r = {1,2,3};
        System.out.println(permute(r));
        String s = JSONArray.toJSONString(null);
        System.out.println(s);
        List<JSON> jsons = JSONArray.parseArray(s, JSON.class);
        System.out.println(jsons);
    }
}
