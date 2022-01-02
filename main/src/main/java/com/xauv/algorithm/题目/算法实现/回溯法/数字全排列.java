package com.xauv.algorithm.题目.算法实现.回溯法;

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
 * @Date 2021/11/07 16:26
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 * 给定一批数字，给出全排列，不重复数字
 * 比如给出 1 2 3
 * 全排列为
 *  123 132 213 231 312 321
 */
public class 数字全排列 {

    public static void main(String[] args) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        int[] nums = new int[]{1,2,3};
        backTrack(nums, res, trace);
        System.out.println(res);
    }

    public static void backTrack(int[] nums, List<List<Integer>> res, List<Integer> trace) {

        // 由于所有数参与排列，因此，当 trace 的长度等于所有数的数量时，说明排列完成，加入结果集
        if (trace.size() >= nums.length) {
            res.add(new ArrayList<>(trace));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果这个决策已经做过了，那么不再做这个决策
            if (trace.contains(nums[i])) {
                continue;
            }
            // 加入这个决策，做这个决策
            trace.add(nums[i]);
            // 做完上面那个决策，再执行下一层的决策
            backTrack(nums, res, trace);
            // 由于决策是有很多的，其他决策也要尝试，因此撤销刚才所作的决策
            trace.remove(trace.size() - 1);
        }
    }
}
