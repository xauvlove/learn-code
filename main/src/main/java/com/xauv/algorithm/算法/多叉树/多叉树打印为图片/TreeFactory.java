package com.xauv.algorithm.算法.多叉树.多叉树打印为图片;

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
        TreeNode<String> h = new TreeNode<>("H部门");
        TreeNode<String> g = new TreeNode<>("G部门");

        TreeNode<String> d1 = new TreeNode<>("d1");
        TreeNode<String> d2 = new TreeNode<>("d2");
        TreeNode<String> d3 = new TreeNode<>("d3");
        TreeNode<String> d4 = new TreeNode<>("d4");
        TreeNode<String> d5 = new TreeNode<>("d5");


        addChildren(r, b, c, d);
        addChildren(b, e, f);
        addChildren(d, h);
        addChildren(f, g);

        addChildren(e, d1, d2);
        addChildren(g, d5);
        addChildren(c, d3, d4);
        return r;
    }

    public static void addChildren(TreeNode<String> parent, TreeNode<String> ... nodes) {
        for (TreeNode<String> node : nodes) {
            node.parent = parent;
            parent.children.add(node);
        }
    }
}
