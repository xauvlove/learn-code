package com.xauv.algorithm.算法.分治;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.映射.Map;

/**
 * @Date 2022/10/28 19:46
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.贪心
 * @Desc
 *
 * divide and conquer
 *
 * 将问题分解为子问题
 *
 * 子问题结构是一样的，只是规模不一样
 *
 * 子问题又可以分解为子问题，直到不能分解
 *
 * note：
 * 子问题之间是相互独立的
 *
 *
 * 应用：
 * 归并排序：将数组切分到不能再分，进行组内排序
 *
 * 快速排序
 *
 * 大数乘法
 *
 *
 * ----------------- 主定理 -----------------
 * 解决规模为 n 的问题，分解为 a 个规模为 n/b 的子问题
 * 然后在 O(n^d) 时间内，将 n/b 的子问题解合并
 *
 *
 *
 * 序列的连续性：
 *
 * 可以不连续：
 * 1.子序列
 *
 * 必须连续：
 * 1.字串
 * 2.子数组
 * 3.子区间
 *
 */
public class 分治 {

    /**
     *
     * 最大【连续】子序列和
     *
     * 给定长度为 n 的整数序列，求他的最大【连续】子序列的和
     *
     * 如：-2 1 -3 4 -1 2 1 -5 4
     *
     * 最大连续子序列 = 4 + (-1) + 2 + 1 = 6
     *
     *
     */
    public static int maxChildSequenceSummary(int[] array) {
        //return force1(array);
        return divideAndConquer(array);
    }

    /**
     * 使用分治
     *
     * 将 [begin, end) 分解成 [begin, mid) 和 [mid, end)
     *
     * 假设问题的解是：区间 S[i, j]，那么问题的解有三种情况
     *
     * 1.[i, j) 存在于 [begin, mid) 中
     *
     * 2.[i, j) 存在于 [mid, end) 中
     *
     * 3.[i, j) 一部分存在于 [begin, mid) 中，一部分存在于 [mid, end) 中
     *
     *
     *
     * @param array
     * @return
     */
    public static int divideAndConquer(int[] array) {
        if (array == null || array.length <= 0) {
            return Integer.MIN_VALUE;
        }
        return maxSubarray(array, 0, array.length);
    }

    /**
     * 求解序列 begin 到 end 最大连续子序列的和
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    public static int maxSubarray(int[] array, int begin, int end) {
        // 如果数组长度小于2，说明该数组序列最大子序列和就是这个元素本身
        if (end - begin < 2) {
            return array[begin];
        }
        int mid = (begin + end) >> 1;

        // 横跨了做序列和右序列的最大子序列和
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid-1; i >= begin; i--) {
            leftSum = leftSum + array[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum = rightSum + array[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        int max = leftMax + rightMax;

        return Math.max(max,
                Math.max(
                        // 分割后，左边序列最大子序列和
                        maxSubarray(array, begin, mid),
                        // 分割后，右边序列最大子序列和
                        maxSubarray(array, mid, end)));
    }

    /**
     * 暴力解
     *
     * 准备两个指针 begin  end
     * 然后不断移动两个指针，进行序列遍历加和
     * 选出最大的
     */
    public static int force(int[] array) {
        int max = 0;
        for (int p1 = 0; p1 < array.length; p1++) {
            for (int p2 = 0; p2 < array.length; p2++) {
                int sum = 0;
                int bi = p1;
                // 在这一步的时候，其实序列的加法重复做了很多次，这一步可以优化，见 force1()
                while (bi <= p2) {
                    sum = sum + array[bi++];
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    /**
     * 暴力解优化
     * @param array
     * @return
     */
    public static int force1(int[] array) {
        int max = 0;
        for (int p1 = 0; p1 < array.length; p1++) {
            int sum = 0;
            for (int p2 = p1; p2 < array.length; p2++) {
                sum = sum + array[p2];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxChildSequenceSummary(array));
    }
}
