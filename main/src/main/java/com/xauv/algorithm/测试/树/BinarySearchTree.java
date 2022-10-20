package com.xauv.algorithm.测试.树;

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;
import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;

import java.util.Comparator;

/**
 * @author: Bing Juan
 * @date: 2022/10/19 14 31
 * @desc: c
 */
@SuppressWarnings("rawtypes")
public class BinarySearchTree<E> implements BinaryTreeInfo {

    int size;

    Node<E> root;

    Comparator<E> comparator;

    public void add(E e) {

        Node<E> newNode = createNode(e, null);
        if (root == null) {
            root = newNode;
            size++;
            afterAdd(newNode);
            return;
        }

        Node<E> node = root;
        Node<E> pNode = node;
        while (node != null) {
            int compare = compare(e, node.e);
            if (compare > 0) {
                pNode = node;
                node = node.right;
            } else if (compare < 0) {
                pNode = node;
                node = node.left;
            } else {
                return;
            }
        }

        int compare = compare(e, pNode.e);
        if (compare > 0) {
            pNode.right = newNode;
            newNode.parent = pNode;
        } else {
            pNode.left = newNode;
            newNode.parent = pNode;
        }
        afterAdd(newNode);
        size++;
    }

    public void remove(E e) {
        Node<E> eNode = find(e);
        if (eNode == null) {
            return;
        }
        int degree = 0;
        if (eNode.left != null) {
            degree++;
        }
        if (eNode.right != null) {
            degree++;
        }
        if (degree == 0) {
            delete0(eNode);
        }
        if (degree == 1) {
            delete1(eNode);
        }
        if (degree == 2) {
            delete2(eNode);
        }
        size--;
        afterRemove(eNode);
    }

    private void delete0(Node<E> node) {
        if (node == root) {
            root = null;
            return;
        }
        if (node.parent.left == node) {
            node.parent.left = null;
        }
        if (node.parent.right == node) {
            node.parent.right = null;
        }
    }

    private void delete1(Node<E> node) {
        if (node == root) {
            if (node.left != null) {
                root = node.left;
                node.left = null;
            }
            if (node.right != null) {
                root = node.right;
                node.right = null;
            }
            return;
        }

        Node<E> child = node.left != null ? node.left : node.right;

        Node<E> parent = node.parent;
        if (parent.left == node) {
            parent.left = child;
        }
        if (parent.right == node) {
            parent.right = child;
        }
    }

    private void delete2(Node<E> node) {
        Node<E> precursor = findPrecursor(node);
        E oe = node.e;
        remove(precursor.e);
        node.e = precursor.e;
    }
    private Node<E> find(E e) {
        Node<E> node = root;
        while (node != null) {
            int compare = compare(e, node.e);
            if (compare > 0) {
                 node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    private Node<E> findPrecursor(Node<E> node) {
        if (node == root) {
            return node.left;
        }
        Node<E> left = node.left;

        Node<E> precursor = null;
        // 左子树不为空
        if (left != null) {
            Node<E> cNode = left;
            while (true) {
                if (cNode.right != null) {
                    cNode = cNode.right;
                } else {
                    break;
                }
            }
            precursor = cNode;
        } else {
            Node<E> cNode = node;
            while (true) {
                Node<E> parent = cNode.parent;
                if (parent == null) {
                    break;
                }
                Node<E> grand = parent.parent;
                if (grand == null) {
                    break;
                }
                if (grand.right == parent) {
                    precursor = grand;
                    break;
                }
                cNode = parent;
            }
        }
        return precursor;
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void afterAdd(Node<E> newNode) {

    }

    public void afterRemove(Node<E> newNode) {

    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        if (grand == root) {
            root = parent;
            parent.parent = null;
            grand.right = parent.left;
            if (parent.left != null) {
                parent.left.parent = grand;
            }
            parent.left = grand;
            grand.parent = parent;
            return;
        }

        Node<E> gParent = grand.parent;
        if (grand.isLeft()) {
            gParent.left = parent;
        } else {
            gParent.right = parent;
        }
        parent.parent = gParent;
        grand.right = parent.left;
        if (parent.left != null) {
            parent.left.parent = grand;
        }
        parent.left = grand;
        grand.parent = parent;
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        if (grand == root) {
            root = parent;
            parent.parent = null;
            grand.left = parent.right;
            if (parent.right != null) {
                parent.right.parent = grand;
            }
            parent.right = grand;
            grand.parent = parent;
            return;
        }

        Node<E> gParent = grand.parent;
        if (grand.isLeft()) {
            gParent.left = parent;
        } else {
            gParent.right = parent;
        }
        parent.parent = gParent;
        grand.left = parent.right;
        if (parent.right != null) {
            parent.right.parent = grand;
        }
        parent.right = grand;
        grand.parent = parent;
    }

    protected Node<E> createNode(E e, Node<E> parent) {
        return new Node<>(e, parent);
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> eNode = (Node<E>) node;
        return eNode.e;
    }

    public static void main(String[] args) {

        int[] array = new int[]{89,19,69,93,91,90,92,99,100,11,101,102};

        BinarySearchTree<Integer> tree = new BinarySearchTree<>(null);
        for (int i : array) {
            tree.add(i);
        }

        BinaryTrees.print(tree);


        tree.remove(91);
        BinaryTrees.print(tree);


    }
}
