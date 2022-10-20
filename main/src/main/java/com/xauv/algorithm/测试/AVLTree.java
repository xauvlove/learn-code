package com.xauv.algorithm.测试;

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;

import java.util.Comparator;

/**
 * @author: Bing Juan
 * @date: 2022/10/19 15 51
 * @desc:
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public void afterAdd(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        while (avlNode != null) {
            if (avlNode.isBalance()) {
                avlNode.updateHeight();
                avlNode = (AVLNode<E>) avlNode.parent;
            } else {
                rotate(avlNode);
            }
        }
    }

    public void afterRemove(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        while (avlNode != null) {
            if (avlNode.isBalance()) {
                avlNode.updateHeight();
            } else {
                rotate(avlNode);
            }
            avlNode = (AVLNode<E>) avlNode.parent;
        }
    }

    private void rotate(AVLNode<E> grand) {
        AVLNode<E> parent = grand.tallerChild();
        AVLNode<E> node = parent.tallerChild();

        if (parent.isLeft()) {
            // LL
            if (node.isLeft()) {
                rotateRight(grand);
            } else {
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            // RL
            if (node.isLeft()) {
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                rotateLeft(grand);
            }
        }
        node.updateHeight();
        parent.updateHeight();
        grand.updateHeight();
    }

    protected AVLNode<E> createNode(E e, Node<E> parent) {
        return new AVLNode<E>(e, parent);
    }

    public static void main(String[] args) {

        int[] array = new int[]{89,19,69,93,91,90,92,99,100,11,101,104};

        AVLTree<Integer> tree = new AVLTree<>(null);
        for (int i : array) {
            tree.add(i);
        }

        BinaryTrees.print(tree);

        tree.remove(99);
        tree.remove(100);
        tree.remove(104);
        BinaryTrees.print(tree);

    }
}
