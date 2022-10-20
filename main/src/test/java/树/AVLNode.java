package 树;

/**
 * @author: Bing Juan
 * @date: 2022/10/19 15 44
 * @desc:
 */
public class AVLNode<E> extends Node<E>{

    int height = 1;

    public AVLNode(E e, Node<E> parent) {
        super(e);
        this.parent = parent;
    }

    public boolean isBalance() {
        if (left == null && right == null) {
            return true;
        }
        int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
        int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
        return Math.abs((leftHeight - rightHeight)) <= 1;
    }

    public void updateHeight() {
        AVLNode<E> avlLeft = (AVLNode<E>) left;
        AVLNode<E> avlRight = (AVLNode<E>) right;
        if (avlLeft == null && avlRight == null) {
            height = 1;
        }
        if (avlLeft != null && avlRight == null) {
            height = 1 + avlLeft.height;
        }
        if (avlLeft == null && avlRight != null) {
            height = 1 + avlRight.height;
        }
        if (avlLeft != null && avlRight != null) {
            height = 1 + Math.max(avlLeft.height, avlRight.height);
        }
    }

    public AVLNode<E> tallerChild() {
        AVLNode<E> avlLeft = (AVLNode<E>) left;
        AVLNode<E> avlRight = (AVLNode<E>) right;
        if (avlLeft == null && avlRight == null) {
            return null;
        }
        if (avlLeft != null && avlRight == null) {
            return avlLeft;
        }
        if (avlLeft == null && avlRight != null) {
            return avlRight;
        }
        return avlLeft.height > avlRight.height ? avlLeft : avlRight;
    }

    public boolean isLeftChild() {
        return parent != null && parent.left == this;
    }

    public boolean isRightChild() {
        return parent != null && parent.right == this;
    }
}
