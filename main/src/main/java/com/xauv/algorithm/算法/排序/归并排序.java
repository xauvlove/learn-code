package com.xauv.algorithm.算法.排序;
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
 * @Date 2022/10/22 18:32
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.排序
 * @Desc
 *
 * 归并排序是将一个序列不断地分割成子序列，直到不能继续分割
 *
 * 然后针对子序列，不断合二为一【合并成有序序列】，直到成为一个序列
 *
 * 例如：56 23 85 41 12 9 24 11 46
 *
 * 先拆分序列
 * 【56 23 85 41】                【12 9 24 11 46】
 *
 * 【56 23】【85 41】            【12 9】【24 11 46】
 *
 * 【56】【23】【85】【41】       【12】【9】【24】【11】【46】
 *
 *
 * 此时已经不能继续拆分，执行合并
 * 【23 56】 【41 85】              【9 12】【11 24】 【46】
 *
 * 【23 41 56 85】                      【9 11 12 24】 【46】
 *
 * 【23 41 56 85】   【9 11 12 24 46】
 *
 * 【9 11 23 24 41 46 56 85】
 *
 *
 * 因此发现，归并排序分为 2 个步骤：拆分、合并  divide， merge
 *
 */
public class 归并排序 {

    public static void main(String[] args) {
        int[] array = {56, 23, 85, 41, 12, 9, 24, 11, 46};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        sort(array, 0, array.length);
    }

    /**
     * 对 [begin, end) 范围内的数据进行排序
     *
     * 拆分、排序都是在同于一个数组内进行的，只是用 begin 和 end 区分了位置
     *
     * 首先要明确的是：切割的过程是递归过程：
     * 首先将 1 个序列切成 2 个序列
     * 将这 2 个序列再切成 4 个序列
     *
     * @param begin
     * @param end
     */
    private static void sort(int[] array, int begin, int end) {
        // 由于是 [begin, end) 左闭右开，因此 end -begin = 数组长度
        // 比如 0 1 2 3 4 ，begin = 0， end = 4， 4-0=4 就是数组长度
        // 如果数组区间小于 2，没有必要进行归并排序了
        if (end - begin < 2) {
            return;
        }
        int mid = (end + begin) >> 1;
        sort(array, begin, mid);
        sort(array, mid, end);
        // sort(begin, end) 相当于已经把数组 divide 了，接下来就是 merge
        merge(array, begin, mid, end);
    }

    /**
     * ------ 将 [begin, mid) 和 [mid, end) 合并成一个有序序列 ------
     *
     * 其中， [begin, mid) 和 [mid, end) 都已经是有序序列了（元素只有一个也是有序序列）
     *
     * 其中 [begin, mid) 和 [mid, end) 两个序列是挨在一起的
     *
     * 例如 要合并：
     * begin       mid       end
     * 【8 10 12 14】【3 6 11 18】
     *
     * 需要在同一个数组内【也就是同一块连续内存内】进行排序操作
     *
     * 考虑备份左边部分到一个新数组，变为
     *
     * array1 = 【8 10 12 14 | 3 6 11 18】
     * array2 = 【8 10 12 14】
     * 占据两块内存，
     *
     *  然后使用两个指针，对 array1 和 array2 排序，结果放在 array1 中
     *
     *  此时指针指向：
     *
     *  point1 = array1[4]
     *  point2 = array2[0]
     *
     *  第一次指针比较：
     *  array1 = 【3 10 12 14 3 6 11 18】
     *  array1 = 【8 10 12 14】
     *
     *  point1 = array1[5]
     *  point2 = array2[0]
     *
     *  第二次指针比较：
     *  array1 = 【3 6 12 14 3 6 11 18】
     *  array1 = 【8 10 12 14】
     *
     *  point1 = array1[5]
     *  point2 = array2[0]
     *
     *  第三次指针比较：
     *  array1 = 【3 6 8 14 3 6 11 18】
     *  array1 = 【8 10 12 14】
     *
     *  point1 = array1[5]
     *  point2 = array2[1]
     *
     * ...
     * ...
     * ...
     *
     * 直到 point1 或者 point2 已经到达数组尾部，再将 array2 没有经历过 point2 指针移动的部分移动到 array1 中
     *
     *
     * @param array
     * @param begin
     * @param mid
     * @param end
     */
    private static void merge(int[] array, int begin, int mid, int end) {
        // 左数组指针
        int li = 0;
        // 左数组长度
        int le = mid - begin;

        // 数组指针
        int ri = mid;
        int re = end;

        // 数组指针，从 array 的 begin 开始
        int ai = begin;

        int[] leftArray = new int[mid - begin];

        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        // 只有在 左数组 还没遍历结束时，才执行循环
        while (li < le) {
            if (ri < re && array[ri] < leftArray[li]) {
                array[ai] = array[ri];
                ai++;
                ri++;
            } else {
                array[ai] = leftArray[li];
                ai++;
                li++;
            }
        }
    }
}
