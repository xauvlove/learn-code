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
 * @Date 2021/11/13 17:15
 * @Author ling yue
 * @Package com.xauv.algorithm.题目.算法实现.排序
 * @Desc
 */
public class 鸡尾酒排序 {

    public static void main(String[] args) {

        int[] sz = init(10);
        System.out.println("鸡尾酒排序前：" + Arrays.toString(sz));
        sort(sz);
        System.out.println("鸡尾酒排序后：" + Arrays.toString(sz));
    }

    public static void sort(int[] sz) {

        // 一共需要 times 次排序
        int times = sz.length / 2;

        for (int t = 0; t < times; t++) {
            sortOrder(sz, t);
            sortReverseOrder(sz, t);
        }
    }

    public static void sortOrder(int[] sz, int t) {
        for (int j = t; j < sz.length - t - 1; j++) {
            if (sz[j] > sz[j + 1]) {
                int value = sz[j];
                sz[j] = sz[j+1];
                sz[j+1] = value;
            }
        }
    }
    public static void sortReverseOrder(int[] sz, int t) {
        for (int j = sz.length - t - 1; j > t; j--) {
            if (sz[j-1] > sz[j]) {
                int value = sz[j];
                sz[j] = sz[j-1];
                sz[j-1] = value;
            }
        }
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
