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

import java.util.Stack;

import static com.xauv.algorithm.题目.数据结构.TreeFactory.init;

/**
 * @Date 2022/10/20 19:07
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构
 * @Desc
 */
public class 二叉树非递归遍历 {

    public static void main(String[] args) {
        TreeNode root = init(7);

        System.out.println("前序：");
        preorder(root);
        System.out.println("中序：");
        inorder(root);
        System.out.println("后续：");
        postorder(root);
    }

   public static void preorder(TreeNode root) {

       TreeNode node = root;
       Stack<TreeNode> stack = new Stack<>();
       while (true) {
           // 三个分支：node 空判断    栈空判断
           if (node != null) {
               System.out.println(node.getCode());
               // 先处理右孩子
               // 遇到右孩子，右孩子都是最后才输出的，因此先入栈，等左孩子全部遍历完，再出栈遍历
               TreeNode right = node.getRight();
               if (right != null) {
                   stack.push(right);
               }
               // 如果左孩子不为空，则交给循环
               node = node.getLeft();
           } else if (stack.isEmpty()) {
               // 来到这说明：node 为空【已经不能再往左走了】，栈也为空，遍历结束
               break;
           } else {
               // 来到这里表示 node 为空【已经不能再往左走了】，栈不为空，弹栈，把右节点弹出，继续走前序流程
               node = stack.pop();
           }
       }
   }

   public static void inorder(TreeNode root) {
       TreeNode node = root;
       Stack<TreeNode> stack = new Stack<>();
       while (true) {
           if (node != null) {
               // node 不为空，说明可以一直往左走
               stack.push(node);
               node = node.getLeft();
           } else if (stack.isEmpty()) {
               // 栈空【已经不能再往左走了】，遍历结束
               break;
           } else {
               // 往左走到底了【已经不能再往左走了】，开始看右边，弹栈
               TreeNode pop = stack.pop();
               System.out.println(pop.getCode());
               // 弹出栈后，需要按照中序逻辑处理弹栈节点（从这个节点出发，往左走）
               node = pop.getRight();
           }
       }
   }

    /**
     *     ┌─100─┐
     *     │     │
     *  ┌─95─┐  101─┐
     *  │    │      │
     * 11    99    102
     *
     * 思路：
     * 使用栈，由于父节点后出，右孩子其次，左孩子先出
     * 因此：压栈顺序为：父亲  右孩子  左孩子
     *
     * 先将 100 压栈，将 100 的右孩子 101 压栈，将 100 的左孩子 95 压栈
     *
     * 左右孩子都入栈完成之后，看一下栈顶为 95，继续：
     *
     * 将 95 的右孩子 99 压栈，将 95 的左孩子 11 压栈
     *
     * 看一下栈顶为 11，没有子节点，则开始弹栈 弹出 11
     *
     * 看栈顶为 99，没有子节点，继续弹栈
     *
     * 弹出 99，没有子节点，继续弹栈
     *
     * 发现栈顶为 95，右子节点，但 95 的孩子已经遍历过了，不能继续压栈，则 95 要继续弹栈，弹出 95
     *
     * 看栈顶为 101，发现存在右孩子 102，压栈
     *
     * 弹出 102
     *
     * 最后弹出 100
     *
     */
    public static void postorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode prev = null;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.peek();
            TreeNode left = node.getLeft();
            TreeNode right = node.getRight();
            // 1.如果栈顶是叶子节点，没有左右孩子，就不要再压栈了，需要弹栈
            // 2.如果栈顶不是叶子节点，但是他的左右孩子已经遍历过了，他的左右孩子不要再压栈，需要弹栈
            if ((left == null && right == null)
                    || (prev != null && (node.getRight() == prev || node.getLeft() == prev))) {
                TreeNode pop = stack.pop();
                System.out.println(pop.getCode());
                prev = pop;
            } else {
                if (right != null) {
                    stack.push(right);
                }
                if (left != null) {
                    stack.push(left);
                }
            }
        }
    }
}
