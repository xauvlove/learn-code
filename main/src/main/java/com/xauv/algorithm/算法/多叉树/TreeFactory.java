package com.xauv.algorithm.算法.多叉树;

/**
 * @author: Bing Juan
 * @date: 2022/11/22 18 27
 * @desc:
 */
public class TreeFactory {

    public static TreeNode<String> create() {
        TreeNode<String> r = new TreeNode<>("A", null);
        TreeNode<String> B = new TreeNode<>("B", null);
        TreeNode<String> C = new TreeNode<>("C", null);
        TreeNode<String> D = new TreeNode<>("D", null);
        TreeNode<String> E = new TreeNode<>("E", null);
        TreeNode<String> F = new TreeNode<>("F", null);
        r.children.add(B);
        B.parent = r;
        r.children.add(C);
        C.parent = r;
        r.children.add(D);
        D.parent = r;
        B.children.add(E);
        E.parent = B;
        B.children.add(F);
        F.parent = B;
        return r;
    }
}
