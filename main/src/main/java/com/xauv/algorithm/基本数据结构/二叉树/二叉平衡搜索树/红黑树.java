package com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;

import java.util.Random;

/**
 * @Date 2022/1/3 14:32
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树
 * @Desc
 */
public class 红黑树 {

    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
        int[] array = new int[]{55,38,80,25,46,76,88,17,33,50,72};

        for (int i : array) {
            tree.add(i);
        }


        BinaryTrees.println(tree);
        tree.add(10);

        BinaryTrees.println(tree);


    }
}
