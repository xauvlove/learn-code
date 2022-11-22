package com.xauv.algorithm.算法.多叉树;


import com.xauv.algorithm.基本数据结构.图.ListGraph;
import org.apache.dubbo.common.utils.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: Bing Juan
 * @date: 2022/11/22 17 50
 * @desc:
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class GraphGenerator<E> {

    /**
     * 对多叉树层次遍历获取图
     * @param root
     * @return
     */
    public ListGraph<TreeNode, E> createGraph(TreeNode root) {
        ListGraph<TreeNode, E> graph = new ListGraph<>();
        if (root == null) {
            return graph;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.peek() != null) {
            TreeNode node = queue.poll();
            if (node != root) {
                graph.addEdge(node.parent, node);
            }
            List<TreeNode> children = node.children();
            if (CollectionUtils.isNotEmpty(children)) {
                for (TreeNode child : children) {
                    queue.offer(child);
                }
            }
        }
        return graph;
    }
}
