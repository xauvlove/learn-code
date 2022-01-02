package com.xauv.test.算法.二叉树.测试类;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/


import com.xauv.test.算法.二叉树.实现.AVLTree;
import com.xauv.test.算法.二叉树.实现.BinarySearchTree;
import com.xauv.test.算法.二叉树.utils.BinaryTrees;
import com.xauv.test.算法.二叉树.实现.Tree;

/**
 * @Date 2021/12/26 17:01
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树
 * @Desc
 */
public class 二叉搜索树 {

    public static void main(String[] args) {
        /*BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] array = new int[]{7, 4, 9, 2, 5, 8, 11, 3, 2, 1, 66, 77, 88, 22, 13};

        for (int i : array) {
            tree.add(i);
        }
        tree.inverse();
        BinaryTrees.println(tree);*/

        AVLTree<Integer> avlTree = new AVLTree<>();

        int[] array = new int[]{7, 4, 9, 2, 5, 8, 11, 3, 2, 1, 66, 77,88,12,13};
        for (int i : array) {
            avlTree.add(i);
        }
        BinaryTrees.println(avlTree);


    }
}