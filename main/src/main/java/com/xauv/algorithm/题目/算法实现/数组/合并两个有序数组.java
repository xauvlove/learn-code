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
import java.util.Random;

/**
 * @Date 2022/11/2 20:00
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.数组
 * @Desc
 */
public class 合并两个有序数组 {

    /**
     *
     * 有两个有序数组
     * arr1 [1, 3, 5, 0, 0, 0]
     * arr2 [2, 4, 6]
     *
     * 将 arr1 和 arr2 合并，arr1 预留了 arr2.length 个位置，不改变数组的情况下，合并
     * 结果为：[1, 2, 3, 4, 5, 6]
     *
     *
     * 准备 3 个指针，
     * p1 指向元素 5
     * p2 指向元素 6
     * pm 指向 arr1 的第一个元素 0
     *
     * 比较 p1 和 p2 元素大小，然后进行指针的移动
     *
     *
     * @param nums1
     * @param nums2
     */
    public static void merge(int[] nums1, int[] nums2) {
        int p1 = nums1.length - nums2.length - 1;
        int p2 = nums2.length - 1;
        int pm = nums1.length - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[pm] = nums1[p1];
                pm--;
                p1--;
            } else {
                nums1[pm] = nums2[p2];
                pm--;
                p2--;
            }
        }
        while (p2 >= 0) {
            nums1[pm] = nums2[p2];
            p2--;
            pm--;
        }
    }

    public static void main(String[] args) {

        Random r = new Random();
        int[] arr1 = new int[30];
        int[] arr2 = new int[20];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = r.nextInt(100);
        }
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = r.nextInt(100);
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int[] arr11 = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, arr11, 0, arr1.length);

        // 开始合并
        merge(arr11, arr2);
        System.out.println(Arrays.toString(arr1));
    }
}
