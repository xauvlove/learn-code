package com.xauv.algorithm.基本数据结构;
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
 * @Date 2022/1/2 16:48
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构
 * @Desc
 */
public class RBTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    private static final boolean RED = false;

    private static final boolean BLACK = true;

    /**
     * 1.染色
     * 2.
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {

    }

    @Override
    protected void afterRemove(Node<E> node) {

    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return null;
        }
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return this.color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return this.color(node, BLACK);
    }

    /**
     * 如果 node 为空，表示隐藏节点，隐藏节点是黑色的
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node);
    }

    private boolean isRed(Node<E> node) {
        return !colorOf(node);
    }

    private static class RBNode<E> extends Node<E> {

        /**
         * false: RED
         * true: BLACK
         */
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
