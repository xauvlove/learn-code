package com.xauv.algorithm.基本数据结构.栈;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;
import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;
import com.xauv.algorithm.题目.数据结构.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Date 2022/11/13 15:41
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.二叉树遍历
 * @Desc
 *
 *
 * 给定一个数组，还原一棵树
 * 这棵树：
 * 1.根节点是最大的
 * 2.子树的根节点是最大的
 * 3.如果元素在根的右边，那么它是右子树上的元素
 * 4.如果元素在根的左边，那么它是左子树上的元素
 *
 * 给定数组：[3, 2, 1, 6, 0, 5]，数组没有重复元素
 *
 * 最大二叉树为：
 *
 * ┌───6───┐
 * │       │
 * 3─┐   ┌─5
 *   │   │
 *   2─┐ 0
 *     │
 *     1
 *
 * 它并不是一个二叉搜索树，
 * 比如 2 是 3 的右子树，因为相对于数组来说， 2 在 3 的右边
 */
public class 最大二叉树 implements BinaryTreeInfo {

    TreeNode root;

    /**
     * 返回最大二叉树的根节点
     * @param array
     * @return
     */
    public TreeNode maxBinaryTree(int[] array) {
        return findRoot(array, 0, array.length);
    }

    /**
     * 查找指定范围的根节点，左闭右开 [l, r)
     * @param array
     * @param l
     * @param r
     * @return
     */
    private TreeNode findRoot(int[] array, int l, int r) {
        if (l == r) {
            return null;
        }

        int maxIdx = l;
        for (int i = l+1; i < r; i++) {
            if (array[i] > array[maxIdx]) {
                maxIdx = i;
            }
        }
        TreeNode rt = new TreeNode(array[maxIdx]);
        if (root == null) {
            root = rt;
        }
        // 设置左子树
        rt.left = findRoot(array, l, maxIdx);
        // 设置右子树
        rt.right = findRoot(array, maxIdx+1, r);
        return rt;
    }

    /**
     * 求父节点索引【单调栈】
     *
     * 节点父节点 = Math.min(往左边遍历一次找出第一个比他大的元素, 往右边遍历一次找出第一个比他大的元素)
     *
     * [3, 2, 1, 6, 0, 5]
     *
     * ┌───6───┐
     * │       │
     * 3─┐   ┌─5
     *   │   │
     *   2─┐ 0
     *     │
     *     1
     *
     * 比如节点 2，它的父节点就是 3
     * 从 2 往左边遍历，第一个比他大的元素是 3
     * 从 2 往右边遍历，第一个比他大的元素是 6
     *
     *
     * @param array
     * @return
     */
    public int[] parentIndexes(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        Stack<Integer> stack = new Stack<>();
        int[] lis = new int[array.length];
        int[] ris = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            lis[i] = -1;
            ris[i] = -1;
        }
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty()) {
                if (array[i] > array[stack.peek()]) {
                    Integer pop = stack.pop();
                    ris[pop] = i;
                } else {
                    break;
                }
            }
            if (stack.isEmpty()) {
                lis[i] = -1;
            } else {
                lis[i] = stack.peek();
            }
            stack.push(i);
        }
        int[] pis = new int[array.length];
        for (int i = 0; i < pis.length; i++) {
            if (lis[i] == -1 && ris[i] == -1) {
                pis[i] = -1;
                continue;
            }
            if (lis[i] == -1) {
                pis[i] = ris[i];
                continue;
            }
            if (ris[i] == -1) {
                pis[i] = lis[i];
                continue;
            }
            pis[i] = array[lis[i]] > array[ris[i]] ? ris[i] : lis[i];
        }
        return pis;
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((TreeNode)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((TreeNode)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((TreeNode)node).code;
    }

    public static void main(String[] args) {
        int[] array = {3, 2, 1, 6, 0, 5};
        最大二叉树 maxTree = new 最大二叉树();
        TreeNode treeNode = maxTree.maxBinaryTree(array);
        BinaryTrees.println(maxTree);
    }
}
