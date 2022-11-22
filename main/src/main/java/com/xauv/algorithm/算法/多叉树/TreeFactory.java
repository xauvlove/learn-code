package com.xauv.algorithm.算法.多叉树;

/**
 * @author: Bing Juan
 * @date: 2022/11/22 18 27
 * @desc:
 */
@SuppressWarnings("unchecked")
public class TreeFactory {

    public static TreeNode<String> create() {
        TreeNode<String> r = new TreeNode<>("总部");
        TreeNode<String> b = new TreeNode<>("B部门");
        TreeNode<String> c = new TreeNode<>("C部门");
        TreeNode<String> d = new TreeNode<>("D部门");
        TreeNode<String> e = new TreeNode<>("E部门");
        TreeNode<String> f = new TreeNode<>("F部门");

        addChildren(r, b, c, d);
        addChildren(b, e, f);
        return r;
    }

    public static void addChildren(TreeNode<String> parent, TreeNode<String> ... nodes) {
        for (TreeNode<String> node : nodes) {
            node.parent = parent;
            parent.children.add(node);
        }
    }
}
