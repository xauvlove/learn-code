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
 * @Date 2022/11/20 15:08
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.二叉树
 * @Desc
 *
 * 在一棵树【并非一定是二叉搜索树】中找到节点最多的 bst 子树
 *
 *
 *   ┌──4──┐
 *   │     │
 * ┌─2─┐ ┌─7─┐
 * │   │ │   │
 * 1   3 5   6
 *
 * 最大 bst 为
 *
 * ┌─2─┐
 * │   │
 * 1   3
 *
 *
 * 其中这个不是二叉搜索树，因此不是最大的二叉搜索子树
 * ┌─7─┐
 * │   │
 * 5   6
 *
 *
 */
public class 最大二叉搜索树 {

    /**
     *
     * 思路 1 ： 自顶向下
     *
     * 如果以 root 为根节点的二叉树 S 是 BST，那么返回 S 节点数量
     * 否则，就查找以 root.left， root.right 为根节点的二叉树【如果是BST】的节点最大数量
     *
     * 类似于前序遍历
     *
     * @param root
     * @return
     */
    public static int largestBstSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isBst(root)) {
            return nodeCount(root);
        }
        return Math.max(largestBstSubtree(root.left), largestBstSubtree(root.right));
    }

    /**
     * 自底向上
     *
     *    ┌────36───┐
     *    │         │
     * ┌─10─┐     ┌─43─┐
     * │    │     │    │
     * 1  ┌─14─┐ 42  ┌─41
     *    │    │     │
     *   12    30   96
     *
     *
     * 整棵树不是二叉搜索树，41 这个节点是不对的
     *
     * 这棵子树是二叉搜索树
     * ┌─10─┐
     * │    │
     * 1  ┌─14─┐
     *    │    │
     *   12    30
     *
     * 自底向上的方法为：
     *
     * 比如判断 96，96 是二叉搜索树
     * 41，不是二叉搜索树，那么以 41 为子节点的父节点，整个都不是二叉搜索树，排除了一大片
     *
     *
     * 后序遍历
     *
     * @param root
     * @return
     */
    public static int largestBstSubtree2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return -1;
    }

    private static int nodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return nodeCount(root.left) + nodeCount(root.right) + 1;
    }

    private static boolean isBst(TreeNode root) {
        return isBst(root, root.left, root.right);
    }

    private static boolean isBst(TreeNode root, TreeNode left, TreeNode right) {
        if (root == null) {
            return true;
        }
        if (left != null && left.code > root.code) {
            return false;
        }
        if (right != null && right.code < root.code) {
            return false;
        }
        if (left != null && right != null) {
            return isBst(left, left.left, left.right) && isBst(right, right.left, right.right);
        }
        return true;
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

        System.out.println(largestBstSubtree(root));
    }
}
