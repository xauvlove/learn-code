package com.xauv.algorithm.算法.查找;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/20 21:37
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.排序
 * @Desc
 *
 * 对有序序列进行二分查找
 */
public class 二分搜索 {

    public static int binarySearch(int[] array, int value) {

        if (array == null || array.length == 0) {
            return -1;
        }

        int begin = 0;
        int end = array.length;

        // end - begin = 现有元素数量
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (value > array[mid]) {
                begin = mid + 1;
            } else if (value < array[mid]) {
                end = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sz = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int index = 二分搜索.binarySearch(sz, 5);
        System.out.println(index);
    }
}
