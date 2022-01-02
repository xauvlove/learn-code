package com.xauv.test.算法.二叉树.实现;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.test.算法.二叉树.utils.BinaryTreeInfo;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2022/1/2 12:28
 * @Author Administrator
 * @Package com.xauv.test.算法.二叉树
 * @Desc
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements BinaryTreeInfo {

    @Override
    public boolean contains(E e) {
        if (root == null || e == null) {
            return false;
        }
        Node<E> node = root;
        while (node != null) {
            int res = e.compareTo(node.value);
            if (res > 0) {
                node = node.left;
            } else if (res < 0) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("添加的元素不能为空");
        }
        if (root == null) {
            root = createNode(e);
            afterAdd(root);
        } else {
            Node<E> node = root;
            Node<E> parent = node;
            while (node != null) {
                int res = e.compareTo(node.value);
                if (res > 0) {
                    parent = node;
                    node = node.right;
                } else if (res < 0) {
                    parent = node;
                    node = node.left;
                } else {
                    node = null;
                }
            }
            Node<E> cNode = createNode(e);
            int res = e.compareTo(parent.value);
            if (res > 0) {
                parent.right = cNode;
            } else if (res < 0) {
                parent.left = cNode;
            } else {
                parent.value = cNode.value;
            }
            cNode.parent = parent;
            afterAdd(cNode);
        }
        size++;
    }

    @Override
    public void remove(E e) {
        if (e == null) {
            throw new IllegalArgumentException("添加的元素不能为空");
        }
        if (root == null) {
            return;
        }
        Node<E> eNode = find(e);
        if (eNode == null) {
            return;
        }

        if (eNode.degree0()) {
            delete0(eNode);
        } else if (eNode.degree1()) {
            delete1(eNode);
        } else if (eNode.degree2()) {
            delete2(eNode);
        } else {
            throw new RuntimeException("求树的度错误");
        }
    }

    protected void afterAdd(Node<E> node) {

    }

    private void delete0(Node<E> node) {

        if (node == root) {
            root = null;
            size--;
            afterRemove(node);
            return;
        }

        Node<E> cur = root;
        Node<E> parent = cur;

        while (cur != null) {
            int res = node.value.compareTo(cur.value);
            if (res > 0) {
                parent = cur;
                cur = cur.right;
            } else if (res < 0) {
                parent = cur;
                cur = cur.left;
            } else {
                break;
            }
        }
        if (cur == null) {
            throw new RuntimeException("找不到节点的父节点，出现错误");
        }
        if (parent.left == node) {
            parent.left = null;
        } else {
            parent.right = null;
        }
        afterRemove(node);
        size--;
    }

    protected void afterRemove(Node<E> node) {

    }

    private void delete1(Node<E> node) {
        Node<E> left = node.left;
        Node<E> right = node.right;
        Node<E> child = left != null ? left : right;
        if (root == node) {
            root = child;
        } else {
            Node<E> parent = node.parent;
            if (parent == null) {
                throw new RuntimeException("删除度为 1 的节点时，它不是根节点，但父节点为空");
            }
            if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            child.parent = parent;
        }
        afterRemove(node);
        size--;
    }

    private void delete2(Node<E> node) {
        Node<E> predecessor = getPredecessor(node);
        Node<E> replaced = predecessor != null ? predecessor : getSuccessor(node);
        if (replaced == null) {
            throw new RuntimeException("节点无前驱也无后继，但是计算得到度为 2");
        }
        remove(replaced.value);
        node.value = replaced.value;
    }

    @Override
    public int height() {
        return treeLevel();
    }

    @Override
    public void inverse() {
        if (root == null) {
            return;
        }
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            Node<E> left = poll.left;
            Node<E> right = poll.right;
            poll.left = right;
            poll.right = left;
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }
    }

    @Override
    public boolean complete() {
        if (root == null) {
            return true;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean allLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            Node<E> left = poll.left;
            Node<E> right = poll.right;

            if (allLeaf && !poll.isLeaf()) {
                return false;
            }

            // 左不空，右不空
            if (left != null && right != null) {
                queue.offer(left);
                queue.offer(right);
            }
            // 左不空，右空
            else if (left != null) {
                queue.offer(left);
            }
            // 左空，右不空
            else if (right != null) {
                return false;
            }
            // 左空，右空
            else {
                allLeaf = true;
            }
        }
        return true;
    }

    protected Node<E> createNode(E e) {
        return new Node<>(e);
    }

    private int treeHeight() {
        return treeHeight(root);
    }

    private int treeHeight(Node<E> node) {
        if (node == null) {
            return 0;
        }
        Node<E> left = node.left;
        Node<E> right = node.right;
        return Math.max(treeHeight(left), treeHeight(right)) + 1;
    }

    private int treeLevel() {
        return treeLevel(root);
    }

    private int treeLevel(Node<E> node) {
        if (node == null) {
            return 0;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        int queueSize = 1;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            queueSize--;
            boolean emptyQueue = false;
            if (queueSize == 0) {
                height++;
                emptyQueue = true;
            }
            Node<E> left = poll.left;
            if (left != null) {
                queue.offer(left);
            }

            Node<E> right = poll.right;
            if (right != null) {
                queue.offer(right);
            }
            if (emptyQueue) {
                queueSize = queue.size();
            }
        }
        return height;
    }

    private Node<E> find(E e) {
        if (e == null) {
            return null;
        }
        if (root == null) {
            return null;
        }

        Node<E> node = root;

        while (node != null) {
            int res = e.compareTo(node.value);
            if (res > 0) {
                node = node.right;
            } else if (res < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    private Node<E> findParent(Node<E> node) {
        if (node == root) {
            return null;
        }

        Node<E> parent = root;

        while (parent != null) {
            int res = node.value.compareTo(parent.value);
            if (res > 0) {
                parent = parent.right;
            } else if (res < 0) {
                parent = parent.left;
            } else {
                break;
            }
        }
        return parent;
    }

    private Node<E> getPredecessor(Node<E> node) {
        if (root == null) {
            return null;
        }

        Node<E> left = node.left;
        if (left != null) {
            Node<E> cur = left;
            while (true) {
                if (cur.right == null) {
                    return cur;
                }
                cur = cur.right;
            }
        } else {
            Node<E> cur = node;
            while (true) {
                Node<E> parent = cur.parent;
                if (parent == null) {
                    return null;
                }
                Node<E> grand = parent.parent;
                if (grand == null) {
                    return null;
                }
                if (grand.right == parent) {
                    return grand;
                }
                cur = parent;
            }
        }
    }

    private Node<E> getSuccessor(Node<E> node) {
        if (root == null) {
            return null;
        }

        Node<E> right = node.right;
        if (right != null) {
            Node<E> cur = right;
            while (true) {
                if (cur.left == null) {
                    return cur;
                }
                cur = cur.left;
            }
        } else {
            Node<E> cur = node;
            while (true) {
                Node<E> parent = cur.parent;
                if (parent == null) {
                    return null;
                }
                Node<E> grand = parent.parent;
                if (grand == null) {
                    return null;
                }
                if (grand.left == parent) {
                    return grand;
                }
                cur = parent;
            }
        }
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
        return ((Node<E>) node).value;
    }
}
