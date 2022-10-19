package com.xauv.algorithm.基本数据结构.二叉树.题目;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉堆.BinaryHeap;
import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;

import java.util.Comparator;

/**
 * @Date 2022/10/19 21:01
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树.题目
 * @Desc
 *
 * 使用小顶堆
 *
 * 先将 K 个整数存到堆里，堆顶是最小的，继续循环数列，如果发现数比堆顶还大，就替换，然后进行堆调整，还原成小顶堆
 *
 * 比如数列  83 35 71 66 43 67 12 36 4 73 99 1 54，求最大的 6 个整数
 *
 * 先将 83 35 71 66 43 67 存放到小顶堆
 */
public class 最大的K个整数 {

    public static void main(String[] args) {

        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        heap.add(83);
        heap.add(35);
        heap.add(71);
        heap.add(66);
        heap.add(43);
        heap.add(67);

        BinaryTrees.println();
    }
}
