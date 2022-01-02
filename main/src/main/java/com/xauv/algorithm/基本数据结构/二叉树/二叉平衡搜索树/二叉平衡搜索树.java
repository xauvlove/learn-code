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

/**
 * @Date 2021/12/27 22:23
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树
 * @Desc
 */
public class 二叉平衡搜索树 {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] array = new int[]{89,19,69,99,95,100,11,101,102};

        for (int i : array) {
            tree.add(i);
        }

        BinaryTrees.println(tree);

        tree.remove(69);
        tree.remove(19);
        tree.remove(89);
        BinaryTrees.println(tree);




        //int[] array = new int[]{5,3,8,7,9,10};
        /*Random random = new Random();

        int[] array = new int[20];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }


        for (int i : array) {
            tree.add(i);
        }
        BinaryTrees.println(tree);

        tree.remove(692);*/

       /* BinarySearchTree<Integer> trees = new BinarySearchTree<>();
        for (int i : array) {
            trees.add(i);
        }
        BinaryTrees.println(trees);*/

    }
}
