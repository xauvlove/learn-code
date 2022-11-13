package com.xauv.algorithm.题目.算法实现.数组;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date 2022/11/12 22:28
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.数组
 * @Desc
 *
 * 给定数组
 *
 * 给定滑动窗口，大小为 k
 *
 * 求所有滑动窗口的最大值
 *
 * 比如数组 [1, 3, -1, -3, 5, 3, 6, 7]，滑动窗口大小为 3
 *
 * 则会产生下面窗口和对应窗口最大值
 * 【1 3 -1】     3
 * 【3 -1 -3】    3
 * 【-1 -3 5】    5
 * 【-3 5 3】     5
 * 【5 3 6】      6
 * 【3 6 7】      7
 *
 * 如果数组大小为 n，滑动窗口大小为 k，则所有窗口共 n-(k-1)
 *
 */
public class 滑动窗口最大值 {

    /**
     * 使用双端队列
     *
     * 思路 1【暴力法】：
     * 使用一个指针，从 nums[0]开始，指针一直往后移动，直到移动到 n-(k-1)
     * 移动过程中，不断计算 nums[i] 到 nums[i+k-1] 的最大值
     *
     *
     * 思路 2【双端队列】：
     * 使用两个指针 i 和 w
     * i 用于遍历数组，w 是窗口的左指针，i 是窗口右指针
     * 使用一个双端队列，从尾部添加元素
     * 队列中的元素是数组的索引位置，队列中的元素（索引）对应的数组元素值，从头到尾是依次减小的，
     *
     * 初始位置：w = -2, i = 0
     *        [1, 3, -1, -3, 5, 3, 6, 7]
     *  w      i
     *
     *  双端队列：[0, 1]
     *
     *
     * 一次移动：w = -1, i = 1
     *         [1, 3, -1, -3, 5, 3, 6, 7]
     *     w       i
     *
     *  双端队列：[1, 3]
     *
     *
     *
     * 二次移动：w = 0, i = 2，现在 w 是合法位置，拥有了一个滑动窗口
     *        [1, 3, -1, -3, 5, 3, 6, 7]
     *         w      i
     *
     *  双端队列：[1, 3] <-> [2, -1]
     *
     *
     * 三次移动：w = 1, i = 3，现在 w 是合法位置，拥有了一个滑动窗口
     *        [1, 3, -1, -3, 5, 3, 6, 7]
     *            w       i
     *
     *  双端队列：[1, 3] <-> [2, -1] <-> [3, -3]
     *
     *
     * 指针 i 和 w 刚好是窗口
     *
     * first: 表示队头
     * last: 表示队尾
     *
     * 1.如果 nums[i] >= nums[last]，不断删除队尾，直到 nums[last] > nums[i] 为止
     * 2.将 i 加入队尾
     * 3.如果 w >= 0，
     * 3.1 如果队头失效 first < w，则移除对头
     * 3.2 如果对头没失效 first >= w，则从对头弹出，弹出的索引就是窗口最大值
     *
     *
     * @param array
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] array, int k) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        if (k == 1) {
            return array;
        }
        int[] res = new int[array.length - (k - 1)];

        Deque<Integer> queue = new LinkedList<>();
        int i = 0;
        for (; i < array.length; i++) {
            // 只要 array[队尾] < array[i]，就删除队尾
            while (!queue.isEmpty() && array[i] > array[queue.peekLast()]) {
                queue.pollLast();
            }
            // 来到这里，要么是队列为空，要么是当前 num 小于队列对应元素所有值，将当前值入队
            queue.offerLast(i);
            // 看当前是否为窗口，如果是窗口，则记录窗口最大值
            int w = i - (k - 1);
            if (w < 0) {
                continue;
            }
            // 看一下队头是否失效
            if (queue.peekFirst() < w) {
                // 队头不在窗口范围内
                queue.pollFirst();
            }
            // 设置窗口最大值
            res[w] = array[queue.peekFirst()];
        }
        return res;
    }

    /**
     *
     * @param array
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] array, int k) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        if (k == 1) {
            return array;
        }
        int[] res = new int[array.length - (k - 1)];

        // 求出前 k 个元素最大值索引
        int maxIndex = 0;
        for (int i = 1; i < k; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int li = 0; li < array.length - (k - 1); li++) {
            int ri = li + (k - 1);
            if (maxIndex < li) {
                maxIndex = li;
                // 最大值索引过期，扫描
                for (int i = li + 1; i <= ri; i++) {
                    if (array[i] > array[maxIndex]) {
                        maxIndex = i;
                    }
                }
            } else {
                // 最大值索引没有过期，和新进的元素进行比较
                if (array[ri] >= array[maxIndex]) {
                    maxIndex = ri;
                }
            }
            res[li] = array[maxIndex];
        }
        return res;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums = {1,7,5,3,-3,-1,8,2};
        int[] window = maxSlidingWindow2(nums, 3);
        System.out.println(Arrays.toString(window));
    }
}
