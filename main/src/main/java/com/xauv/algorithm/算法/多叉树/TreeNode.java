package com.xauv.algorithm.算法.多叉树;

import org.apache.dubbo.common.utils.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Bing Juan
 * @date: 2022/11/22 17 14
 * @desc:
 */
public class TreeNode<E> {

    TreeNode<E> parent;

    E val;

    List<TreeNode<E>> children = new LinkedList<>();

    public TreeNode(E val) {
        this.val = val;
    }

    public TreeNode(E val, TreeNode<E> parent) {
        this.parent = parent;
        this.val = val;
    }

    public TreeNode<E> parent() {
        return parent;
    }

    public List<TreeNode<E>> children() {
        return children;
    }

    public E val() {
        return val;
    }

    public TreeNode<E> left() {
        List<TreeNode<E>> children = children();
        if (CollectionUtils.isNotEmpty(children)) {
            if (children.size() >= 1) {
                return children.get(0);
            }
        }
        return null;
    }

    public TreeNode<E> right() {
        List<TreeNode<E>> children = children();
        if (CollectionUtils.isNotEmpty(children)) {
            if (children.size() >= 2) {
                return children.get(1);
            }
        }
        return null;
    }

    public void addChild(E e) {
        children.add(new TreeNode<>(e, this));
    }

    public void removeChild(E e) {
        children.iterator().forEachRemaining((c) -> {
            if (c.val.equals(e)) {
                c.parent = null;
                children.remove(c);
            }
        });
    }
}
