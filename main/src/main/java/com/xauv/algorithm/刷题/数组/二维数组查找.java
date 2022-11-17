package com.xauv.algorithm.刷题.数组;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Bing Juan
 * @date: 2022/11/17 16 00
 * @desc:
 *
 * [1,2,8,9]
 * [2,4,9,12]
 * [4,7,10,13]
 * [6,8,11,15]
 *
 * 二维数组从上到下，从左到右递增
 *
 * 查找是否包含 target
 *
 *
 */
public class 二维数组查找 {

    /**
     * 暴力解
     * @param target
     * @param array
     * @return
     */
    public static boolean Find(int target, int [][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int num = array[i][j];
                if (num == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * 纵轴使用二分查找
     *
     * @param target
     * @param array
     * @return
     */
    public static boolean binaryFind(int target, int [][] array) {
        if (array == null) {
            return false;
        }
        if (array.length == 0) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            boolean bia = binarySearch(array[i], target);
            if (bia) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] array, int target) {
        if (array.length == 0) {
            return false;
        }
        int start = 0;
        int end = array.length;
        while (start < end) {
            int mid = (start + end) / 2;
            int num = array[mid];
            if (num == target) {
                return true;
            } else if (num > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(binarySearch(nums, 1));

        int[][] array = {
                {0,1,3,4,5,7,8,11,13,15,18,21,24,27,30,32,35,36,39,41,42,43,46,49,52,55,58,60,63,66,67,69,72,75,78,80,81,82,85,86,},
                {1,4,6,8,11,12,15,17,18,20,23,24,27,30,33,34,38,39,42,44,47,48,51,52,55,57,59,62,64,67,70,72,75,77,81,83,84,87,90,91,},
                {4,7,8,11,14,16,18,20,21,24,27,29,32,35,36,39,40,42,44,46,49,52,54,56,58,60,61,64,67,70,73,76,78,81,84,87,89,91,93,96,},
                {5,8,10,13,15,19,21,23,24,27,29,31,34,37,38,41,43,45,46,49,52,55,58,59,61,64,67,69,71,72,76,78,79,83,87,90,91,94,96,97,},
                {6,11,14,16,18,22,24,27,29,32,33,35,36,40,42,44,47,50,51,52,54,58,60,62,64,67,70,73,76,79,82,84,87,88,91,94,97,99,101,102,},
                {9,13,16,19,21,23,25,29,31,35,38,39,42,45,48,51,54,56,57,60,63,64,67,69,72,73,74,76,79,81,85,88,90,92,95,98,100,101,104,106,},
                {10,16,19,22,24,26,29,31,34,36,40,41,45,46,50,54,56,59,60,63,66,69,70,72,75,77,79,81,83,85,88,91,93,96,98,99,102,105,107,109,},
                {12,18,22,25,26,29,32,33,37,39,42,44,47,50,52,57,59,61,62,66,68,71,72,74,76,80,82,84,87,90,92,94,95,98,101,102,105,107,109,112,}};


        System.out.println(binaryFind(13, array));
    }
}
