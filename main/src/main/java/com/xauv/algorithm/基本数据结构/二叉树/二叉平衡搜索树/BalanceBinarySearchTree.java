package com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树.BinarySearchTree;

/**
 * @Date 2021/12/27 22:23
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树
 * @Desc
 *
 * 二叉平衡搜索树的分类
 *
 * 1.红黑树 （red-black tree, RB-Tree）
 * 2.AVL树
 *
 * AVL 树概念
 *
 * 平衡因子：节点左右高度差
 *  叶子节点平衡因子 = 0
 *  平衡因子可正可负
 *  AVL 树每个节点的平衡因子不大于 1，否则失去平衡
 * 复杂度：log(n)
 */
public class BalanceBinarySearchTree<E extends Comparable<E>> extends BinarySearchTree<E> {


}
