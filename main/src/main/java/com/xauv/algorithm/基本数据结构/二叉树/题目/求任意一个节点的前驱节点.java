package com.xauv.algorithm.基本数据结构.二叉树.题目;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.DupTreeNode;

/**
 * @Date 2021/12/26 20:50
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树.题目
 * @Desc
 *
 * 比如树
 *        1
 *    2        3
 * 4     5  6     7
 *
 * 从中序遍历结果看：4 2 5 1 6 3 7
 *
 * 则 1 的前驱为：5，
 * 中序遍历结果中，1 的前面一个元素是 5
 *
 * //////////////////////////////////////////
 *
 * --利用二叉搜索树来考虑，比如树：
 *         4
 *    2          6
 * 1     3    5      7
 *
 * 4 的前驱是 3，也就是说，
 * 对于二叉搜索树，某个节点的前驱就是它的左子树中最大的那个节点
 * 同时，也是比这个节点小的所有节点里面，最大的节点
 *
 *
 *
 * //////////////////////////////////////////
 * 对于二叉树(任意树，多叉树)来说 也是左子树中最右边的节点
 * 只要找到左子树的根节点开始，一直往右找，找到最右节点就是前驱节点
 *
 * 搜索过程中也分两种情况
 * 1.要查找的节点的左子树不为空
 *  从左子树的根节点一直往右找即可
 * 2.要查找的节点的左子树为空
 *  对于二叉搜索树：一直向上搜索父节点，直到找到第一个比自己小的节点就是前驱（结合二叉搜索树容易理解，因为父节点可能会比当前节点小 -> 当它为右孩子，它的父节点肯定比它小）
 *  对于普通二叉树：一直向上搜索父节点和祖父节点，直到找到第一个祖父节点：父节点对于祖父节点来说是右子树上的节点
 *
 *  比如树（二叉搜索树）
 *
 *              8
 *       4                 13
 *   2       6         10
 *                  9      12
 *                      11
 *
 *  比如寻找 9 的前驱，前驱是 8
 *  9 没有右子树，
 *  先找父节点 10，祖父节点 13，发现 10 是 13 的左子树，继续网上找
 *  找到父节点 13 祖父节点 8，发现 13 是 8 的右子树，则 祖父节点 8 就是 9 的前驱
 */
public class 求任意一个节点的前驱节点 {

    public static void main(String[] args) {

        DupTreeNode n8 = new DupTreeNode(8);
        DupTreeNode n4 = new DupTreeNode(4);
        DupTreeNode n13 = new DupTreeNode(13);
        DupTreeNode n2 = new DupTreeNode(2);
        DupTreeNode n6 = new DupTreeNode(6);
        DupTreeNode n10 = new DupTreeNode(10);
        DupTreeNode n9 = new DupTreeNode(9);
        DupTreeNode n12 = new DupTreeNode(12);
        DupTreeNode n11 = new DupTreeNode(11);

        n8.setLeft(n4);
        n4.setParent(n8);
        n8.setRight(n13);
        n13.setParent(n8);
        n4.setLeft(n2);
        n2.setParent(n4);
        n4.setRight(n6);
        n6.setParent(n4);
        n13.setLeft(n10);
        n10.setParent(n13);
        n10.setLeft(n9);
        n9.setParent(n10);
        n10.setRight(n12);
        n12.setParent(n10);
        n12.setLeft(n11);
        n11.setParent(n12);

        System.out.print("中序:");
        middle(n8);
        System.out.println();
        DupTreeNode predecessor = getPredecessor(n9);
        System.out.println("前驱:" + predecessor.getCode());
    }

    public static void middle(DupTreeNode dupTreeNode) {
        DupTreeNode left = dupTreeNode.getLeft();
        if (left != null) {
            middle(left);
        }
        System.out.print(dupTreeNode.getCode() + " ");
        DupTreeNode right = dupTreeNode.getRight();
        if (right != null) {
            middle(right);
        }
    }

    public static DupTreeNode getPredecessor(DupTreeNode node) {

        DupTreeNode left = node.getLeft();
        // 左子树不为空，一直往右找
        if (left != null) {
            node = left;
            while (true) {
                DupTreeNode right = node.getRight();
                if (right != null) {
                    node = right;
                } else {
                    return node;
                }
            }
        }
        // 左子树为空，从 node 开始，一直往上找，直到祖父节点的右孩子==父节点，祖父节点即前驱
        else {
            while (true) {
                DupTreeNode parent = node.getParent();
                if (parent == null) {
                    return null;
                }
                DupTreeNode parentParent = parent.getParent();
                if (parentParent == null) {
                    return null;
                }
                if (parentParent.getRight() == parent) {
                    return parentParent;
                }
                node = parent;
            }
        }
    }
}
