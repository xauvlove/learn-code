package com.xauv.algorithm.题目.数据结构;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Random;

/**
 * @Date 2021/12/24 21:08
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.数据结构
 * @Desc
 */
public class ArrayFactory {

    public static int[] initRandomArray(int length) {
        int[] sz = new int[length];
        Random random = new Random(20);
        for (int i = 0; i < length; i++) {
            sz[i] = random.nextInt(length * 10);
        }
        return sz;
    }

    public static int[] initSortedArray(int length) {
        int[] sz = new int[length];
        for (int i = 0; i < length; i++) {
            sz[i] = i+1;
        }
        return sz;
    }
}
