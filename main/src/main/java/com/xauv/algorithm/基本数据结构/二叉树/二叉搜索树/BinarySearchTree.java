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
public class BinarySearchTree<E extends Comparable<E>> implements BinaryTreeInfo {

    /**
     * 树 size
     */
    protected int size;

    /**
     * 根节点
     */
    protected Node<E> root;

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

    public void add(E element) {
        elementNotNullCheck(element);
        // 如果 root = null 则表示添加第一个节点
        if (root == null) {
            root = createNode(element, null);
            size = size + 1;
            afterAdd(root);
            return;
        }

        // 添加的不是第一个节点
        // 1.找到父节点
        // 2.创建新节点 node
        // 3.父节点的 left 或者 right = node
        // 如果插入的值相等 1.直接return 2.直接覆盖旧的值
        Node<E> node = root;
        Node<E> parent = null;
        int res = 0;
        while (node != null) {
            res = element.compareTo(node.element);
            // 暂时保存父节点
            parent = node;
            if (res > 0) {
                // 新元素比当前元素大
                node = node.right;
            } else if (res < 0) {
                // 新元素比当前元素小
                node = node.left;
            } else {
                // 新元素与当前元素相等 替换
                node.element = element;
                return;
            }
        }
        Node<E> addNode = createNode(element, parent);
        if (res > 0) {
            parent.right = addNode;
        } else {
            parent.left = addNode;
        }
        afterAdd(addNode);
        size = size + 1;
    }

    /**
     * 添加完成之后
     * 二叉搜索树不需要做任何事
     * @param node
     */
    protected void afterAdd(Node<E> node) {

    }

    /**
     * 删除的节点
     * 1.为叶子节点
     *  直接删除 置为 null 即可
     *
     * 2.删除度为 1 的节点
     *  直接将被删除的节点的子节点（因为度为1，因此只有一个子节点）替换到该节点的位置
     *  比如树
     *
     *                    7
     *           4                     9
     *       2                              11
     *    1      3                     10         12
     *
     *    2.1.度为 1 的节点有：4，9
     *    如果删除 4，那么直接将其唯一的子节点 2 替换到它的位置即可，还是二叉搜索树
     *    如果删除 9，那么直接将其唯一的子节点 11 替换到它的位置即可，还是二叉搜索树
     *
     *    2.2.如果被删除的节点为根节点，那么将根节点的子节点替换为根节点
     *
     * 3.删除度为 2 的节点
     *   比如树
     *                      5
     *           3                       8
     *       1        4             6          11
     *          2                      10          12
     *    3.1.如果删除节点为根节点 5， 则将 5 的前驱或者后继代替为根节点，然后再在子树中将替换节点删除
     *        比如 5 的前驱是 4，后继是 6，
     *        如果使用前驱 4 替换，则将 5 的值替换为 4，然后再在左子树中将 4 删除
     *        如果使用后继 6 替换，则将 5 的值替换为 6，然后再在右子树中将 6 删除
     *        - 如果一个节点的度为 2，那么它的前驱和后继的度只可能是 0 或者 1
     *        因为：我们找前驱的时候，
     *        - 可能一路往右找，找到最右，那么这个最右的子节点要么没有，要么就只有一个左孩子，不可能有左右孩子，否则它就不是最右的节点了
     *        - 可能一路往上找，找到父节点和祖父节点，且祖父节点的右孩子是父节点
     *        因为：我们找后继的时候，
     *        - 可能一路往左找，找到最左节点，这个最左节点的子节点要么没有，要么就只有一个右孩子
     *        -
     *    3.2.如果删除的节点不是根节点，同样也是按照删除根节点的规则，如果删除的节点为 3
     *        3 的前驱和后继分别是 2 和 4
     *        如果使用前驱 2 替换，则将 3 的值替换为 2，然后再在 3 的左子树中将 2 删除
     *        如果使用后继 4 替换，则将 3 的值替换为 4，然后再在 3 的右子树将 4 删除
     *        - 如果一个节点的度为 2，那么它的前驱和后继的度只可能是 0 或者 1
     * @param element
     */
    public void remove(E element) {
        // 查找节点
        if (root == null) {
            return;
        }

        Node<E> node = findNodeByElement(element);
        if (node == null) {
            return;
        }

        if (isLeaf(node)) {
            delete0(node);
            return;
        }
        if (degree1(node)) {
            delete1(node);
            return;
        }
        if (degree2(node)) {
            delete2(node);
            return;
        }
    }

