package com.xauv.algorithm.题目.算法实现.二叉树遍历;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/11/12 18:34
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 */
public class 前序中序后序递归遍历二叉树 {

    public static void main(String[] args) {
        TreeNode root = init(7);

        System.out.print("前序：");
        before(root);
        System.out.println();
        System.out.print("中序：");
        middle(root);
        System.out.println();
        System.out.print("后序：");
        after(root);
        System.out.println();
    }

    public static void before(TreeNode node) {

        System.out.print(node.getCode() + " ");
        TreeNode left = node.getLeft();
        if (left != null) {
            before(left);
        }
        TreeNode right = node.getRight();
        if (right != null) {
            before(right);
        }
    }

    public static void middle(TreeNode node) {

        TreeNode left = node.getLeft();
        if (left != null) {
            middle(left);
        }
        System.out.print(node.getCode() + " ");
        TreeNode right = node.getRight();
        if (right != null) {
            middle(right);
        }
    }

    public static void after(TreeNode node) {

        TreeNode left = node.getLeft();
        if (left != null) {
            after(left);
        }
        TreeNode right = node.getRight();
        if (right != null) {
            after(right);
        }
        System.out.print(node.getCode() + " ");
    }

    public static TreeNode init(int nodeCount) {

        List<TreeNode> nodes = new ArrayList<>();

        for (int i = 0; i <nodeCount; i++) {
            TreeNode node = new TreeNode(i+1);
            nodes.add(node);
        }

        for (int i = 1; i < nodes.size(); i++) {
            int parent = i / 2 + 1;
            TreeNode parentNode = nodes.get(parent - 1);
            int leftChild = parent * 2;
            int rightChild = parent * 2 + 1;
            if (leftChild - 1 < nodes.size()) {
                parentNode.setLeft(nodes.get(leftChild - 1));
            }
            if (rightChild - 1 < nodes.size()) {
                parentNode.setRight(nodes.get(rightChild - 1));
            }
        }
        return nodes.get(0);
    }
}
