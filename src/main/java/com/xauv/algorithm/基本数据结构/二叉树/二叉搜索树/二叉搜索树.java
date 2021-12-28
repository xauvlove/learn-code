package com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树;
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
 * @Date 2021/12/26 17:01
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树
 * @Desc
 */
public class 二叉搜索树 {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] array = new int[]{7, 4, 9, 2, 5, 8, 11, 3, 2, 1, 66, 77, 88, 22, 13};
        for (int i : array) {
            tree.add(i);
        }
        BinaryTrees.println(tree);
    }
}