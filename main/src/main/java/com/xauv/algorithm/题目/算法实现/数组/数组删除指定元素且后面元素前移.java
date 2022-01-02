package com.xauv.algorithm.题目.算法实现.数组;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.ArrayFactory;
import com.xauv.algorithm.题目.数据结构.ArrayUtil;

/**
 * @Date 2021/12/24 21:08
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现
 * @Desc
 */
public class 数组删除指定元素且后面元素前移 {

    public static void main(String[] args) {

        int[] ints = ArrayFactory.initSortedArray(10);
        ArrayUtil.print(ints);

        deleteElementByIdx(ints, 3);
        ArrayUtil.print(ints);
    }

    public static void deleteElementByIdx(int[] array, int index) {

        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        array[index] = 0;

        for (int i=index+1; i<array.length; i++) {
            array[i-1] = array[i];
        }

        array[array.length - 1] = 0;
    }
}
