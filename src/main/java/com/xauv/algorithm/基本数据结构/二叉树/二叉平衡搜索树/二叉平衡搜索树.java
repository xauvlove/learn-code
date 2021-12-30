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
 * @Date 2021/12/27 22:23
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树
 * @Desc
 */
public class 二叉平衡搜索树 {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        //int[] array = new int[]{5,3,8,7,9,10};
        Random random = new Random();

        int[] array = new int[10];


        for (int i : array) {
            tree.add(random.nextInt(100));
        }
        BinaryTrees.println(tree);

    }
}
