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

import java.util.*;

/**
 * @Date 2021/11/12 18:34
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 */
public class 前序中序后序非递归遍历二叉树 {

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
    }

    public static void before(TreeNode root) {

        TreeNode curNode = root;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.empty() || curNode != null) {

            while (curNode != null) {
                System.out.print(curNode.getCode() + " ");
                stack.push(curNode);
                curNode = curNode.getLeft();
            }

            if (!stack.empty()) {
                curNode = stack.pop();
                curNode = curNode.getRight();
            }
        }
    }

    public static void middle(TreeNode root) {
        TreeNode curNode = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || curNode != null) {
            // 如果左孩子不为空，一直压栈
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.getLeft();
            }
            if (!stack.empty()) {
                // 当没有左孩子的时候，需要弹栈，并输出
                curNode = stack.pop();
                System.out.print(curNode.getCode() + " ");
                curNode = curNode.getRight();
            }
        }
    }

    public static void after(TreeNode root) {

       Stack<TreeNode> stack = new Stack<>();
       TreeNode curNode = root;
       Set<TreeNode> visited = new HashSet<>();
       while (!stack.empty() || curNode != null) {
           while (curNode != null) {
               stack.push(curNode);
               curNode = curNode.getLeft();
           }
           if (!stack.empty()) {
               curNode = stack.peek();
               TreeNode right = curNode.getRight();
               if (right != null && !visited.contains(curNode)) {
                   visited.add(curNode);
                   curNode = right;
                   stack.push(curNode);
                   curNode = curNode.getLeft();
               } else {
                   stack.pop();
                   System.out.print(curNode.getCode() + " ");
                   curNode = null;
               }
           }
       }
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
