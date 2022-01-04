package com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树.BinarySearchTree;

/**
 * @Date 2021/12/27 22:23
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树
 * @Desc
 *
 * 二叉平衡搜索树的分类
 *
 * 1.红黑树 （red-black tree, RB-Tree）
 * 2.AVL树
 *
 * AVL 树概念
 *
 * 平衡因子：节点左右高度差
 *  叶子节点平衡因子 = 0
 *  平衡因子可正可负
 *  AVL 树每个节点的平衡因子不大于 1，否则失去平衡
 * 复杂度：log(n)
 */
public class BalanceBinarySearchTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    /**
     * 左旋转
     * @param grand
     */
    protected void rotateLeft(Node<E> grand) {

        // 修改指向
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        // 更新 parent
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            // grand 已经是根节点 grand.parent = null
            root = parent;
        }
        grand.parent = parent;
        if (child != null) {
            child.parent = grand;
        }
    }

    /**
     * 右旋转
     * @param grand
     */
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        grand.left = child;
        parent.right = grand;

        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        grand.parent = parent;
        if (child != null) {
            child.parent = grand;
        }
        afterRotate(grand, parent, child);
    }

    /**
     * AVL 统一旋转操作
     * r a b c d e f g 是按照旋转完成后，二叉搜索树的特性从左到右传入的
     *
     * 旋转操作完成后，树是类似于这样的
     *
     *                d
     *        b                f
     *    a       c       e          g
     *
     * @param r 子树根节点，一般都是 grand，首先失衡的那个节点
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     */
    protected void rotate(Node<E> r,
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        d.left = b;
        b.parent = d;
        d.right = f;
        f.parent = d;

        b.left = a;
        if (a != null) {
            a.parent = b;
        }

        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        f.right = g;
        if (g != null) {
            g.parent = f;
        }
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {

    }
}
