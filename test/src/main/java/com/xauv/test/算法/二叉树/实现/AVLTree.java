package com.xauv.test.算法.二叉树.实现;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/1/2 14:14
 * @Author Administrator
 * @Package com.xauv.test.算法.二叉树.实现
 * @Desc
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    @Override
    protected void afterAdd(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        while (avlNode != null) {
            if (avlNode.isBalance()) {
                avlNode.updateHeight();
                avlNode = (AVLNode<E>) avlNode.parent;
            } else {
                rotateBalance(avlNode);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        while (avlNode != null) {
            if (avlNode.isBalance()) {
                avlNode.updateHeight();
                avlNode = (AVLNode<E>) avlNode.parent;
            } else {
                rotateBalance(avlNode);
                avlNode = (AVLNode<E>) avlNode.parent;
            }
        }
    }

    private void rotateBalance(AVLNode<E> grand) {
        AVLNode<E> parent = grand.getTallerChild();
        if (parent == null) {
            throw new RuntimeException("AVL树恢复平衡，发现 parent 为空");
        }
        AVLNode<E> child = parent.getTallerChild();
        if (child == null) {
            throw new RuntimeException("AVL树恢复平衡，发现 child 为空");
        }

        if (parent.isLeftChild()) {
            if (child.isLeftChild()) {
                rotateRight(grand);
            } else {
                rotateLeft(grand);
                rotateRight(grand);
            }
        } else {
            if (child.isLeftChild()) {
                rotateRight(grand);
                rotateLeft(grand);
            } else {
                rotateLeft(grand);
            }
        }
    }

    private void pointBalance(AVLNode<E> grand) {
        AVLNode<E> parent = grand.getTallerChild();
        if (parent == null) {
            throw new RuntimeException("AVL树恢复平衡，发现 parent 为空");
        }
        AVLNode<E> child = parent.getTallerChild();
        if (child == null) {
            throw new RuntimeException("AVL树恢复平衡，发现 child 为空");
        }

        if (parent.isLeftChild()) {
            if (child.isLeftChild()) {
                rotate(grand, child.left, child, child.right, parent, parent.right, grand, grand.right);
            } else {
                rotate(grand, parent.left, parent, child.left, child, child.right, grand, grand.right);
            }
        } else {
            if (child.isLeftChild()) {
                rotate(grand, grand.left, grand, child.left, child, child.right, parent, parent.right);
            } else {
                rotate(grand, grand.left, grand, parent.left, parent, child.left, child, child.right);
            }
        }
    }

    private void rotateLeft(AVLNode<E> grand) {
        AVLNode<E> parent = (AVLNode<E>) grand.right;
        AVLNode<E> child = (AVLNode<E>) parent.left;

        parent.parent = grand.parent;

        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        grand.right = child;
        grand.parent = parent;
        parent.left = grand;
        if (child != null) {
            child.parent = grand;
        }
        grand.updateHeight();
        parent.updateHeight();
    }

    private void rotateRight(AVLNode<E> grand) {
        AVLNode<E> parent = (AVLNode<E>) grand.left;
        AVLNode<E> child = (AVLNode<E>) parent.right;

        parent.parent = grand.parent;

        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        parent.right = grand;
        grand.left = child;
        grand.parent = parent;
        if (child != null) {
            child.parent = grand;
        }
        grand.updateHeight();
        parent.updateHeight();
    }

    private void rotate(Node<E> r,
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

        ((AVLNode<E>) b).updateHeight();

        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        ((AVLNode<E>) f).updateHeight();

        ((AVLNode<E>) d).updateHeight();
    }

    protected Node<E> createNode(E e) {
        return new AVLNode<>(e);
    }
}