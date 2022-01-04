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
 *
 *
 * 删除一个节点，失衡的情况
 *  首先失衡的是父节点
 *  父节点调整后，祖父节点可能会失衡 ······ 需要不断向上调整，让整棵树平衡
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
                rotateBalance(node);
                break;
            }
        }
    }

    /**
     * 删除后，只可能导致父节点失衡，祖父节点等不会失衡
     * 但是删除完调整平衡后，祖父节点可能再次失衡，因此需要不断地向上寻找 父节点，祖父节点 ······· 查看是否失衡
     * 因此，删除节点后，可能需要 log(N)次调整平衡
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            // 如果是平衡的，则更新高度
            if (isBalanced(node)) {
                updateHeight(node);
            }
            // 如果不平衡，则恢复平衡
            else {
                rotateBalance(node);
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
        afterRotate(grand, parent, node);
    }

    /**
     * AVL 统一旋转操作
     * @param grand
     */
    private void rotateBalance(Node<E> grand) {
        AVLNode<E> parent = ((AVLNode<E>)((AVLNode<E>) grand).tallerChild());
        AVLNode<E> node = (AVLNode<E>) ((parent.tallerChild()));

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            if (node.isLeftChild()) {
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
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