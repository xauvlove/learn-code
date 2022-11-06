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

/**
 * @Date 2022/11/6 14:26
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.数组
 * @Desc
 */
public class 部分排序_寻找最大逆序对 {


    /**
     * 有数组 [1, 5, 4, 3, 2, 6, 7]
     *
     * 数组是无序的
     *
     * 问：如何将最小的乱序区间排序，使得整个数组有序
     *
     * 数组从 [5, 4, 3, 2] 是乱序的，只需要将这部分排序，就可使得整个数组有序
     *
     * 返回结果为元素下标 result = [1, 4]
     *
     * 解法为：寻找最大逆序对
     *
     * 可以发现
     * 5 4 是逆序的
     * 5 3 是逆序的
     * 5 2 是逆序的
     * 4 3 是逆序的
     * 4 2 是逆序的
     * 3 2 是逆序的
     *
     * 但最大的逆序对是 5 2，元素下标是 1 和 4
     *
     *
     * @param nums
     * @return
     */
    public static int[] subSort(int[] nums) {
        int max = nums[0];
        int r1 = -1;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num < max) {
                r1 = i;
            } else {
                max = num;
            }
        }
        if (r1 == -1) {
            return new int[]{-1, -1};
        }
        int min = nums[nums.length - 1];
        int r2 = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int num = nums[i];
            if (num > min) {
                r2 = i;
            } else {
                min = num;
            }
        }
        return new int[]{r2, r1};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 4, 3, 2, 6, 7};
        int[] rp = subSort(nums);
        System.out.println(Arrays.toString(rp));
    }
}
