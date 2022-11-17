package com.xauv.algorithm.算法.字符串;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;
import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;
import com.xauv.algorithm.题目.数据结构.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/11/16 19:53
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.字符串
 * @Desc
 *
 *
 * 树
 *   ┌──1──┐
 *   │     │
 * ┌─2─┐ ┌─3─┐
 * │   │ │   │
 * 4   5 6   7
 *
 *
 * 子树
 * ┌─2─┐
 * │   │
 * 4   5
 *
 * 两棵树，其中一棵为另一棵的子树
 *
 * 这棵树不是子树，虽然具有相同结构，缺少了右子树
 *
 *   ┌──1
 *   │
 * ┌─2─┐
 * │   │
 * 4   5
 *
 * =============================================================
 *
 * 树
 *
 *   ┌──3──┐
 *   │     │
 * ┌─4─┐   5
 * │   │
 * 1   2
 *
 * 通过后续遍历
 * 将其序列化为字符串：#!#!1!#!#!2!4!#!#!5!3!
 *
 * ┌─4─┐
 * │   │
 * 1   2
 *
 * 通过后续遍历
 * 序列化为：#!#!1!#!#!2!4!
 *
 * 可以发现，树 2 是树 1 的子树，那么序列化的结果：序列化结果 1 包含 序列化结果 2
 *
 * 序列化规则为：
 * 由于是后续遍历，先打印左子树，再打印右子树，如果子树为空，那么用 # 表示，节点使用 ! 分隔
 *
 * 可以看到树
 * ┌─4─┐
 * │   │
 * 1   2
 *
 * 后续遍历结果为 1 2 4
 *
 * 4 的左右子树为 1 和 2
 *
 * 但 1 没有左右子树，因此 1 这颗子树序列化为 #!#!1
 *    2 没有左右子树，因此 2 这颗子树序列化为 #!#!2
 *
 * 接下来打印 4 这颗子树
 *
 * #!#!1#!#!2!4!
 *
 * 那么
 * ┌─4─┐
 * │   │
 * 1   2
 * 就打印完毕了
 *
 *
 * 在将树序列化之后，可以根据序列化的结果，再次重新构建出原二叉树
 *
 * 反推原二叉树：我们使用 ! 做了节点的分隔，我们使用 String.spilt 就可以按照顺序拿到所有的节点，其中 # 代表空节点
 *
 */
public class 树的序列化_是否另一棵树的子树 {

    public static boolean isSubTree(TreeNode parent, TreeNode child) {
        String ps = serializeBinaryTree(parent);
        String cs = serializeBinaryTree(child);
        System.out.println("parent serialize = " + ps);
        System.out.println("child serialize = " + cs);
        return ps.contains(cs);
    }

    /**
     * 使用后续遍历实现序列化
     *
     * 不能使用前序遍历
     *
     * @param root
     * @return
     */
    public static String serializeBinaryTree(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serializeBinaryTree(root, sb);
        return sb.toString();
    }

    private static void serializeBinaryTree(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#").append("!");
            return;
        }
        serializeBinaryTree(node.left, sb);
        serializeBinaryTree(node.right, sb);
        sb.append(node.code).append("!");
    }

    public static void main(String[] args) {

        List<TreeNode> nodes = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            TreeNode node = new TreeNode(i+1);
            nodes.add(node);
        }

        for (int i = 1; i < nodes.size(); i++) {
            int parent = i / 2 + 1;
            TreeNode parentNode = nodes.get(parent - 1);
            int leftChild = parent * 2;
            int rightChild = parent * 2 + 1;
            if (leftChild - 1 < nodes.size()) {
                parentNode.setLeft(nodes.get(leftChild - 1));
            }
            if (rightChild - 1 < nodes.size()) {
                parentNode.setRight(nodes.get(rightChild - 1));
            }
        }
        TreeNode root = nodes.get(0);
        TreeNode child = nodes.get(1);
        BinaryTreeInfo binaryTreeInfo = new BinaryTreeInfo() {
            @Override
            public Object root() {
                return root;
            }

            @Override
            public Object left(Object node) {
                return ((TreeNode) node).left;
            }

            @Override
            public Object right(Object node) {
                return ((TreeNode) node).right;
            }

            @Override
            public Object string(Object node) {
                return ((TreeNode) node).code;
            }
        };
        BinaryTrees.println(binaryTreeInfo);
        System.out.println("is sub tree = " + isSubTree(root, child));
    }
}