    /**
     * 删除度为 0 的节点，叶子节点
     */
    private void delete0(Node<E> node) {
        Node<E> parent = node.parent;
        if (parent.left == node) {
            // 父节点的左孩子指向空
            parent.left = null;
            size = size - 1;
        }
        if (parent.right == node) {
            // 父节点的右孩子指向空
            parent.right = null;
            size = size - 1;
        }
        afterRemove(node);
    }

    /**
     * 删除度为 1 的节点
     */
    private void delete1(Node<E> node) {
        Node<E> left = node.left;
        Node<E> right = node.right;
        // 如果删除根节点
        if (node == root) {
            // 左孩子不为空
            if (left != null) {
                root = left;
                left.parent = null;
            }
            // 右孩子不为空
            if (right != null) {
                root = right;
                right.parent = null;
            }
            size = size - 1;
        }
        // 删除节点不是根节点
        else {
            Node<E> parent = node.parent;
            // 左孩子不为空
            if (node.isLeftChild()) {
                if (left != null) {
                    // 祖父节点的左替换为孙子节点
                    parent.left = left;
                    // 孙子节点的父亲指向祖父节点
                    left.parent = parent;
                }
                // 右孩子不为空
                if (right != null) {
                    parent.left = right;
                    right.parent = parent;
                }
            } else if (node.isRightChild()){
                if (left != null) {
                    // 祖父节点的左替换为孙子节点
                    parent.right = left;
                    // 孙子节点的父亲指向祖父节点
                    left.parent = parent;
                }
                // 右孩子不为空
                if (right != null) {
                    parent.right = right;
                    right.parent = parent;
                }
            } else {
                throw new RuntimeException("该节点有父节点，但它既不是左孩子也不是右孩子，请检查上面操作是否有误");
            }

            size = size - 1;
        }
        afterRemove(node);
    }

    /**
     * 删除度为 2 的节点
     */
    private void delete2(Node<E> node) {

        // 如果删除节点且度为 2，找出前驱或者后继
        E predecessorElement = getPredecessor(node.element);
        Node<E> predecessor = findNodeByElement(predecessorElement);
        if (predecessorElement != null) {
            remove(predecessorElement);
            node.element = predecessorElement;
            return;
        }
        E successorElement = getSuccessor(node.element);
        Node<E> successor = findNodeByElement(successorElement);
        if (successorElement != null) {
            remove(successorElement);
            node.element = successorElement;
            return;
        }
    }

    /**
     * 删除节点之后做的事
     * @param node
     */
    protected void afterRemove(Node<E> node) {

    }

    public boolean contains(E element) {
        return findNodeByElement(element) != null;
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
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
            Node<E> cur = right;
            while (cur != null) {
                successor = cur;
                cur = cur.left;
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

    private Node<E> findNodeByElement(E element) {
        // 查找节点
        if (root == null) {
            return null;
        }

        Node<E> cur = root;
        while (cur != null) {
            if (cur.element.compareTo(element) > 0) {
                cur = cur.left;
            } else if (cur.element.compareTo(element) < 0) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

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
            // 条件 4
            else if (left != null) {
                queue.offer(left);
            }
            // 条件 3，后面的节点必须都是叶子节点
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

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("元素不能为空");
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
        public E element;

        /**
         * 左孩子
         */
        public Node<E> left;

        /**
         * 右孩子
         */
        public Node<E> right;

        /**
         * 父节点
         */
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }

        /**
         * 求兄弟节点
         * @return
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }

        /**
         * 求叔父节点
         * @return
         */
        public Node<E> uncle() {
            if (parent == null) {
                return null;
            }
            // 叔父节点就是父节点的兄弟节点
            return parent.sibling();
        }
    }
}