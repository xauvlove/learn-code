package com.xauv.algorithm.刷题.数组;

/**
 * @author: Bing Juan
 * @date: 2022/11/17 16 53
 * @desc:
 */
public class 二分查找 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public static int search(int[] nums, int target) {
        // write code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int mid = (start + end) >> 1;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return -1;
    }
}
