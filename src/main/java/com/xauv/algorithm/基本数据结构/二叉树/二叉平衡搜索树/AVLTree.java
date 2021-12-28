package com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/12/27 22:23
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树.二叉平衡搜索树
 * @Desc
 *
 *
 * AVL 树概念
 *
 * 平衡因子：节点左右高度差
 *  叶子节点平衡因子 = 0
 *  平衡因子可正可负
 *  AVL 树每个节点的平衡因子不大于 1，否则失去平衡
 *
 * 复杂度：log(n)
 *
 * 添加一个节点，失衡的情况
 *  只有 祖父节点和祖父祖父节点 ····· 会失衡，也就是沿着父节点的那条线上，除了父节点其他所有元素都可能失衡
 *  父节点不会失衡
 *  非父系节点不失衡（没有沿着父节点的线上的节点，不会失衡）
 *
 * 旋转情况
 * LL-右旋
 * RR-左旋
 * LR-RR左旋，旋转成LL，然后进行 LL右旋
 * RL-LL右旋，旋转称RR，然后进行 RR左旋
 * 旋转后，更新父子关系，更新节点高度
 *
 * 旋转时，【只需】从下往上找到第一个失衡节点进行旋转
 * 旋转后，父节点平衡，祖父节点也平衡 ······ 往上的所有节点都平衡
 */
public class AVLTree<E extends Comparable<E>> extends BalanceBinarySearchTree<E> {

    /**
     *
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // 看父节点是否平衡，再看父节点的父节点是否平衡，一直往上找到根节点
        // 如果每添加一个元素都去更新所有节点高度，浪费性能
        // 这里巧妙利用检查旋转的过程来更新高度

        // 因为这个方法是：节点刚刚添加到树中，它是个叶子节点，那么 这个节点默认高度就是 1
        // 因此，我们只需要维护 这个新添加节点的所有父节点 祖父节点 ······ 即可
        while ((node = node.parent) != null) {
            // 如果是平衡的，则更新高度
            if (isBalanced(node)) {
                updateHeight(node);
            }
            // 如果不平衡，则恢复平衡
            else {
                // 如果第一个节点恢复平衡 则整棵树平衡
                reBalance(node);
                break;
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private void updateHeight(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        avlNode.updateHeight();
    }

    /**
     *
     * @param grand 高度最低的不平衡节点
     *              一定不是父节点，可能是 祖父节点 也可能是祖父节点的父节点
     */
    private void reBalance(Node<E> grand) {
        AVLNode<E> avlGrand = (AVLNode<E>) grand;
        AVLNode<E> parent = (AVLNode<E>) avlGrand.tallerChild();
        AVLNode<E> node = (AVLNode<E>) parent.tallerChild();

        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                rotateRight(grand);
            }
            // LR
            else {
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            // RL
            if (node.isLeftChild()) {
                rotateRight(parent);
                rotateLeft(grand);
            }
            // RR .
            else {
                rotateLeft(grand);
            }
        }
    }

    /**
     * 左旋转
     * @param node
     */
    private void rotateLeft(Node<E> node) {

        Node<E> rightChild = node.right;
        Node<E> childLeft = rightChild.left;

        // 断开链
        node.right = null;
        rightChild.parent = null;
        rightChild.left = null;
        childLeft.parent = null;

        // 连接 & 维护父子关系
        rightChild.parent = node.parent;
        rightChild.left = node;

        childLeft.parent = node;

        node.right = childLeft;
        node.parent = rightChild;
    }

    /**
     * 右旋转
     * @param node
     */
    private void rotateRight(Node<E> node) {
        // 获取必要数据 左孩子 和 左孩子的右孩子，一个比 node 小 一个比 node 大
        Node<E> leftChild = node.left;
        Node<E> childRight = leftChild.right;

        // 将左孩子的父节点置为空 断链
        leftChild.parent = null;
        // 将需要旋转的左节点置为空 断链
        node.left = null;
        // 将左孩子的右孩子的父节点置为空
        childRight.parent = null;

        // 将左孩子的父节点置为父节点的父节点
        leftChild.parent = node.parent;
        // 将左孩子的右节点的父节点
        node.left = childRight;

        // 维护一下父子关系
        node.parent = leftChild;
        childRight.parent = node;
    }

    /**
     * 平衡因子绝对值小于等于 1
     * 表示平衡
     * @param node
     * @return
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E> {

        /**
         * 高度
         *
         * 默认 = 1
         */
        public int height = 1;

        /**
         * 父类有参构造方法
         * 子类必须实现
         * @param element
         * @param parent
         */
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 平衡因子
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
            int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
            int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 返回左右子树较高的那个节点
         * 如果一样高 返回任意一个即可（这里代码写死了返回右节点）
         * @return
         */
        public Node<E> tallerChild() {
            return balanceFactor() > 0 ? left : right;
        }
    }
}