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
import com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树.BinarySearchTree;
import com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树.BinaryTree;
import com.xauv.algorithm.题目.数据结构.GraphTreeFactory;
import com.xauv.algorithm.题目.数据结构.TreeFactory;
import com.xauv.algorithm.题目.数据结构.TreeNode;

import java.util.Stack;

/**
 * @Date 2022/11/20 13:21
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.二叉树
 * @Desc
 *
 *   ┌──1──┐
 *   │     │
 * ┌─2─┐ ┌─3
 * │   │ │
 * 4   5 6
 *
 * 1 和 3 的最近公共祖先为 1
 *
 * 2 和 3 的最近公共祖先为 1
 *
 * 2 和 5 的最近公共祖先为 2
 *
 * 4 和 6 的最近公共祖先为 1
 *
 */
public class 最近的公共祖先 {

    /**
     *
     * 去以 root 为根节点的二叉树中查找 p，q 的最近公共祖先
     *
     *   ┌──1──┐
     *   │     │
     * ┌─2─┐ ┌─3─┐
     * │   │ │   │
     * 4   5 6   7
     *
     * 1.如果 p q 同时存在于这棵二叉树中，就能成功返回他们的最近公共祖先
     *
     * 2.如果 p q 有一方或者都不在这棵二叉树，则返回 null
     *
     * 3.如果只有 p 存在于这棵二叉树，则返回 p
     *
     * 4.如果只有 q 存在于这棵二叉树，则返回 q
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        if (p == q) {
            return p;
        }

        // 去左子树查找 p q 公共祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 去右子树查找 p q 公共祖先
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 说明在左半部分找到了公共祖先
        if (left != null && right == null) {
            return left;
        }
        // 说明在右半部分找到了公共祖先
        if (left == null && right != null) {
            return right;
        }
        // 左右子树都找到了，这样的话，根节点就是公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 最后一种情况是，左子树和右子树都没找到
        return null;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        GraphTreeFactory treeFactory = new GraphTreeFactory();
        treeFactory.init(7);
        BinaryTrees.println(treeFactory);

        System.out.println(lowestCommonAncestor(root, n2, n7));
    }
}
