package com.xauv.algorithm.题目.数据结构;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/12/24 21:57
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.数据结构
 * @Desc
 */
public class GraphTreeFactory implements BinaryTreeInfo {

    public static void main(String[] args) {
        GraphTreeFactory tree = new GraphTreeFactory();
        TreeNode init = tree.init(7);
        BinaryTrees.println(tree);
    }

    TreeNode root;

    public TreeNode init(int nodeCount) {

        List<TreeNode> nodes = new ArrayList<>();

        for (int i = 0; i <nodeCount; i++) {
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
        root = nodes.get(0);
        return nodes.get(0);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((TreeNode)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((TreeNode)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((TreeNode)node).code;
    }
}
