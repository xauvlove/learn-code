package com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/12/26 21:48
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树
 * @Desc
 */

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 性质：
 *  1.左子树都比父节点小
 *  2.右子树都比父节点大
 *  3.每个节点都是一颗二叉搜索树
 *  4.
 * @param <E>
 */
public abstract class BinaryTree<E extends Comparable<E>> implements BinaryTreeInfo {

    /**
     * 树 size
     */
    private int size;

    /**
     * 根节点
     */
    private Node<E> root;

    public Node<E> getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
    }

    public abstract void add(E element);

    /**
     *
     * @param element
     */
    public abstract void remove(E element);


    public boolean contains(E element) {
        return findNodeByElement(element) != null;
    }

    /**
     * 查找前驱节点
     * @return
     */
    public E getPredecessor(E element) {
        Node<E> node = findNodeByElement(element);
        if (node == null) {
            return null;
        }

        Node<E> left = node.left;
        Node<E> cur = node.left;
        if (left != null) {
            while (true) {
                Node<E> right = cur.right;
                if (right == null) {
                    return cur.element;
                }
                cur = right;
            }
        }
        else {
            while (true) {
                Node<E> parent = node.parent;
                if (parent == null) {
                    return null;
                }
                Node<E> parentParent = parent.parent;
                if (parentParent == null) {
                    return null;
                }
                if (parentParent.right == parent) {
                    return parentParent.element;
                }
                node = parent;
            }
        }
    }

    public E getSuccessor(E element) {
        Node<E> node = findNodeByElement(element);
        if (node == null) {
            return null;
        }
        Node<E> right = node.right;
        // 右孩子不为空
        Node<E> successor = null;
        if (right != null) {
            while (right != null) {
                successor = right;
                right = right.right;
            }
            return successor.element;
        }
        // 右孩子为空，找父节点 祖父节点
        else {
            Node<E> cur = node;
            while (true) {
                Node<E> parent = cur.parent;
                if (parent == null) {
                    return null;
                }
                Node<E> parentParent = parent.parent;
                if (parentParent == null) {
                    return null;
                }
                if (parentParent.left == parent) {
                    return parentParent.element;
                } else {
                    cur = parent;
                }
            }
        }
    }

    public abstract Node<E> findNodeByElement(E element);

    /**
     * 获取树的高度
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 获取某个节点的高度
     *
     * 某个节点的高度 = 它的左孩子节点和右孩子节点中，最大的节点高度 + 1
     * @param node
     * @return
     */
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        Node<E> left = node.left;
        Node<E> right = node.right;
        return Math.max(height(left), height(right)) + 1;
    }

    /**
     * 使用层次遍历求高度
     *
     * height: 每访问完一层，height 就 +1
     * queue: 每访问完一层，这一层的节点的所有子节点已经入队，并且队列里面的元素全都是这一层的子节点
     *  因此，访问完一层（一层的最后一个节点被访问后），queue.size() = 下一层子节点的数量
     *
     * @return
     */
    public int heightLevel() {
        return heightLevel(root);
    }

    private int heightLevel(Node<E> head) {
        if (head == null) {
            return 0;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(head);

        // 树高度
        int height = 0;
        // 存储每一层元素数量
        int levelSize = 1;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 每访问(poll)完这一层的一个节点，这一层节点的总个数 -1
            levelSize = levelSize - 1;
            Node<E> left = node.left;
            if (left != null) {
                queue.offer(left);
            }
            Node<E> right = node.right;
            if (right != null) {
                queue.offer(right);
            }
            // 如果 levelSize == 0 表示这一层已经访问完了 下一次 poll 的时候将会 访问(poll) 下一层的节点了
            if (levelSize == 0) {
                height = height + 1;
                levelSize = queue.size();
            }
        }
        return height;
    }

    /**
     * 判断是否完全二叉树
     * 1.左孩子为空 但右孩子不为空，则不是完全二叉树
     * 2.左孩子不为空，但右孩子为空，那么必须后面所有节点都是叶子节点
     * 3.左孩子为空，右孩子为空，那么必须后面所有节点都是叶子节点
     *
     * 判断时有 4 种情况：
     * 1.左右孩子都不为空，那么按照正常入队
     * 2.左孩子为空，右孩子不为空，那么可以直接判定不是完全二叉树
     * 3.左孩子为空，右孩子为空，那么剩余所有节点必须是叶子节点
     * 4.左孩子不为空，右孩子为空，那么剩余所有节点必须是叶子节点
     * @return
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean allLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            Node<E> left = poll.left;
            Node<E> right = poll.right;

            // 条件 4 要求后续所有节点为叶子节点，但该节点不为叶子节点，直接判定不是完全二叉树
            if (allLeaf && !isLeaf(poll)) {
                return false;
            }

            // 条件 1
            if (left != null && right != null) {
                queue.offer(left);
                queue.offer(right);
            }
            // 条件 2
            else if (left == null && right != null) {
                return false;
            }
            // 条件 3，后面的节点必须都是叶子节点
            else if (left != null) {
                queue.offer(left);
            }
            // 条件 4，后面的节点必须都是叶子节点
            else {
                allLeaf = true;
            }
        }
        return true;
    }

    /**
     * 更简单的写法
     * @return
     */
    public boolean isCompleteV2() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean allLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            Node<E> left = poll.left;
            Node<E> right = poll.right;

            if (allLeaf && !isLeaf(poll)) {
                return false;
            }

            if (left != null) {
                queue.offer(left);
            }
            // 条件 2
            // 左为空，右不为空，非完全
            else if (right != null){
                return false;
            }

            if (right != null) {
                queue.offer(right);
            }
            // 条件 3 4
            // 右为空，左边可能空，可能不空
            else {
                // 这两种情况都必须得保证后面的节点都是叶子节点
                allLeaf = true;
            }
        }
        return true;
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element.toString();
    }

    private boolean isLeaf(Node<E> node) {
        return node.left == null && node.right == null;
    }

    private boolean degree1(Node<E> node) {
        return (node.left != null && node.right == null) || (node.left == null && node.right != null);
    }

    private boolean degree2(Node<E> node) {
        return hasTwoKids(node);
    }

    private boolean hasTwoKids(Node<E> node) {
        return node.left != null && node.right != null;
    }

    public static class Node<E> {

        /**
         * 本节点值
         */
        E element;

        /**
         * 左孩子
         */
        Node<E> left;

        /**
         * 右孩子
         */
        Node<E> right;

        /**
         * 父节点
         */
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
