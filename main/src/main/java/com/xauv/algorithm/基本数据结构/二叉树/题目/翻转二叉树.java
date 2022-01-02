package com.xauv.algorithm.基本数据结构.二叉树.题目;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.TreeFactory;
import com.xauv.algorithm.题目.数据结构.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2021/12/26 20:18
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树.题目
 * @Desc
 *
 * 二叉树翻转，只要遍历就行
 * 遍历的过程中，将左右交换
 * 遍历完所有节点，那他们的左右孩子就已经全部完成了交换
 */
public class 翻转二叉树 {

    public static void main(String[] args) {
        TreeNode init = TreeFactory.init(7);
    }

    /**
     * 利用前序遍历
     * 每遍历到一个节点，就把它的左右孩子位置互换
     * @param head
     * @return
     */
    public TreeNode invertTreeByBefore(TreeNode head) {
        if (head == null) {
            return null;
        }
        // 交换左右孩子
        TreeNode left = head.getLeft();
        TreeNode right = head.getRight();
        head.setLeft(right);
        head.setRight(left);
        invertTreeByBefore(left);
        invertTreeByBefore(right);
        return head;
    }

    /**
     * 利用中序遍历
     * 每遍历到一个节点，就把它的左右孩子位置互换
     * @param head
     * @return
     */
    public TreeNode invertTreeByMiddle(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNode left = head.getLeft();
        TreeNode right = head.getRight();
        // 这里先遍历 left
        invertTreeByMiddle(left);
        // 交换左右孩子
        head.setLeft(right);
        head.setRight(left);
        // 这里遍历 left，因为 left 和 right 已经交换了
        invertTreeByMiddle(left);
        return head;
    }

    /**
     * 利用层次遍历
     * 每遍历到一个节点，就把它的左右孩子位置互换
     * @param head
     * @return
     */
    public TreeNode invertTreeByLevel(TreeNode head) {
        if (head == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode left = poll.getLeft();
            TreeNode right = poll.getRight();
            // 交换左右孩子
            head.setLeft(right);
            head.setRight(left);

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }
        return head;
    }
}
