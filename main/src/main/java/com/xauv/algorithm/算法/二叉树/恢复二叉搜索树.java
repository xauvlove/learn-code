package com.xauv.algorithm.算法.二叉树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;
import com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树.AVLTree;
import com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树.BinarySearchTree;
import com.xauv.algorithm.题目.数据结构.GraphTreeFactory;
import com.xauv.algorithm.题目.数据结构.TreeFactory;
import com.xauv.algorithm.题目.数据结构.TreeNode;

/**
 * @Date 2022/11/20 14:05
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.二叉树
 * @Desc
 *
 * 二叉搜索树中的两个节点被错误地交换了
 *
 * 在不改变结构地情况下，恢复二叉搜索树
 *
 *
 * 正确的树：
 *     ┌────28───┐
 *     │         │
 *  ┌─18─┐       37─┐
 *  │    │          │
 * 11─┐  22       ┌─44─┐
 *    │           │    │
 *    17         42    62
 *
 *
 *
 *
 *
 * 错误的树 1：【28 和 22 被交换了】
 *     ┌────22───┐
 *     │         │
 *  ┌─18─┐       37─┐
 *  │    │          │
 * 11─┐  28       ┌─44─┐
 *    │           │    │
 *    17         42    62
 *
 *
 * 错误的树 2：【18 和 42 被交换了】
 *     ┌────28───┐
 *     │         │
 *  ┌─42─┐       37─┐
 *  │    │          │
 * 11─┐  18       ┌─44─┐
 *    │           │    │
 *    17         42    62
 *
 */
public class 恢复二叉搜索树 {

    /**
     * 找到被交换的节点，交换过来即可
     *
     * 二叉搜索树，中序遍历结果是升序的
     *
     * 情况 1：交换的两个节点中序遍历结果是挨着的
     * 正确的树中序遍历结果：11 17 18 22 28 37 42 44 62
     * 错误的树中序遍历结果：11 17 18 28 22 37 42 44 62
     * 只找到一个逆序对 【28 22】
     *
     *
     * 情况 2：交换的两个节点中序遍历结果不是挨着的
     * 正确的树中序遍历结果：11 17 18 22 28 37 42 44 62
     * 错误的树中序遍历结果：11 17 42 28 22 37 18 44 62
     * 找到两个个逆序对【42 28】 【37 18】
     *
     * 不可能找到比两个更多的逆序对
     *
     * 通过这两种情况
     * 情况 1，【28 22】
     * 情况 2，【42 28】，【37 18】
     *
     * 我们可以发现，不管是哪种情况，都包含两个错误节点
     * case1 两个错误节点是：28 22
     * case2 两个错误节点是：42 18
     * 总结起来，
     * 第一个错误节点是：第一个逆序对中较大节点
     * 第二个错误节点是：第二个【最后一个】逆序对中较小节点
     *
     *
     *
     * @param root
     */
    public static void recoverBst(TreeNode root) {
        if (root == null) {
            return;
        }
        // 找到错误节点
        findWrongNodes(root);

        // 交换两个节点值
        int tmp = first.code;
        first.code = second.code;
        second.code = tmp;
    }


    /*------------------------------中序遍历找到错误节点----------------------------------*/
    // 上一次遍历过的节点
    private static TreeNode prev = null;

    // 第一个错误节点
    private static TreeNode first = null;

    // 第二个错误节点
    private static TreeNode second = null;

    // 使用中序遍历寻找逆序对和错误节点
    private static void findWrongNodes(TreeNode root) {
        if (root == null) {
            return;
        }

        findWrongNodes(root.left);
        if (prev != null && prev.code > root.code) {
            // 出现了逆序对

            // 第二个错误节点是最后一个逆序对较小的那个节点
            second = root;

            // 第一个错误节点是第一个逆序对较大的那个节点
            if (first != null) {
                return;
            }
            first = prev;
        }
        prev = root;
        findWrongNodes(root.right);
    }

    /**
     * morris 中序遍历
     */
    public static void morris(TreeNode root) {

    }

    public static void main(String[] args) {

        // 模拟搜索二叉树
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);
        avlTree.add(5);
        avlTree.add(6);
        avlTree.add(7);

        BinaryTrees.println(avlTree);

        // 创建二叉搜索树
        TreeNode root = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;


        // 正确树中序遍历
        TreeFactory.inorder(root);

        // 错误树树中序遍历
        n2.code = 7;
        n7.code = 2;
        TreeFactory.inorder(root);

        // 修正树后的中序遍历
        recoverBst(root);
        TreeFactory.inorder(root);
    }
}
