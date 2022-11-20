package com.xauv.algorithm.刷题.二叉树;

import com.xauv.algorithm.题目.数据结构.TreeFactory;
import com.xauv.algorithm.题目.数据结构.TreeNode;
import java.util.Stack;

/**
 * @author: Bing Juan
 * @date: 2022/11/18 14 54
 * @desc:
 *
 *
 *      3
 *    2   5
 *  1   4
 */
public class 判断是否二叉搜索树 {

    public static boolean isValidBST (TreeNode root) {
        // write code here
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        Integer prev = null;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                TreeNode pop = stack.pop();
                if (prev == null) {
                    prev = pop.code;
                } else {
                    if (prev > pop.code) {
                        return false;
                    }
                    prev = pop.code;
                }
                System.out.println(pop.code);
                node = pop.right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode init = TreeFactory.init(7);
        System.out.println(isValidBST(init));
    }
}
