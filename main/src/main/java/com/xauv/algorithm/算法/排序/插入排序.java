package com.xauv.algorithm.算法.排序;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/20 21:06
 * @Author Administrator
 * @Package com.xauv.algorithm.算法
 * @Desc
 */
public class 插入排序 {

    public static void sort(int[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0) {
                int r = array[cur];
                int l = array[cur - 1];
                if (r < l) {
                    swap(array, r, l);
                    cur = cur - 1;
                }
            }
        }
    }

    private static void swap(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    public static void main(String[] args) {

    }
}
