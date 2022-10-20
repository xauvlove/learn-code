package com.xauv.algorithm.基本数据结构.二叉堆;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;

/**
 * @Date 2022/10/18 21:45
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉堆
 * @Desc
 */
public class 二叉堆 {

    public static void main(String[] args) {

        BinaryHeap<Integer> heap = new BinaryHeap<>(null);
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);

        BinaryTrees.println(heap);

        heap.remove();
        BinaryTrees.println(heap);

        heap.replace(1);
        BinaryTrees.println(heap);


    }
}
