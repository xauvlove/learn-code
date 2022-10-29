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
 * @Date 2022/10/29 10:06
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.查找
 * @Desc
 *
 * 给定数列，求出数值不断增大的序列，序列并不要求连续，但要求上升
 *
 * 数列
 * 10 2 2 5 1 7 101 18
 *
 * 最长上升子序列为：2 5 7 101 或者 2 5 7 18
 *
 *
 * 二分搜索法：
 *
 * 新建 n 个空牌顶
 *
 * 遍历所有的牌，再遍历所有的牌顶，如果当前牌 k，小于等于当前牌顶 top，则将牌放在当前牌顶，如果大于当前牌顶 top，则新建牌堆，将牌 k 放到这个牌顶
 *
 *
 * 数列
 * 10 2 2 5 1 7 101 18
 *
 * k = 10 ，当前无任何牌顶，新建牌堆
 *
 * 10
 *
 * k = 2，有一个牌堆，牌顶是 10，2 <= 10，将 2 放在此牌堆，当作新牌顶
 *
 * 10
 * 2
 *
 * k = 2，有一个牌堆，牌顶是 2，2 <= 2，将 2 放在此牌堆，当作新牌顶
 *
 * 10
 * 2
 * 2
 *
 * k = 5，有一个牌堆，牌顶是 2，5 > 2，新建牌堆，5 作为新牌堆的牌顶
 *
 * 10    5
 * 2
 * 2
 *
 * k = 1，有两个个牌堆，发现第一个牌堆牌顶比当前牌大，1 <= 2，将 1 放在第一个牌堆
 *
 * 10    5
 * 2
 * 2
 * 1
 *
 * k = 7，有两个个牌堆，这两个牌堆牌顶都比 7 大，新建牌堆
 *
 * 10    5    7
 * 2
 * 2
 * 1
 *
 * 将所有牌遍历完之后，形成的牌堆
 *
 * 10    5    7    101
 * 2                18
 * 2
 * 1
 *
 * 我们就知道，最长上升子序列为：
 * 2 5 7 101 或者 2 5 7 18
 *
 * 最长上升子序列的长度就是【牌堆的数量】
 *
 */
public class 最长上升子序列 {


    public static int longestIncSubsequence(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        // 牌堆的数量
        int len = 0;
        // 牌顶
        int[] top = new int[array.length];
        // 遍历所有的牌，形成牌堆
        for (int i = 0; i < array.length; i++) {
            // 遍历所有牌顶
            int j = 0;
            for (; j < len; j++) {
                // 牌顶大于当前牌，将牌放在当前牌顶
                if (top[j] >= array[i]) {
                    top[j] = array[i];
                    break;
                }
            }
            // 已有牌堆的牌顶都小于当前牌，新建牌堆，放入牌作顶
            if (j == len) {
                top[len] = array[i];
                len++;
            }
        }
        return len;
    }

    /**
     * 使用二分搜索优化
     * @param array
     * @return
     */
    public static int longestIncSubsequenceOptimize(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        // 牌堆的数量
        int len = 0;
        // 牌顶
        int[] top = new int[array.length];
        // 遍历所有的牌，形成牌堆
        for (int num : array) {
            int begin = 0;
            int end = len;
            // 使用二分搜索查找放牌位置
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }
            top[begin] = num;
            if (begin == len) {
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {10, 2, 2, 5, 1, 7, 101, 18};
        int len = longestIncSubsequenceOptimize(arr);
        System.out.println(len);
    }
}
