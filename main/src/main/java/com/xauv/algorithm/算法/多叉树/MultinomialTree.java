package com.xauv.algorithm.算法.多叉树;

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;

/**
 * @author: Bing Juan
 * @date: 2022/11/22 17 16
 * @desc:
 */
@SuppressWarnings({"rawtype", "unchecked"})
public class MultinomialTree<E> implements BinaryTreeInfo {

    private TreeNode<E> root;

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((TreeNode<E>)node).left();
    }

    @Override
    public Object right(Object node) {
        return ((TreeNode<E>)node).right();
    }

    @Override
    public Object string(Object node) {
        return ((TreeNode<E>)node).val;
    }
}
