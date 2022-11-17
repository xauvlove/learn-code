package com.xauv.algorithm.基本数据结构.栈;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;
import com.xauv.algorithm.题目.数据结构.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Date 2022/11/13 19:11
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.栈
 * @Desc
 *
 * 假如每日温度为
 *
 * [73, 74, 75, 71, 69, 72, 76, 73]
 *
 * 计算从第 i 天开始，要隔多少天才能升温
 *
 * 比如现在是 73°，那么隔一天就可以升温了【升温到 74°】
 *
 * 假如现在是 75°，要隔 4 天，才能升温到 76°
 *
 * 假如现在是 76°，那么再也无法升温了
 *
 * 最终表现结果为：
 *
 * 温度：[73, 74, 75, 71, 69, 72, 76, 73]
 * 间隔：[ 1,  1,  4,  2,  1,  1,  0,  0]
 *
 *
 * 也就是，找到右边第一个比它大的值
 *
 * 【使用单调栈】
 *
 */
public class 每日温度 {

    /**
     * 使用单调栈，栈单调递减
     *
     * 1.如果元素比栈顶元素大，则弹栈
     *
     * 2.如果元素比栈顶元素小，则入栈
     *
     * @param array
     * @return
     */
    public static int[] dailyTemperatures(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty()) {
                if (array[i] > array[stack.peek()]) {
                    Integer pop = stack.pop();
                    res[pop] = i - pop;
                } else {
                    break;
                }
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 倒推法
     *
     * 定义结果数组：dp[]
     *
     * 每日温度：
     * 73, 74, 75, 71, 69, 72, 76, 73
     *
     * 最后一天温度为 73°
     * dp[7] = 0
     * 然后往前推
     *
     *
     * @param array
     * @return
     */
    public static int[] dailyTemperatures2(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        int[] dp = new int[array.length];
        dp[array.length-1] = 0;

        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] >= array[i+1]) {
                for (int j = i+1; j < array.length; j++) {
                    if (array[i] < array[j]) {
                        dp[i] = j-i;
                        break;
                    }
                }
            } else {
                dp[i] = 1;
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        //int[] array = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] array = {11, 53, 43, 23, 23, 23, 33};
        int[] temperatures1 = dailyTemperatures(array);
        System.out.println(Arrays.toString(temperatures1));
        int[] temperatures2 = dailyTemperatures2(array);
        System.out.println(Arrays.toString(temperatures2));
    }
}
