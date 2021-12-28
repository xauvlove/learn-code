package com.xauv.algorithm.题目.算法实现.排序;

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
 * @Date 2021/11/13 19:36
 * @Author ling yue
 * @Package com.xauv.algorithm.题目.算法实现.排序
 * @Desc
 */
public class 快速排序 {

    public static void main(String[] args) {
        int[] sz = init(10);
        System.out.println("正序快速排序前：" + Arrays.toString(sz));
        quickSort(sz, 0, sz.length - 1);
        System.out.println("正序快速排序后：" + Arrays.toString(sz));


    }

    public static void quickSort(int[] sz, int start, int end) {

        if (start >= end) {
            return;
        }
        int result = sort(sz, start, end);
        quickSort(sz, start, result - 1);
        quickSort(sz, result + 1, end);
    }

    /**
     * 正序快排
     * @param sz
     * @param start
     * @param end
     * @return
     */
    public static int sort(int[] sz, int start, int end) {

        int left = start;
        int right = end;

        int pivotValue = sz[start];

        while (left < right) {
            while (pivotValue <= sz[right] && left < right) {
                right --;
            }
            while (pivotValue >= sz[left] && left < right) {
                left ++;
            }

            if (left < right) {
                int value = sz[left];
                sz[left] = sz[right];
                sz[right] = value;
            }
        }

        sz[start] = sz[left];
        sz[left] = pivotValue;

        return left;
    }

    public static void quickSortReverse(int[] array, int start, int end) {

        if (start < end) {
            int partition = partitionReverse(array, start, end);
            quickSortReverse(array, start, partition - 1);
            quickSortReverse(array, partition + 1, end);
        }

    }

    /**
     * 倒序快排
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partitionReverse(int[] array, int start, int end) {

        int left = start;
        int right = end;
        int p = array[start];

        while (left < right) {
            // 倒叙快排仅仅是将 >= 变为 <=
            while (array[right] <= p && left < right) {
                right = right - 1;
            }
            // 倒叙快排仅仅是将 <= 变为 >=
            while (array[left] >= p && left < right) {
                left = left + 1;
            }
            if (left < right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }
        }

        array[start] = array[left];
        array[left] = p;
        return left;
    }


    public static int[] init(int length) {
        int[] sz = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sz[i] = random.nextInt(length * 10);
        }
        return sz;
    }

}
